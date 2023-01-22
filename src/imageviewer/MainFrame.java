package imageviewer;

import imageviewer.architecture.Command;
import imageviewer.architecture.ImageDisplay;
import imageviewer.architecture.ImageWindow;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final Map<String,Command> commands = new HashMap<>();
    private ImagePanel imageDisplay;

    public static MainFrame create() throws IOException {
        return new MainFrame();
    }

    private MainFrame() throws IOException {
        this.setTitle("Image Viewer");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(imageDisplay = imagePanel());
        this.add(nextButton(), BorderLayout.EAST);
        this.add(prevButton(), BorderLayout.WEST);
    }
    
   
    void start() {
        this.setVisible(true);
    }
    
    public void add(String name, Command command) {
        commands.put(name, command);
    }

    
    private ImagePanel imagePanel()  {
        return new ImagePanel();
    }

    private Component nextButton() {
        final JButton button = new JButton(">");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("next").execute();
            }
        });
        return button;
    }

    private Component prevButton() {
        final JButton button = new JButton("<");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                commands.get("prev").execute();
            }
        });
        return button;
    }

    ImageDisplay imageDisplay() {
        return imageDisplay;
    }
    
}
