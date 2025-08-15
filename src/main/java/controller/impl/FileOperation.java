package controller.impl;


import controller.MainController;
import controller.Operation;
import controller.SiteSettings;
import java.io.*;

import static util.Validator.isNotEmptySiteSettings;

public class FileOperation implements Operation {
    private MainController mainController = new MainController();

    private final String PATH_DIR = "src/main/java/siteSettings/";
    private final String DEFAULT_FILE = "default";
    private final String SUFFICS_PATH = ".txt";
    private String temp = null;


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public String[] readFile(String path) throws IOException {
        mainController.message("Чтение из файла. " + PATH_DIR + path + SUFFICS_PATH);
        String[] result;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH_DIR + path + SUFFICS_PATH));
            String line = reader.readLine();
            while (line!= null) {
                temp = line;
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            if (path.equals("default")) {
                mainController.message("Дефолтного файла нет.");
                createDefaultFileSettings(path);
            }
            path = DEFAULT_FILE;
            mainController.message("Файла не существует. Чтение из дефолтного файла. " + PATH_DIR + path + SUFFICS_PATH);
            readFile(path);
        }
        result = temp.split(" \\| ", -1);
        return result;
    }

    private void createDefaultFileSettings(String path){
        mainController.message("Создание дефолтного файла");
        SiteSettings siteSettings = new SiteSettings("default", "default", "default", "default", "default", "default");
        writeFile(path, siteSettings);
    }


    @Override
    public void writeFile(String path, SiteSettings siteSettings) {
        if (path.isEmpty()) {
            path = DEFAULT_FILE;
        }
        if (!isNotEmptySiteSettings(siteSettings)) {
            mainController.message("Все поля должны быть заполнены");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_DIR + path + SUFFICS_PATH))) {
                mainController.message("Writing....");
                writer.write(siteSettings.toString());
            } catch (IOException e) {
                mainController.message("WARNIG " + e.getMessage());
            }
        }
    }

    public void testWriteJSON(SiteSettings siteSettings){
        String nameFileToJSON = "site_Settings.json";
    }
}
