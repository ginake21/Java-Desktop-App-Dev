/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_1;


import java.util.Random;
import java.util.Scanner;
//import java.util.logging.Handler;
/**
 *
 * @author gina0
 */
public class ActionBeans implements ActionInterface {
    JavaBeans beans = new JavaBeans();
    
    @Override
    public void initialize(){        
        System.out.println("Welcome to C Raps. Please add money into your account. How much you want to save? $: ");
        Scanner keyboard = new Scanner(System.in);
        if(keyboard.hasNextInt()){
            beans.setBankRoll(keyboard.nextInt());
        }
        else{
            System.out.println("Sorry, you entered the wrong input, please try again");
            initialize();
        }
        
    }
    
    @Override
    public int roll(){
        System.out.print("The dice are rolling...");
        Random rand1 = new Random();
        Random rand2 = new Random();
        beans.setDiceValue(rand1.nextInt(JavaBeans.SIDEOFDICE) + 1 + rand2.nextInt(JavaBeans.SIDEOFDICE)+ 1);   
        return beans.getDiceValue();    
    }
    
    @Override
    public void bet(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the amount of money you wanna bet for the game: \r");
        if(keyboard.hasNextInt()){
            beans.setBetAmount(keyboard.nextInt());
            if((beans.getBankRoll() - beans.getBetAmount()) >=0){
                System.out.println("\nYour current balnace is $" + beans.getBankRoll() + " dollars. You put $" + beans.getBetAmount() 
                        + " on the bet. Wish you good luck :)");    
            }
            else{
                System.out.println("Sorry, your balance is not enough. You only have $" + beans.getBankRoll ()
                        + " dollars in your account. Please try again.\n");
                bet();            
            }
        } else{
            System.out.println("Your input is invalid. Please try again.\n");
            bet();
        }
    }
    
    @Override
    public void winTheGame(){
        beans.setBankRoll(beans.getBankRoll() + beans.getBetAmount());
        System.out.println("Congrats!! You won the game! Now your balance is $" + (beans.getBankRoll()) + " dollars. \n");
        beans.setBetAmount(0);   
    }
    
    @Override
    public void loseTheGame(){
        beans.setBankRoll(beans.getBankRoll() - beans.getBetAmount());
        System.out.println("Sorry, you lost the game. Now your balance is $" + beans.getBankRoll() + "\n");
        beans.setBetAmount(0);
        if(beans.getBankRoll()<=0){
            System.out.println("Game Over! You have no money left...GoodBye...\n");
            System.exit(0);
        }    
    }
}
