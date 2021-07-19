package random;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomString {

    public static void main(String[] args) {
        f3();
        f1();
        f2();
    }

    public static void f3(){
        Random rand = new Random();
        System.out.println(rand.nextInt(10));
        System.out.println(String.format("%06d", rand.nextInt(10)));
    }

    public static void f1(){
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));

        System.out.println(generatedString);
    }

    public static void f2() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 100;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
    }


}
