/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_1;

//import Assignments1.C_RapsBeans;
import java.util.Scanner;

/**
 *
 * @author gina0
 */
public class Menu {
    Games game = new Games();
    
    public void start(){        
        game.action.initialize();
        showMenu();
    }
        
    public void showMenu(){        
        System.out.println("Your current balance is $" + game.action.beans.getBankRoll() + ". Please choose the game you want to play: ");
        Scanner keyboard = new Scanner(System.in);
        String[] games = {"1. Pass Line", "2. File Bet", "3. Any 7", "4. End the game"};
        while(true){
            for(String s:games){
                System.out.print( s +"\t");
            }
            System.out.println();
            int userInput = keyboard.nextInt();
            switch(userInput){
                case 1:
                    passLineRules();
                    passLineOption();
                    System.exit(0);
                    break;
                case 2:
                    fieldBetRules();
                    fieldBetOption();
//                  System.exit(0);
                    break;
                case 3:
                    any7Rules();
                    any7Option();
//                  System.exit(0);
                    break;
                case 4:
                    System.out.println("See you soon. Goodbye...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("You entered the wrong number, please try again.");
                    break;
            }
        }           
    }  
    
    public void passLineOption(){
        System.out.println("Pass Line - please enter 'Y' to continue the Pass Line Game or '0' for the main menu. \r");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.next();        
        keyboard.nextLine();
        
        if(userInput.equalsIgnoreCase("Y")){
            game.passLine();
        }else if(userInput.equals("0")){
            showMenu();
        }else{
            System.out.println("You entered the wrong option, please try again!\n");
            passLineOption();
        }
        passLineOption();
    }
    
    public void fieldBetOption(){
        System.out.println("Field Bet - please enter 'Y' to continue the Field Bet Game or '0' for the main menu. \r");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.next();        
        keyboard.nextLine();
        
        if(userInput.equalsIgnoreCase("Y")){
            game.fieldBet();
        }else if(userInput.equals("0")){
            showMenu();
        }else{
            System.out.println("You entered the wrong option, please try again!\n");
            fieldBetOption();
        }
        fieldBetOption();
    }
    
    
    public void any7Option(){
        System.out.println("Any7 - please enter 'Y' to continue the Any7 Game or '0' for the main menu. \r");
        Scanner keyboard = new Scanner(System.in);
        String userInput = keyboard.next();        
        keyboard.nextLine();
        
        if(userInput.equalsIgnoreCase("Y")){
            game.any7();
        }else if(userInput.equals("0")){
            showMenu();
        }else{
            System.out.println("You entered the wrong option, please try again!\n");
            any7Option();
        }
        any7Option();
    } 
    
    public void passLineRules(){
        System.out.println( "Pass Line Game Rule: \nIn this game, if your first roll is 7 or 11, you win the amount of the bet. \n" 
                + "If it is 2, 3 or 12, you lose the bet.\n" 
                + "Any other number will become the \"point\" and you will keep rolling the dice UNTIL you either\n" 
                + "(a) roll the point total again, in which you will win the amount of the bet or \n" 
                + "(b) roll a total of 7, in which you will lose the amount of the bet.\n");
    }
    
    public void fieldBetRules(){
        System.out.println("Field Bet Game Rule: \nIn this game, if the total is 3, 4, 9, 10 or 11, then you win the amount of the bet.\n" 
                + "If the total is 2, then you win DOUBLE of the bet.\nIf the total is 12, then you win TRIPLE of the bet.\n" 
                + "For any other points, you lose the amount of the bet.\n");
    }
    
    public void any7Rules(){
        System.out.println("Any 7 Game Rule: \nIn this game, if the total is equal to 7, then you win QUADRUPLE of the bet.\n" 
        + "For any other points, you lose the amount of the bet.\n");
    }    
    
}
