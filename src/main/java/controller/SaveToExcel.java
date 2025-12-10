package controller;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class SaveToExcel {
    List<String> list = new ArrayList<>();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd_MMM_yyyy");

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void Save(List<String> list, String fileName) {
        SaveToFile1(list, fileName);
    }

    public void SaveToFile1(List<String> list, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        String date = LocalDate.now().format(dateFormatter);
        Sheet newSheet = workbook.createSheet(date);
        for (int i = 0; i < list.size(); i++) {
            String[] str = list.get(i).split(" \\| ");
            String name = str[0];
            String price = str[1];
            Row row = newSheet.createRow(i);
            row.createCell(0).setCellValue(name);
            row.createCell(1).setCellValue(price);
        }
        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\user\\Documents\\"+fileName+".xlsx");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("File create!!!");
        } catch (Exception e) {
            System.out.println("Что то пошло не так!!!!");
            System.out.println("MSG: " + e.getMessage());
        }
    }

    public void SaveToFile() {
        String message = "Начало експорта данных";
        showMessage(message);
        Workbook workbook = new XSSFWorkbook();
        Sheet newSheet = workbook.createSheet("Test");

        Row row1 = newSheet.createRow(0);
        row1.createCell(0).setCellValue("Name");
        row1.createCell(1).setCellValue("Age");
        row1.createCell(2).setCellValue("Phone");

        try {
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\user\\Documents\\test.xlsx");
            workbook.write(fileOut);
            fileOut.close();
            System.out.println("File create!!!");
        } catch (Exception e) {
            System.out.println("Что то пошло не так!!!!");
            System.out.println("MSG: " + e.getMessage());
        }
    }

    private <T> void showMessage(T message) {
        mainController.message(message);
    }
}