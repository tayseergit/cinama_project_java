
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.AncestorListener;

public class project extends Thread {

  public static void main(String[] args) throws InterruptedException {
    startApplication();
  }

  public static void startApplication() {
    // new UserTickets(2);
    backgroundimage bacgrg = new backgroundimage();
    Thread t1 = new Thread(bacgrg);
    t1.start();

    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    t1.interrupt();
    bacgrg.destroy();
    // ColorApp.SwitchColor(false);
    Main_frame rue = new Main_frame();

  }

}
