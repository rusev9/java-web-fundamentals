package org.softuni.javache.util;

import org.softuni.javache.WebConstants;
import org.softuni.javache.io.Reader;

import java.io.*;
import java.util.Set;

public class JavacheConfigService {
    private static final String CONFIG_FOLDER_PATH = WebConstants.SERVER_ROOT_FOLDER_PATH + "config/";

    private static final String REQUEST_HANDLER_PRIORITY_CONFIG_FILE_PATH =
            CONFIG_FOLDER_PATH + "config.ini";

    private Set<String> requestHandlerPriority;

    public JavacheConfigService() {
        this.initConfigurations();
    }

    private void initConfigurations() {
        try {
            this.loadRequestHandlerConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRequestHandlerConfig() throws IOException {
        File priorityConfigFile = new File(this.REQUEST_HANDLER_PRIORITY_CONFIG_FILE_PATH);

        if (!priorityConfigFile.exists() || !priorityConfigFile.isFile()) {
            throw new IllegalArgumentException("Request Handler priority configuration file does not exist.");
        }

        String configFileContent = Reader.readAllLines(new BufferedInputStream(
                        new FileInputStream(priorityConfigFile)));

        System.out.println(configFileContent);
    }
}
