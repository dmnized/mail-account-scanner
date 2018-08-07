package com.example.mailaccountscanner.domain.enumeration;

import java.util.Random;

public enum MailType {

    NON_ACCETTAZIONE,
    POSTA_CERTIFICATA,
    AVVENUTA_CONSEGNA,
    ERRORE_CONSEGNA,
    ACCETTAZIONE,
    PREAVVISO_ERRORE_CONSEGNA;

    public static MailType getRandom(){
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
