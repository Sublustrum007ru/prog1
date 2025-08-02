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
    private final String SUFFICS_PATH = "txt";


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public String[] readFile(String path) throws IOException {
        String[] result = null;
        if (path.isEmpty()) {
            if (!Files.exists(Paths.get(PATH_DIR + DEFAULT_FILE + SUFFICS_PATH))) {
                mainController.message("Файл настроек по умолчанию не найден. Используются значения по умолчанию.");
                result = new String[]{"", "", "", "", "", ""}; // Конструктор по умолчанию
            }
            path = DEFAULT_FILE;
        }
        if (!Files.exists(Paths.get(PATH_DIR + path + SUFFICS_PATH))) {
            mainController.message("Файл '" + path + SUFFICS_PATH + "' настроек не найден. Используются значения из дефолтного файла!!!!.");
            readFile("");
        } else {
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
                mainController.message("Файл настроек не найден. Используются значения по умолчанию.");
//            e.printStackTrace();
            }
            if (temp == null) {
                return result = new String[]{"", "", "", "", "", ""};
            }
            result = temp.split(" \\| ", -1);
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
