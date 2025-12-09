package AI1.NineMensMorrisSolver; // I absolutely hate the package declarations

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
public class visual extends JFrame implements ActionListener {

    int[][] board = new int[3][8]; /* ring, which spot
    For example, [0][0] would be the top left (outer ring, 0th spot)
    [2][1] would be the inside ring, the second spot (top left to right)
    */

    /*
        Connections:
        0 -> +1, +3 (right, down)
        1 -> -1, +1 [] (left, right) (up or down ring if possible)
        2 -> -1, +2 (left, down)
        3 -> -3, +2 [] (up, down) (up or down ring if possible)
        4 -> -2, +3 [] (up, down) (up or down ring if possible)
        5 -> -2, +1 (up, right)
        6 -> -1, +1 [] (left, right) (up or down ring if possible)
        7 -> -3, -1 (up, left)


        1, 3, 4, or 6 can move rings
        -1 is always left
        +1 is always right
        +3 is always down, but +2 if 2/3
        -3 is always up, but -2 if 3/4

        moving rings keeps the 2nd dimension the same
    */
    public static final int EMPTY = 0;
    public static final int WHITE = 1;
    public static final int BLACK = 2;

    JLabel[][] boardLaels = new JLabel[7][7];
    /*
        0,0
                    3,0
                                6,0

            1,1
                    3,1
                            5,1

                2,2
                    3,2
                        4,2

        0,3
            1,3
                2,3
                        4,3
                            5,3
                                6,3

                2,4
                    3,4
                        4,4

            1,5
                    3,5
                            5,5

        0,6
                    3,6
                            6,6
    */
    int[][][] relationship = new int[3][8][2];
    /*

    */

    public visual() {
        super("minmax 9 men's morris");
        setLocation(300,200);
        setSize(750, 750);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Row, Col, HorGap, VerGap
        setLayout(new GridLayout(8,7,15,15));

        int number = 0;
        int ring = 0;
        int add =1;
        for (int i=0; i<boardLaels[0].length; i++) { // x axis
            for (int j=0; j<boardLaels.length; j++) { // y axis
                // boardLabels[j][i]
                // 0-6, 1-5, 2-4, 3
                if (j%6==0) {
                    if (i%3==0) {
                        boardLaels[j][i] = new JLabel(".");
                        relationship[ring][number] = new int[] {j, i};
                    }
                }
                else if (j%4==1) {
                    if (i%2==1) {
                        boardLaels[j][i] = new JLabel(".");
                        relationship[ring][number] = new int[] {j, i};
                    }
                }
                else if (j!=3) {
                    if (i==2 || i==3 || i==4) {
                        boardLaels[j][i] = new JLabel(".");
                        relationship[ring][number] = new int[] {j, i};
                    }
                }
                else if (i!=3) {
                    boardLaels[j][i] = new JLabel(".");
                    relationship[ring][number] = new int[] {j, i};
                }
                
                if (boardLaels[j][i]!=null) {
                    System.out.print("["+ring+", "+number+"] -> ");
                    if (i<3) {
                        number+=3;
                        if (number==6) {
                            number--;
                        }
                        else if (number==8) {
                            number=0;
                            ring++;
                        }
                        if (ring==3) {
                            ring=0;
                            number=1;
                        }
                    }
                    else if (i==3) {
                        ring+=add;
                        if (number==6 && ring==-1) {
                            ring = 2;
                            number = 2;
                        }
                        else if (ring==3) {
                            add =-1;
                            number=6;
                            ring--;
                        }
                    }
                    else {
                        number+=2;
                        if (number==6) {
                            number++;
                        }
                        else if (number==9) {
                            number =2;
                            ring--;
                        }
                    }
                    System.out.println("["+ring+", "+number+"]");
                }

                if (boardLaels[j][i]==null) {
                    boardLaels[j][i] = new JLabel("");
                }
                boardLaels[j][i].setFont(new Font("Ariel", Font.BOLD, 30));
                add(boardLaels[j][i]);
            }
        }
        setVisible(true);
    }

    public static void main(String[] args) {
        visual a = new visual();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}