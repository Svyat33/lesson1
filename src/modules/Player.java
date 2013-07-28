package modules;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: kss
 * Date: 25.07.13
 * Time: 0:12
 * To change this template use File | Settings | File Templates.
 */
public class Player {
    private final String name;
    private final int playerType; //1 - user 2-machine
    public final char mySymbol;


    public int getPlayerType() {
        return playerType;
    }


    public Player(char s, String nn, int mtype) {
        String name1;
        int playerType1;
        mySymbol = s;
        Scanner r = new Scanner(System.in);

        System.out.print(nn+" человек? (y\\n):");
        try {
            String yn = r.next();
            System.out.print("["+yn.toLowerCase().substring(0,1)+ "]\n\n");

            if (yn.substring(0,1).equalsIgnoreCase("y")||yn.equalsIgnoreCase("yes")){
                playerType1 = 1;
                System.out.print("Введите имя игрока: ");
                name1 = r.next();
            }   else {
                playerType1 = 2;
                name1 = ("Machine");
            }
        } catch(Exception e){
            System.out.print("Хех... систему не обманешь.");
            playerType1 = mtype;
            name1 = nn;
        }
        name = name1;
        playerType = playerType1;
    }

    public String getName() {
        return name;
    }


    public UserStep getStep(Fields currFields, Player opponent){
        if (this.getPlayerType()==1){
              System.out.print("Введите строку:");
            int a = getInteger();
            System.out.print("Введите колонку:");
            int b = getInteger();
            if (currFields.checkEmptyField(a,b)){
                return new UserStep(a,b,this.mySymbol);
            }   else {
                System.out.print("Ошибка. Повтор хода.\n\n");
                return this.getStep(currFields,opponent);
            }


        }  else {
             return getMachineStep(currFields,opponent);
        }

    }


    private UserStep getMachineStep(Fields currFields,Player opponent) {
        float[][] weight = new float[currFields.SIZE][currFields.SIZE];

        //default weight
     /*   for (int i=0;i<currFields.SIZE;i++){
            for(int j=0;j<currFields.SIZE;j++){
                weight[i][j]=0;
            }
        }
        for (int i=0;i<currFields.SIZE;i++){
            weight[i][(currFields.SIZE-i-1)]+=0.25;
        }
        for (int i=0;i<currFields.SIZE;i++){
            weight[i][i]+=0.25;
        }
        */
        weight = new float[][]{
                {(float) 0.25, 0, (float) 0.25},
                { 0,  (float) 0.5,  0},
                { (float) 0.25, 0,  (float) 0.25}
        };


//1. gorizont
        boolean clearLineWeight;
        clearLineWeight = false;
        int rowe=2;
        for (int i=0;i<currFields.SIZE;i++){
            rowe=2;
            clearLineWeight = false;
            for(int j=0;j<currFields.SIZE;j++){
                if (currFields.checkOwner(i,j,opponent.mySymbol)) {
                    rowe*=3;
                }

                if (currFields.checkOwner(i,j,this.mySymbol)) {
                    clearLineWeight = true;
                    break;
                }
            }
            if (!clearLineWeight && rowe>2){
                for(int j=0; j<currFields.SIZE;j++){
                    if (currFields.checkEmptyField(i,j)){
                        weight[i][j]+=rowe;
                    }
                }
            }

        }

//2. stolbci
        for (int i=0;i<currFields.SIZE;i++){
            rowe=2;
            clearLineWeight = false;
            for(int j=0;j<currFields.SIZE;j++){
                if (currFields.checkOwner(j,i,opponent.mySymbol)) {
                    rowe*=3;
                }

                if (currFields.checkOwner(j,i,this.mySymbol)) {
                    clearLineWeight = true;       break;
                }
            }

            if (!clearLineWeight && rowe > 2){
                for(int j=0;j<currFields.SIZE;j++){
                    if (currFields.checkEmptyField(j,i)){
                        weight[j][i]+=rowe;
                    }
                }
            }

        }

        clearLineWeight = false;
        rowe=2;
        for (int i=0;i<currFields.SIZE;i++){
            if (currFields.checkOwner(i,i,opponent.mySymbol)) {
                rowe*=3;
            }
            if (currFields.checkOwner(i, i, this.mySymbol)) {
                clearLineWeight = true;
                break;
            }
        }
        if (!clearLineWeight && rowe > 2)    {
            for (int i=0;i<currFields.SIZE;i++){
                if (currFields.checkEmptyField(i,i)){
                    weight[i][i]+=rowe;
                }
            }
        }

        rowe=2;
        clearLineWeight = false;
        for (int i=0;i<currFields.SIZE;i++){
            if (currFields.checkOwner(i,(currFields.SIZE-i-1),opponent.mySymbol)) {
                rowe*=3;
            }

            if (currFields.checkOwner(i,(currFields.SIZE-i-1),this.mySymbol)) {
                clearLineWeight = true;
                break;
            }
        }
        if (!clearLineWeight && rowe > 2)    {
            for (int i=0;i<currFields.SIZE;i++){
                if (currFields.checkEmptyField(i,(currFields.SIZE-i-1))){
                    weight[i][ (currFields.SIZE-i-1)]+=rowe;
                }
            }
        }

        float maxval=-1;
        int maxI=0,maxJ=0;
        for (int i=0;i<currFields.SIZE;i++){
            for(int j=0;j<currFields.SIZE;j++){
                if (weight[i][j]>maxval){
                    maxval = weight[i][j];
                    maxI=i;maxJ=j;
                }
            }
        }
        return new UserStep(maxI,maxJ,this.mySymbol);
    }


    private static int getInteger(){
        Scanner readHod  = new Scanner(System.in);
        int x=0;
        try{
            x = readHod.nextInt();
        }   catch (Exception e){
            System.out.print("Ошибка! Введите число: ");
            return getInteger();
        }
        return x;
    }

    private void printWeight(float[][] weightMas){
        System.out.println("Weight array");
        for (int i = 0 ; i < weightMas.length ; i++){
            for (int j = 0 ; j < weightMas.length; j++) {
                System.out.print(i+":"+j+"["+weightMas[i][j]+"]\t\t");
            }
            System.out.println();
        }
    }

}
