package pl.com.bottega.inventory.decorator_pattern;

import java.io.IOException;
import java.io.OutputStream;

public class CesarOutputStream extends OutputStream {

    private OutputStream decorated;

    private int key;

    public CesarOutputStream(OutputStream decorated, Integer key) {
        this.decorated = decorated;
        this.key = key;
    }


    @Override
    public void write(int b) throws IOException {
        int code = (b + key) % 0xff;
        decorated.write(code);
    }
}
