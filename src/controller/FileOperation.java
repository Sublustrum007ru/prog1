package controller;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static util.Validator.isNotEmptySiteSettings;

public class FileOperation implements Operation{
    @Override
    public List<SiteSettings> readFile(String path) throws IOException {
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
                        System.out.println("Ошибка в формате строки: " + line);
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
                    System.out.println("Ошибка в обработке строки: " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void writeFile(String patch, SiteSettings siteSettings) {
        System.out.println("Test run FileOperation");
//        if (patch.isEmpty()) {
//            patch = "FileOperation.txt";
//        }
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(patch))) {
//            if (!isNotEmptySiteSettings(siteSettings)) {
//                mainController.message("Test Все поля должны быть заполнены");
//            } else {
//                writer.write(siteSettings.toString());
//                mainController.message("Test Succesful");
//            }
//        } catch (IOException e) {
//            mainController.message("Test WARNIG " + e.getMessage());
//        }

    }

}
