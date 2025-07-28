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

    public void setMainGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
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
    public List<SiteSettings> readFile(String path) {
        List<SiteSettings> list = new ArrayList<>();
        String siteName;
        String baseURL;
        String categorySelector;
        String productSelector;
        String titleSelector;
        String priceSelector;
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                try {
                    String[] parts = line.split("\\|", -1);
                    if (parts.length < 6) {
                        mainGUI.showMessage("Ошибка в формате строки: " + line);
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
                    mainGUI.showMessage("Ошибка в обработке строки: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            mainGUI.showMessage("Ошибка чтения файла: " + e.getMessage());
        }
        return list;

    }

    @Override
    public void writeFile(String path, SiteSettings siteSettings) {
        mainGUI.showMessage(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            if (!isNotEmptySiteSettings(siteSettings)) {
                mainGUI.showMessage("Все поля должны быть заполнены");
            } else {
                writer.write(siteSettings.toString());
                mainGUI.showMessage("Succesful");
            }
        } catch (IOException e) {
            mainGUI.showMessage("WARNIG " + e.getMessage());
        }

    }
}
