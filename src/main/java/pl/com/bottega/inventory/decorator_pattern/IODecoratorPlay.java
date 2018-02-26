package pl.com.bottega.inventory.decorator_pattern;

import java.io.*;

public class IODecoratorPlay {

    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream("C:\\Users\\bartek\\IdeaProjects\\inventory_test\\src\\main\\java\\pl\\com\\bottega\\inventory\\decorator_pattern\\test.txt");
        int k = 200;
        outputStream = new CesarOutputStream(outputStream, k);
        outputStream.write("Test Cesar code text".getBytes());
        outputStream.close();

    }

}
