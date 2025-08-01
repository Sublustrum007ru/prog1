package controller;

import view.MainGUI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static util.Validator.isNotEmptySiteSettings;

public class MainController implements MainView, Operation {
    private MainGUI mainGUI;
    private FileOperation fileOperation;

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
    }

    public void setFileOperation(FileOperation fileOperation){
        this.fileOperation = fileOperation;
    }

    public void message(String message) {
        showMessage(message);
    }

    public void setMainGUIVisible() {
        mainGUI.closeMainGUI();
    }

    @Override
    public void showMessage(String message) {
        mainGUI.showMessage(message);
    }


    @Override
    public List<SiteSettings> readFile(String patch) throws IOException {
        List<SiteSettings> list = new ArrayList<>();
        String siteName;
        String baseURL;
        String categorySelector;
        String productSelector;
        String titleSelector;
        String priceSelector;
        try {
            List<String> lines = Files.readAllLines(Paths.get(patch));
            for (String line : lines) {
                try {
                    String[] parts = line.split("\\|", -1);
                    if (parts.length < 6) {
                        message("Ошибка в формате строки: " + line);
                        continue;
                    }
                    siteName = parts[0].trim();
                    baseURL = parts[1].trim();
                    categorySelector = parts[2].trim();
                    productSelector = parts[3].trim();
                    titleSelector = parts[4].trim();
                    priceSelector = parts[5].trim();
                    SiteSettings temp = new SiteSettings(siteName, baseURL, categorySelector, productSelector, titleSelector, priceSelector);
                    list.add(temp);
                } catch (Exception e) {
                    message("Ошибка в обработке строки: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            message("Ошибка чтения файла: " + e.getMessage());
        }
        System.out.println(fileOperation.readFile(patch));
        return list;
    }

    @Override
    public void writeFile(String patch, SiteSettings siteSettings) {
        FileOperation fileOperation = new FileOperation();
        if (patch.isEmpty()) {
            patch = "default.txt";
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patch))) {
            if (!isNotEmptySiteSettings(siteSettings)) {
                message("Все поля должны быть заполнены");
            } else {
                writer.write(siteSettings.toString());

//                fileOperation.writeFile(patch, siteSettings);

                message("Succesful");
            }
        } catch (IOException e) {
            message("WARNIG " + e.getMessage());
        }

    }
}
