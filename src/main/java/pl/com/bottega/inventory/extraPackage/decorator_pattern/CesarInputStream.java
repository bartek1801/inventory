package pl.com.bottega.inventory.extraPackage.decorator_pattern;

import java.io.IOException;
import java.io.InputStream;

public class CesarInputStream extends InputStream {

    private InputStream decorated;

    private int key;

    public CesarInputStream(InputStream decorated, int key) {
        this.decorated = decorated;
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        //TODO
        return 0;
    }
}
