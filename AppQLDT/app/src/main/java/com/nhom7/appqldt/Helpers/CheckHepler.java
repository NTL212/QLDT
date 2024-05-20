package com.nhom7.appqldt.Helpers;

public class CheckHepler {
    public static boolean checkCCCD(String s) {
        // Kiểm tra độ dài của chuỗi
        if (s.length() != 12) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return true;
                }
            }
            return true;
        }

        // Kiểm tra từng ký tự trong chuỗi có phải là số hay không


        // Nếu qua được tất cả các kiểm tra, trả về True
        return false;
    }
    public static boolean checkPhone(String s) {
        // Kiểm tra độ dài của chuỗi
        if (s.length() != 10) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return true;
                }
            }
            return true;
        }

        // Kiểm tra từng ký tự trong chuỗi có phải là số hay không


        // Nếu qua được tất cả các kiểm tra, trả về True
        return false;
    }
}
