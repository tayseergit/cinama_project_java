import java.awt.*;

public class ColorApp {

    static Color cyan_Red = Color.cyan;// red~
    static Color backColor = new Color(215, 215, 215);// black~
    static Color font_Color = new Color(44, 44, 44);// white ~
    static Color whiteColor_Black = Color.white;// black
    static Color ligtTicketBack = new Color(188, 227, 230);
  
    static Color black_WhiteMainLabel = new Color(44, 44, 44);// black
    static Color buttonTicketDelete = new   Color(248, 58, 58);// black

    
    static public void SwitchColor(boolean sw) {
        if (sw) {
            cyan_Red = Color.cyan;
            backColor = new Color(215, 215, 215);
            font_Color = new Color(44, 44, 44);
            whiteColor_Black = Color.white;//
            black_WhiteMainLabel = new Color(44, 44, 44);
            ligtTicketBack = new Color(188, 227, 230);
             buttonTicketDelete = new   Color(249, 89, 89);
        } else {
            cyan_Red = new Color(126,24,24);
            backColor = new Color(44, 44, 44);
            font_Color = new Color(215, 215, 215);
            whiteColor_Black = Color.black;//
            black_WhiteMainLabel = new Color(180,180,180);
            ligtTicketBack = new Color(21,21,21);
            buttonTicketDelete= new Color(208,0,0);
        }
    }

}
