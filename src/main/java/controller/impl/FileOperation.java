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
    private String[] temp1;

    private SiteSettings setting;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public SiteSettings readFile(String path) throws IOException {
        showMessage("Чтение из файла. " + PATH_DIR + path + SUFFICS_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_DIR + path + SUFFICS_PATH));) {
            String line = null;
            int counter = 0;
            String[] lineObject = new String[9];
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) {
                    continue;
                }
                lineObject[counter++] = line;
                if (counter == 9) {
                    counter = 0;
                    setting = new SiteSettings(
                            lineObject[0],
                            lineObject[1],
                            lineObject[2],
                            lineObject[3],
                            lineObject[4],
                            lineObject[5],
                            lineObject[6],
                            lineObject[7],
                            lineObject[8]);
                }
            }
        } catch (IOException e) {
            if (path.equals("default")) {
                showMessage("Дефолтного файла нет.");
                createDefaultFileSettings(path);
            }
            path = DEFAULT_FILE;
            showMessage("Файла не существует. Чтение из дефолтного файла. " + PATH_DIR + path + SUFFICS_PATH);
            readFile(path);
        }
        return setting;
    }



    public SiteSettings testReadFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_DIR + DEFAULT_FILE+ SUFFICS_PATH));) {
            String line = null;
            int counter = 0;
            String[] lineObject = new String[9];
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) {
                    continue;
                }
                lineObject[counter++] = line;
                if (counter == 9) {
                    counter = 0;
                    setting = new SiteSettings(lineObject[0], lineObject[1], lineObject[2], lineObject[3], lineObject[4], lineObject[5], lineObject[6], lineObject[7], lineObject[8]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return setting;
    }

    private void createDefaultFileSettings(String path){
        showMessage("Создание дефолтного файла");
        SiteSettings siteSettings = new SiteSettings("default", "default", "default", "default", "default", "default", "default", "default", "default");
        writeFile(path, siteSettings);
    }


    @Override
    public void writeFile(String path, SiteSettings siteSettings) {
        if (path.isEmpty()) {
            path = DEFAULT_FILE;
        }
        if (!isNotEmptySiteSettings(siteSettings)) {
            showMessage("Все поля должны быть заполнены");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_DIR + path + SUFFICS_PATH))) {
                showMessage("Writing....");
                writer.write(siteSettings.toString());
            } catch (IOException e) {
                showMessage("WARNIG " + e.getMessage());
            }
        }
    }

    private <T>void showMessage(T message){
        mainController.showMessage(message);
    }

    public void testWriteJSON(SiteSettings siteSettings){
        String nameFileToJSON = "site_Settings.json";
    }
}
