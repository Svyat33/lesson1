import modules.Fields;
import modules.Player;
import modules.UserStep;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player('+',"Первый игрок",1);
        Player player2 = new Player('0',"Второй игрок",2);

        Fields newgame = new Fields("Играет "+player1.getName()+" против "+player2.getName());
        newgame.print();

        while (!newgame.check(player1, player2))   {
            newgame.print();

            UserStep s = player1.getStep(newgame,player2);
            newgame.setXY(s);
            newgame.print();
            if (!newgame.check(player1, player2)){
                //Первый не выиграл еще
                s = player2.getStep(newgame,player1);
                newgame.setXY(s);
                newgame.print();
                if (newgame.check(player1, player2)){
                    newgame.printGameDone();
                }
            } else {
                newgame.printGameDone();
            }
            System.out.println("-------------------------------------------------------");
        }

    }
}
