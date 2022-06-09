package Fakehalla.Game.Entity.Animations;

import Fakehalla.Settings.Settings;
import Fakehalla.Settings.SettingsLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BackgroundImageView extends ImageView { //images on background
    private Settings settings;
    private int width, height;
    private double currentOffset = 0;
    public BackgroundImageView(Image image) throws IOException, ClassNotFoundException {
        super(image);
        this.settings = new SettingsLoader().loadSettings("settings.txt");
        width = settings.getWidth();
        height = settings.getHeight();
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setPreserveRatio(false);
        this.setViewport(new Rectangle2D(0, 0, width, height));
    }

    public void update(double movement, int offset){ //Updating position of background according to time passed
        currentOffset+=movement*width*0.000000000000005;
        currentOffset%=(width-offset);
        this.setViewport(new Rectangle2D(currentOffset, 0, width, height));
    }
}
