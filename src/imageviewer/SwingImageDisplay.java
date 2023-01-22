package imageviewer;

import imageviewer.architecture.Image;
import imageviewer.architecture.ImageDisplay;
import imageviewer.architecture.ImageWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay{
    private Image image;
    private BufferedImage bi;

    @Override
    public void show(Image image) {
        try {
            this.image = image;
            this.bi = ImageIO.read(new File(image.location()));
            this.repaint();
        } catch (IOException ex) {
            this.bi = null;
        }
    }

    @Override
    public void paint(Graphics g) {
        if (bi == null) return;
        ImageWindow window = ImageWindow.of(bi).adjustTo(this.getWidth(), this.getHeight());
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(bi, window.x(), window.y(), window.width(), window.height(), this);
    }
        
    @Override
    public Image current() {
        return image;
    }
    
}
