package com.example.mailaccountscanner.service.mapper;

import com.example.mailaccountscanner.service.dto.MailDTO;
import org.apache.commons.codec.digest.DigestUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.SerializationUtils;


import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "receivedDate", target = "receiptDate")
    @Mapping(source = "message", target = "sender", qualifiedByName = "addressToString")
    @Mapping(source = "message", target="hashHex", qualifiedByName = "computeHash")
    MailDTO toMailDTO(Message message) throws Exception;

    @Named("computeHash")
    default String computeHash(Message message) throws Exception {
       return DigestUtils.sha256Hex(this.toByteArray(message));
    }
   
    @Named("addressToString")
    default String addressToString(Message message) throws MessagingException {
       return Arrays.stream(message.getFrom())
               .map(Address::toString)
               .collect(Collectors.joining(","));
    }

    default byte[] toByteArray(Message message) throws Exception {
        ObjectOutputStream objStream = null;
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            objStream = new ObjectOutputStream(byteStream);
            message.writeTo(objStream);
            objStream.close();
            return byteStream.toByteArray();
        }finally {
            if(objStream!=null)
               objStream.close();
        }

    }

    default Instant map(Date date){
        return date.toInstant();
    }

}
