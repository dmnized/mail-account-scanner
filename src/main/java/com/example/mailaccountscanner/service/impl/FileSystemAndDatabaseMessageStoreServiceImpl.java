package com.example.mailaccountscanner.service.impl;

import com.example.mailaccountscanner.service.MailService;
import com.example.mailaccountscanner.service.MessageStoreService;
import com.example.mailaccountscanner.service.dto.MailDTO;
import com.example.mailaccountscanner.service.mapper.MessageMapper;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Qualifier("FileSystemAndDatabaseMessageStoreServiceImpl")
public class FileSystemAndDatabaseMessageStoreServiceImpl implements MessageStoreService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MessageMapper messageMapper;

    private final MailService mailService;

    @Value("${mail-account-scanner.mail.folder}")
    private String fileSystemBasePath;

    public FileSystemAndDatabaseMessageStoreServiceImpl(MessageMapper messageMapper, MailService mailService) {
        this.messageMapper = messageMapper;
        this.mailService = mailService;
    }


    @Override
    public List<MailDTO> saveMessagesForMailAccount(Long mailAccountId, List<Message> messages) {

        List<MailDTO> mailDTOList = new ArrayList<>();

        for(Message message : messages){
            try {
                MailDTO mailDTO = messageMapper.toMailDTO(message);
                if( ! mailService.isMailAlreadyPresent(mailDTO)) {
                    saveMessages(mailAccountId, mailDTOList, message, mailDTO);
                }
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }

        }

        return mailDTOList;
    }

    private void saveMessages(Long mailAccountId, List<MailDTO> mailDTOList, Message message, MailDTO mailDTO) throws Exception {

        String fullPath = null;
        try {
            mailDTO.setMailAccountId(mailAccountId);
            fullPath = saveToFileSystem(mailDTO, message);
            mailDTO.setFullFilePath(fullPath);
            mailDTOList.add(mailService.save(mailDTO));
        }catch(Exception e){
            if(fullPath!=null) {
                File file = new File(fullPath);
                file.delete();
            }
        }
    }

    private String saveToFileSystem(MailDTO mailDTO, Message message) throws Exception {
        File file = getFilePath(mailDTO,message).toFile();
        file.getParentFile().mkdirs();
        FileUtils.writeByteArrayToFile(file, messageMapper.toByteArray(message));
        return file.getAbsolutePath();
    }


    private String getFileSystemBasePath(){
       return this.fileSystemBasePath;
    }

    private Path getFilePath(MailDTO mailDTO, Message message) throws MessagingException {

        return Paths.get(getFileSystemBasePath(),String.valueOf(mailDTO.getMailAccountId()),
                getYear(message.getReceivedDate()),
                getMonth(message.getReceivedDate()),
                getDay(message.getReceivedDate()),
                mailDTO.getHashHex());

    }

    private String getYear(Date date) throws MessagingException {
        return formatDate(date,"yyyy");
    }

    private String getMonth(Date date) throws MessagingException {
        return formatDate(date,"MM");
    }

    private String getDay(Date date) throws MessagingException {
        return formatDate(date,"dd");
    }

    private String formatDate(Date date,String format){
        SimpleDateFormat yearFormat = new SimpleDateFormat(format);
        return yearFormat.format(date);
    }

}
