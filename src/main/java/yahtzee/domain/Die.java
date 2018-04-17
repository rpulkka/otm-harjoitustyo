package yahtzee.domain;

// @author rpulkka
import java.util.ArrayList;
import javafx.scene.image.ImageView;

public class Die {

    private int value;
    private ImageView slot;
    private DieImage valueImage;
    private ArrayList<DieImage> imageOptions;
    private boolean chosen;

    public Die(ArrayList<DieImage> images) {
        this.value = 1;
        this.slot = new ImageView();
        this.valueImage = images.get(0);
        this.slot.setImage(images.get(0).getImage());
        this.imageOptions = images;
        this.chosen = true;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value >= 0 && value <= 6) {
            this.value = value;
            changeImage(value);
        }
    }

    public void changeImage(int value) {
        switch (value) {
            case 1:
                valueImage = imageOptions.get(0);
                slot.setImage(valueImage.getImage());
                break;
            case 2:
                valueImage = imageOptions.get(1);
                slot.setImage(valueImage.getImage());
                break;
            case 3:
                valueImage = imageOptions.get(2);
                slot.setImage(valueImage.getImage());
                break;
            case 4:
                valueImage = imageOptions.get(3);
                slot.setImage(valueImage.getImage());
                break;
            case 5:
                valueImage = imageOptions.get(4);
                slot.setImage(valueImage.getImage());
                break;
            case 6:
                valueImage = imageOptions.get(5);
                slot.setImage(valueImage.getImage());
                break;
            default:
                break;
        }
    }

    public ImageView getSlot() {
        return slot;
    }

    public void setSlot(ImageView slot) {
        this.slot = slot;
    }

    public boolean getChosen() {
        return chosen;
    }

    public void setChosen(boolean boo) {
        chosen = boo;
    }
}
