import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MarqueeApplet extends Applet implements Runnable {
    private String message = "Welcome to Java Applet Marquee!";
    private int xPosition = 600; // Start position of the text
    private Thread marqueeThread;

    @Override
    public void init() {
        setBackground(Color.black);
        setForeground(Color.white);
        setFont(new Font("Arial", Font.BOLD, 30));

        // Start the marquee thread
        marqueeThread = new Thread(this);
        marqueeThread.start();
    }

    @Override
    public void run() {
        while (true) {
            // Move the text to the left
            xPosition -= 2;

            // If the text has completely moved off the screen, reset its position
            if (xPosition < -getFontMetrics(getFont()).stringWidth(message)) {
                xPosition = getWidth();
            }

            // Repaint the applet to update the position of the text
            repaint();

            try {
                // Pause the thread for 50 milliseconds to control the speed of the scrolling
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // Draw the message at the current x position
        g.drawString(message, xPosition, 100);
    }

    @Override
    public void stop() {
        // Stop the thread when the applet is stopped
        marqueeThread = null;
    }
}
// <html>
// <body>
//     <applet code="MarqueeApplet.class" width="600" height="200">
//     </applet>
// </body>
// </html>
