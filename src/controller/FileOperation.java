package controller;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static util.Validator.isNotEmptySiteSettings;

public class FileOperation implements Operation {
    private MainController mainController = new MainController();

    private final String PATH = "src/siteSettings/";
    private final String DEFAULT_FILE = "default";


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    @Override
    public String[] readFile(String path) throws IOException {
        String[] result = null;
        if(path.isEmpty()){
            if (!Files.exists(Paths.get(PATH + DEFAULT_FILE))) {
                mainController.message("Файл настроек не найден. Используются значения по умолчанию.");
                return result = new String[]{"","","","","",""}; // Конструктор по умолчанию
            }
            path = DEFAULT_FILE;
        }
        String temp = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH + path + ".txt"));
            String line = reader.readLine();
            while (line != null) {
                temp = line;
                line = reader.readLine();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        if(temp.isEmpty()){
            result = new String[]{"","","","","",""};
        }
        result = temp.split(" \\| ", -1);
        return result;
    }

    @Override
    public void writeFile(String path, SiteSettings siteSettings) {
        if(path.isEmpty()){
            path = DEFAULT_FILE;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter( PATH + path + ".txt"))) {
            if (!isNotEmptySiteSettings(siteSettings)) {
                mainController.message("Все поля должны быть заполнены");
            } else {
                writer.write(siteSettings.toString());
            }
        } catch (IOException e) {
            mainController.message("WARNIG " + e.getMessage());
        }

    }


}
