package utils;

import java.util.Scanner;

public class Utils {
    public double inputDouble(Scanner scanner) {
        while (true){
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Lỗi input, mời nhập lại");
            }
        }
    }
    public int inputInteger(Scanner scanner) {
        while (true){
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Lỗi input, mời nhập lại");
            }
        }
    }
}
