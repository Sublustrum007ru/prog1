package util;

import util.Validator;

public class SearchMaxNumb {
    private Validator valid = new Validator();

    public int search(String str) {
        String[] temp = str.split(" ");
        int max = 1;
        for (int i = 0; i < temp.length; i++) {
            if (valid.isNumber(temp[i])) {
                if (Integer.parseInt(temp[i]) > max) {
                    max = Integer.parseInt(temp[i]);
                }
            }
        }
        return max;
    }

}
