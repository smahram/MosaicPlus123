import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;

class OvalDraw extends Oval {
    private Boolean drawOvalFilledBlack;
    private Boolean drawOvalFilledYellow;
    public void setDrawOvalFilledBlack() {
        drawOvalFilledBlack = true;
    }

    public OvalDraw() {
        super(0,0,0,0);
        drawOvalFilledBlack = false;
        drawOvalFilledBlack = true;
    }

    public OvalDraw(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);
        drawOvalFilledBlack = false;
        drawOvalFilledYellow = true;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(getPositionX(), getPositionY(), getWidth(), getHeight());

        if (drawOvalFilledBlack) {
            g.setColor(Color.black);
            g.fillOval(getPositionX()+1, getPositionY()+1, getWidth()-1, getHeight()-1);
        }
    }
}


class Face extends OvalDraw {
    private OvalDraw eye1;
    private OvalDraw eye2;
    private int mouth;
    
    public Face() {
        super(0,0,0,0);
        eye1 = new OvalDraw(0,0,0,0);
        eye2 = new OvalDraw(0,0,0,0);
    }

    public Face(int positionXIn, int positionYIn, int widthIn, int heightIn) {
        super(positionXIn, positionYIn, widthIn, heightIn);

        int eyeHeight = 10;
        int eyeWidth = 10;
        int eye1PositionX = (widthIn / 2) - (eyeWidth / 2) - 5; 
        int eye1PositionY = (heightIn / 3) - (eyeHeight / 2);
        int eye2PositionX = (widthIn / 2) - (eyeWidth / 2) + 5; 
        int eye2PositionY = (heightIn / 3) - (eyeHeight / 2);

        eye1 = new OvalDraw(20, 10, eyeWidth, eyeHeight);
        eye2 = new OvalDraw(50, 10, eyeWidth, eyeHeight);

        eye1.setDrawOvalFilledBlack();
        eye2.setDrawOvalFilledBlack();

        mouth = (int)(Math.random()*3);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        eye1.paintComponent(g);
        eye2.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;{
        g2.setStroke(new BasicStroke(2));}
        
        if (mouth == 0) { // 0=sad, 1=smile, 2=neutral
            g.drawArc(getPositionX()+5, getPositionY()+(getHeight() / 2), getWidth()-10, getHeight()-10, 45, 90);
        }
        else if(mouth == 1) {
            g.drawArc(getPositionX() + (getWidth() / 50),  getPositionY(), getWidth(), getHeight() - 10, -45, -90);
        }
        else {
            g.drawLine(getPositionX()+(getWidth()/4)/2+5, getPositionY()+(getHeight())-20, getPositionX()+(getWidth()+1)-20, getPositionY()+(getHeight())-20);
        }

        eye1.paintComponent(g);
        eye2.paintComponent(g);
    }
}