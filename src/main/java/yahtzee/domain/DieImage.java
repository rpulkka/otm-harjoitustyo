package yahtzee.domain;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import javafx.scene.image.Image;

// @author rpulkka
public class DieImage {

    private InputStream is;
    private Image image;

    public DieImage(URL url) throws IOException {
        this.is = url.openStream();
        this.image = new Image(is);
    }

    public InputStream getIs() {
        return is;
    }

    public void setIs(InputStream is) {
        this.is = is;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
