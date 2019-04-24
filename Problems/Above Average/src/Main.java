import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        //otherSymbols.setDecimalSeparator(',');
        //otherSymbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", otherSymbols);
        //FileInputStream str = new FileInputStream(args[0]);
        Scanner sc = new Scanner(System.in);
        // Get first line
        int N = Integer.parseInt(sc.nextLine());
        while(sc.hasNextLine()){
            String[] parts = sc.nextLine().split(" ");
            int c = Integer.parseInt(parts[0]);
            double sum = 0;
            for(int i = 1; i <= c; i++){
                sum += Integer.parseInt(parts[i]);
            }
            double average = sum/c;
            double aboveAverage = 0;
            for(int i = 1; i <= c; i++){
                if(Integer.parseInt(parts[i]) > average){
                    aboveAverage++;
                }
            }
            System.out.println(df.format((aboveAverage * 100.0) / c) + "%");
        }
    }
}
