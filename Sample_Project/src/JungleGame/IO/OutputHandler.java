package JungleGame.IO;

import JungleGame.Game.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Douglas Liu on 4/4/2018.
 */
public class OutputHandler {
    private static int cellWidth=70;
    private static int textSize=cellWidth/5;
    private static JFrame f=new JFrame("The Jungle Game");
    private StringBuffer sb;

    private volatile static OutputHandler handler = null;

    // lazy initialization with one static field containing the only OutputHandler
    private OutputHandler() {
        sb = new StringBuffer();
        f.setVisible(false);
    }

    // check if the handler is null, then create or use the current one to return to caller. use synchronization to maintain thread-safe
    public static OutputHandler getOutputHandler() {
        if (handler == null) {
            synchronized (OutputHandler.class) {
                if (handler == null) {
                    handler = new OutputHandler();
                }
            }
        }
        return handler;
    }

    public void draw(Board board) {
        int i,j;
        int isTop,isLeft,isBottom,isRight;
        f.getContentPane().removeAll();
        for(i=0;i<board.getWidth();i++){
            JLabel x = new JLabel();
            x.setSize(cellWidth,cellWidth);
            x.setText(Integer.toString(i+1));
            x.setVerticalAlignment(SwingConstants.NORTH);
            x.setHorizontalAlignment(SwingConstants.CENTER);
            x.setFont(new Font("Serif", Font.BOLD, textSize));
            x.setLocation(i*cellWidth+cellWidth/2,0);
            x.setOpaque(false);
            f.add(x);
        }
        for(i=0;i<board.getHeight();i++){
            JLabel y = new JLabel();
            y.setSize(cellWidth*3/5,cellWidth);
            y.setText(String.valueOf((char)(i+'A')));
            y.setVerticalAlignment(SwingConstants.CENTER);
            y.setHorizontalAlignment(SwingConstants.CENTER);
            y.setFont(new Font("Serif", Font.BOLD, textSize));
            y.setLocation(0,i*cellWidth+cellWidth/3);
            y.setOpaque(false);
            f.add(y);
        }

        for(i=0;i<board.getHeight();i++){
            for(j=0;j<board.getWidth();j++){
                isBottom=(i==(board.getHeight()-1)?1:0);
                isRight=(j==(board.getWidth()-1)?1:0);
                JLabel l = new JLabel();
                l.setSize(cellWidth,cellWidth);
                l.setLocation(j*cellWidth+cellWidth/2,i*cellWidth+cellWidth/3);
                if(board.getCell(i,j).getAnimal()!=null) {
                    l.setText(board.getCell(i, j).getAnimal().getName());
                    l.setHorizontalAlignment(SwingConstants.CENTER);
                    l.setVerticalAlignment(SwingConstants.CENTER);
                    if(board.getCell(i, j).getAnimal().getName().length()>7)
                        l.setFont(new Font("Serif", Font.BOLD, textSize*3/4));
                    else
                        l.setFont(new Font("Serif", Font.BOLD, textSize));
                    //set the text color
                    //animals of the current player is printed in black while the others in lightGray
                    if (board.getCell(i, j).getAnimal().getOwner() == Game.getCurPlayer()) {
                        l.setForeground(Color.black);
                    } else {
                        l.setForeground(Color.gray);
                    }
                }
                //set the background color
                if(board.getCell(i,j) instanceof LandCell){
                    l.setBackground(Color.white);
                }
                else if (board.getCell(i,j) instanceof RiverCell){
                    l.setBackground(Color.cyan);
                }
                else if(board.getCell(i,j) instanceof TrapCell){
                    l.setBackground(Color.orange);
                }
                else if(board.getCell(i,j) instanceof DenCell) {
                    l.setBackground(Color.pink);
                }
                else {
                    l.setBackground(Color.black);
                }
                l.setOpaque(true);
                l.setBorder(BorderFactory.createMatteBorder(1*2,1*2,isBottom*2,isRight*2,Color.red));
                f.add(l);
            }
        }

        JLabel turn = new JLabel();
        f.add(turn);

        f.setSize(board.getWidth()*cellWidth+cellWidth,board.getHeight()*cellWidth+cellWidth*3/2);
        f.setVisible(true);
        f.repaint();
    }

    // print something
    public void printPrompt(String s) {
        // no need to have new line
        System.out.print(s);
    }
}
