package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static String load(String str) {
        Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config.properties"));
        } catch (
                FileNotFoundException e) {
            throw new RuntimeException("找不到配置config.properties文件", e);
        } catch (
                IOException e) {
            throw new RuntimeException("读取配置config.properties文件出错", e);
        }
        return props.getProperty(str);
    }
}
