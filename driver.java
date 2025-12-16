// package AI1.NineMensMorrisSolver;
public class driver {
    public static void main(String[] args) {
        visual screen = new visual();
        screen.setVisible(true);
        if (args.length>0) {
            for (int i=0; i<screen.board.length; i++) {
                for (int j=0; j<screen.board[0].length; j++) {
                    int num = i*8+j;
                    int player = Integer.parseInt(args[0].substring(num,num+1));
                    screen.placePiece(player, i, j);
                }
            }
        }
    }
}