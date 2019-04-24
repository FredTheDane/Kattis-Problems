import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class uvcGen{
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("out.txt");
        writer.write("500 10000 " + getRandomNumberInRange(0, 499) + " " + getRandomNumberInRange(0, 499) + "\n");
        for(int i = 0; i < 10000; i++){
            int u = getRandomNumberInRange(0, 499);
            int v = getRandomNumberInRange(0, 499);
            int c = getRandomNumberInRange(1, 100000000);
            if (i > 0) writer.write("\n");
            writer.write(u + " " + v + " " + c);
        }
        writer.close();
        System.out.println("Done");
    }

    private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}