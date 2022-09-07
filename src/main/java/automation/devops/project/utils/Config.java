package automation.devops.project.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public abstract class Config {
	
	private static FileReader reader = null;
	private static Properties properties = new Properties();
	
	static {
    	try {
            reader = new FileReader("target/test-classes/config.properties");
            properties.load(reader);
            reader.close();
            
            reader = new FileReader("target/test-classes/" + properties.getProperty("env") + ".test.properties");
            properties.load(reader);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/*
     * Method to read data from .properties file
     */
    public static String getData(String key) {
        return properties.getProperty(key);
    }
	
	public static String getURL() {
        return getData("app.base.url");
    }
	
	public static String getBrowserType() {
		return getData("browser");
	}
	
	public static String isBrowserEnabled() {
		return getData("run.in.browser");
	}

	public static String getLocalGridURL() {
		return getData("grid.url") + "/wd/hub";
	}
}
