import java.awt.*;
import javax.swing.*;
import java.util.*;

public class backgroundimage implements Runnable {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JLabel label2 = new JLabel();
    JPanel panel = new JPanel();


    @Override
    public void run() {

        panel.setLayout(new BorderLayout());
        panel.setSize(1200,700);
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 710);
        frame.setResizable(false);
        frame.setTitle("ITED");
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);


        label.setPreferredSize(new Dimension(1200, 700)); // Set the size of the label
        label2.setText("CENIMA  SHOP");
        label2.setHorizontalTextPosition(JLabel.RIGHT);
        label2.setForeground(Color.RED);
        label.add(label2);
        // Load the image  
        ImageIcon icon = new ImageIcon("D:\\Cinema project\\backgroundimage.java.jpg");
        Image scaledImage = icon.getImage().getScaledInstance(1200, 700, Image.SCALE_SMOOTH);
        icon = new ImageIcon(scaledImage);
        JLabel label = new JLabel(icon);

        // تعيين محاذاة الصورة لتمتد على كامل حجم الـ JLabel
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        // إضافة JLabel إلى JPanel
        panel.add(label, BorderLayout.CENTER);

        // إضافة JPanel إلى الإطار الرئيسي
        frame.add(panel);
        frame.setVisible(true);

        //throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    void destroy() {
        frame.dispose();

    }

}
