/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_1;

/**
 *
 * @author gina0
 */
public class Games {
    ActionBeans action = new ActionBeans();
    
    public void passLine(){
        action.bet();
        action.beans.setDiceValue(action.roll());
        int diceValue = action.beans.getDiceValue();
        System.out.print("Your first roll points is: " + diceValue + "\n");
        if(diceValue == 7 || diceValue == 11){
            action.winTheGame();
        }else if(diceValue == 2 || diceValue == 3 || diceValue == 12){
            action.loseTheGame();
        } else{
            System.out.print("\nSecond roll..."); 
            boolean con = true;
            int secondDiceValue = 0;
            while(con == true){
                secondDiceValue =action.roll();
                System.out.println("Your second roll points is: " + secondDiceValue);
//                System.out.println("first roll : " + C_RapsBeans.getFirstRoll() + ", second Roll: " + javaBeans.getNewDiceValue());                
                if (secondDiceValue == diceValue){
                    action.winTheGame();
                    con = false;
                } else if(secondDiceValue == 7){
                    action.loseTheGame();
                    con = false;
                } else {
                    System.out.print("Again...");
                }
            }
        }

    }
    
    
    public void fieldBet(){
        action.bet();
        action.beans.setDiceValue(action.roll());
        
        int diceValue = action.beans.getDiceValue();
        System.out.print("Your points are: " + diceValue + "\n");
        
        switch(diceValue){
            case 3: case 4: case 9: case 10: case 11: {
                action.winTheGame();
                break;
            }
            case 2:{
                action.beans.setBetAmount(action.beans.getBetAmount() * 2);
                
                System.out.println("WOW! BET AMOUNT TIMES TWO!!!");
                action.winTheGame();
                break;                
            }
            case 12:{
                action.beans.setBetAmount(action.beans.getBetAmount() * 3);
                System.out.println("WOW! BET AMOUNT TIMES THREE!!!");
                action.winTheGame();
                break;            
            }
            default:{
                action.loseTheGame();
            }
        }
    }
    
    public void any7(){

        action.bet();
        action.beans.setDiceValue(action.roll());
        int diceValue = action.beans.getDiceValue();
        System.out.print("Your points is: " + diceValue + "\n");
        if(diceValue == 7){
            action.beans.setBetAmount(action.beans.getBetAmount()*4);
            System.out.println("WOW! BET AMOUNT TIMES FOUR!!!");
            action.winTheGame();
        } else{
            action.loseTheGame();
        }
    }    
    
}
