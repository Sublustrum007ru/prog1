package util;

import util.Validator;

public class SearchMaxNumb {
    private Validator valid = new Validator();

    public int search(String[] str) {
        int max = 1;
        for (int i = 0; i < str.length; i++) {
            if (valid.isNumber(str[i])) {
                if (Integer.parseInt(str[i]) > max) {
                    max = Integer.parseInt(str[i]);
                }
            }
        }
        return max;
    }

}
