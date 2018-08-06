package com.example.mailaccountscanner.job;
import java.util.Map;

public interface JobProcess {

    void process(Map<String, Object> processData) throws Exception;

}
