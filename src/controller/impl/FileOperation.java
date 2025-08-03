package controller.impl;


import controller.MainController;
import controller.Operation;
import controller.SiteSettings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static util.Validator.isNotEmptySiteSettings;

public class FileOperation implements Operation {
    private MainController mainController = new MainController();

    private final String PATH_DIR = "src/siteSettings/";
    private final String DEFAULT_FILE = "default";
    private final String SUFFICS_PATH = ".txt";


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public String[] readFile(String path) throws IOException {
        String[] result = {"", "", "", "", "", ""};
        if (!Files.exists(Paths.get(PATH_DIR + path + SUFFICS_PATH))) {
            String message = "Файл '"  + path + "' не найден. Попытка загрузить надстройки из файла 'default'";
            mainController.message(message);
            path = DEFAULT_FILE;
            String temp = null;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(PATH_DIR + path + SUFFICS_PATH));
                String line = reader.readLine();
                while (line != null) {
                    temp = line;
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                mainController.message("Файл 'default' настроек не найден. Используются значения по умолчанию.");
                result = new String[]{"", "", "", "", "", ""};
            }
            if (temp == null) {
                result = new String[]{"", "", "", "", "", ""};
            }
            mainController.message("" + temp);
            result = temp.split(" \\| ", -1);
            mainController.message("" + result);
        }
        return result;
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
}
