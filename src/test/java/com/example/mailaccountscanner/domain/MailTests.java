package com.example.mailaccountscanner.domain;

import com.example.mailaccountscanner.utils.AssertAnnotations;
import org.junit.Test;

import javax.persistence.*;

public class MailTests {

    @Test
    public void typeAnnotations(){
        AssertAnnotations.assertType(Mail.class,Entity.class,Table.class);
    }

    @Test
    public void fieldAnnotations(){
        AssertAnnotations.assertField(Mail.class,"id",Id.class,GeneratedValue.class);
        AssertAnnotations.assertField(Mail.class,"mailAccount",ManyToOne.class,JoinColumn.class);
        AssertAnnotations.assertField(Mail.class,"receiptDate",Column.class);
        AssertAnnotations.assertField(Mail.class,"sender",Column.class);
        AssertAnnotations.assertField(Mail.class,"subject",Column.class);
        AssertAnnotations.assertField(Mail.class,"mailType",Enumerated.class,Column.class);
        AssertAnnotations.assertField(Mail.class,"messageIdHeader",Column.class);
        AssertAnnotations.assertField(Mail.class,"xRefMessageIdHeader",Column.class);
        AssertAnnotations.assertField(Mail.class,"hashHex",Column.class);
        AssertAnnotations.assertField(Mail.class,"fullFilePath",Column.class);
    }
}
