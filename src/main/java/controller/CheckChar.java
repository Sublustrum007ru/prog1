package controller;

import java.util.ArrayList;
import java.util.List;

public class CheckChar {
    private List<String> prodyctsList = new ArrayList<>();

    public List<String> Check(List<String> list) {
        List<String> tempList = changeChars(list);
        for (String str : tempList) {
            String temp = checkChar(str);
            prodyctsList.add(temp);
        }
        return prodyctsList;
    }

    private List<String> changeChars(List<String> list) {
        List<String> resultList = new ArrayList<>();
        for (String str : list) {
            String[] arr = str.split("");
            String[] newArr = new String[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (i != 0 && i != arr.length) {
                    if (arr[i].equals("m") && arr[i + 1].equals("l")) {
                        arr[i] = "м";
                        arr[i + 1] = "л";
                    }
                }
                newArr[i] = arr[i];
            }
            String newStr = String.join("", newArr);
            resultList.add(newStr);
        }
        return resultList;
    }

    /***
     * Метод который проверяет есть ли перед еденицей измерения объема пробел, если нету то добавляет
     * @param str - исходная строка.
     * @return - возвращаемая строка.
     */
    private String checkChar(String str) {
        String result = str;
        String[] array = str.split("");
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("м") && array[i + 1].equals("л")) {
                if (checkInt(array[i - 1])) {
                    if (!array[i - 1].equals(" ")) {
                        result = insetChar(str, i);
                    }
                }
            }
            if (array[i].equals("г") && array[i + 1].equals("р")) {
                if (checkInt(array[i - 1])) {
                    if (!array[i - 1].equals(" ")) {
                        result = insetChar(str, i);
                    }
                }
            }
        }
        return result;
    }

    private boolean checkInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * Метод добавления нового(ых) символа(ов) в строку.
     * @param str - исходная строка в которую надо добавить новый символ(ы).
     * @param index - индекс, на какую позицию надо добавить новый(е) символ(ы).
     * @return - возващаемая новая строка.
     */
    private String insetChar(String str, int index) {
        StringBuilder stringBuilder = new StringBuilder(str);
        stringBuilder.insert(index, " ");
        return stringBuilder.toString();
    }
}
