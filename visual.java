// package AI1.NineMensMorrisSolver; // I absolutely hate the package declarations

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
    int[][] moves = new int[][] {
        {1, 3, 0},
        {-1, 1, 1},
        {-1, 2, 0},
        {-3, 2, 1},
        {-2, 3, 1},
        {-2, 1, 0},
        {-1, 1, 1},
        {-3, -1, 0}
    };
    public static final int EMPTY = 0;
    public static final int WHITE = 1;
    public static final int BLACK = 2;
    public static final String WHITEPIECE = "O";
    public static final String BLACKPIECE = "*";
    public static boolean placingPhase = true;
    public static boolean normalPhase = false;
    public static boolean[] flying = new boolean[] {false, false}; // [White, Black]

    JButton[][] boardLabels = new JButton[7][7];
    /*
        0,0            3,0            6,0

            1,1        3,1        5,1

                2,2    3,2    4,2

        0,3 1,3 2,3           4,3 5,3 6,3

                2,4    3,4    4,4

            1,5        3,5        5,5

        0,6            3,6            6,6
    */
    int[][][] relationship = new int[3][8][2];

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
        for (int i=0; i<boardLabels[0].length; i++) { // x axis
            for (int j=0; j<boardLabels.length; j++) { // y axis
                // boardLabels[j][i]
                // 0-6, 1-5, 2-4, 3
                if (j%6==0) {
                    if (i%3==0) {
                        boardLabels[j][i] = new JButton(".");
                        relationship[ring][number] = new int[] {j, i};
                        boardLabels[j][i].addActionListener(this);
                    }
                }
                else if (j%4==1) {
                    if (i%2==1) {
                        boardLabels[j][i] = new JButton(".");
                        relationship[ring][number] = new int[] {j, i};
                        boardLabels[j][i].addActionListener(this);
                    }
                }
                else if (j!=3) {
                    if (i==2 || i==3 || i==4) {
                        boardLabels[j][i] = new JButton(".");
                        relationship[ring][number] = new int[] {j, i};
                        boardLabels[j][i].addActionListener(this);
                    }
                }
                else if (i!=3) {
                    boardLabels[j][i] = new JButton(".");
                    relationship[ring][number] = new int[] {j, i};
                    boardLabels[j][i].addActionListener(this);
                }
                
                if (boardLabels[j][i]!=null) {
                    // System.out.print("["+ring+", "+number+"] -> ");
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
                    // System.out.println("["+ring+", "+number+"]");
                }

                if (boardLabels[j][i]==null) {
                    boardLabels[j][i] = new JButton("");
                }
                boardLabels[j][i].setFont(new Font("Ariel", Font.BOLD, 35));
                boardLabels[j][i].setBorder(null);
                boardLabels[j][i].setContentAreaFilled(false);
                add(boardLabels[j][i]);
            }
        }
        setVisible(true);
    }

    public boolean isValidMove(int[] start, int[] end, int player) { // Checks if a possible move is valid or not
        try {
            boolean t = board[start[0]][start[1]]==board[end[0]][end[1]];
        }
        catch(Exception e) {
            return false;
        }
        if (flying[player-1] || placingPhase) {
            return board[end[0]][end[1]]==EMPTY;
        }
        else {
            int[] options = moves[start[1]];
            options[0]+=start[1];
            options[1]+=start[1];
            if (start[0]==end[0] && (options[0]==end[1] || options[1]==end[1])) {// Possible moves on same ring
                // If they're on the same ring, and the end state is one of the two possible moves on the same ring
                return board[end[0]][end[1]]==EMPTY;
            }
            return (board[end[0]][end[1]]==EMPTY && options[2]==1 && start[1]==end[1] && Math.abs(start[0]-end[0])==1); // Possible move between rings
            // If the end tile is EMPTY, and we can move between rings, and start and end are in the same position between rings, and the rings are only 1 apart
        }
    }

    public ArrayList<int[]> possibleMoves(int player) { // Returns list of all possible moves the chosen player may make
        ArrayList<int[]> moves = new ArrayList<int[]>(); // [start ring, start position, end ring, end position]
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j]!=player) {
                    continue;
                }
                int[] start = new int[] {i,j};
                for (int k=-3; k<3; k++) {
                    int[] end = new int[] {start[0], start[1]+k};
                    if (isValidMove(start, end, player)) {
                        moves.add(end);
                    }
                }
                int[] end = new int[] {start[0]-1, start[1]};
                if (isValidMove(start, end, player)) {
                    moves.add(end);
                }
                end = new int[] {start[0]+1, start[1]};
                if (isValidMove(start, end, player)) {
                    moves.add(end);
                }
            }
        }
        return moves;
    }

    public int countPieces(int player) { // Counts the amount of pieces remaining for a player
        int pieces = 0;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board[i].length; j++) {
                if (board[i][j]==player) {
                    pieces++;
                }
            }
        }
        return pieces;
    }

    public int countMillis(int player) { // Counts the amount of Millis the player currently has on the board
        int Millis = 0;
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<2; j++) {
                if (board[i][5*j]==board[i][5*j+1] && board[i][5*j+1]==board[i][5*j+2] && board[i][5*j+0]==player) { // Top and Bottom rows
                    Millis++;
                }
                if (board[i][2*j]==board[i][j+3] && board[i][j+3]==board[i][2*j+5] && board[i][2*j]==player){ // left and right columns
                    Millis++;
                }
                if (board[0][5*j+1]==board[1][1] && board[1][5*j+1]==board[2][1] && board[0][5*j+1]==player) { // Top and Bottom between rings
                    Millis++;
                }
                if (board[0][3+j]==board[1][3+j] && board[1][3+j]==board[2][3+j] && board[0][3+j]==player) { // Left and Right between rings
                    Millis++;
                }
            }
        }
        return Millis;
    }

    public int evaluateBoard(int player) {
        int total = (countPieces(player)-countPieces((player%2)+1)) * 10; // Count how many pieces you have left compared to your opponent. Weight of 10
        total += (countMillis(player)-countMillis((player%2)+1))*12; // Count how many Millis you have on the board compared to your opponent. Weight of 12

        return -1;
    }

    public static void main(String[] args) {
        visual a = new visual();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Pushed");
    }
}