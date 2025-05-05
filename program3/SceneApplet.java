import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class SceneApplet extends Applet {
    public void paint(Graphics g) {

        setBackground(Color.cyan);

        // Sun
        g.setColor(Color.yellow);
        g.fillOval(50, 50, 100, 100); 

        // House body
        g.setColor(Color.orange);
        g.fillRect(200, 200, 200, 150); 

        // Roof
        g.setColor(Color.red);
        int[] xPoints = {200, 400, 300};
        int[] yPoints = {200, 200, 100}; 
        g.fillPolygon(xPoints, yPoints, 3); 

        // Door
        g.setColor(new Color(139, 69, 19));  
        g.fillRect(275, 275, 50, 75); 

        // Window
        g.setColor(Color.white);
        g.fillRect(230, 230, 40, 40);  

        // Tree trunk
        g.setColor(new Color(139, 69, 19));  
        g.fillRect(500, 250, 30, 60); 

        // Tree leaves
        g.setColor(Color.green);
        g.fillOval(485, 200, 60, 60); 

        System.out.println("Name: Abhay Raj\nEnrolment No: 00976803122");
    }
}

/*
<applet code="SceneApplet.class" width="600" height="400">
</applet>
*/
