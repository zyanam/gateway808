package com.cccts.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;
import java.io.FileInputStream;

public final class Log4jHelper {

    /**
     * 启用自定义log4j2.xml的路径，在conf目录下
     */
    public static void useCustomerConfig (){
        ConfigurationSource source;
        String relativePath = "log4j2.xml";
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator")
                + "conf"
                + System.getProperty("file.separator")
                + relativePath;

        File log4jFile = new File(filePath);
        try {
            if (log4jFile.exists()) {
                source = new ConfigurationSource(new FileInputStream(log4jFile), log4jFile);
                Configurator.initialize(null, source);
            } else {
                System.out.println("loginit failed");
                System.exit(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static Logger getLogger(String loggerName) {
        return LogManager.getLogger(loggerName);
    }
}
