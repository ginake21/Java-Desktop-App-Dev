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
public class Driver {
    public void perform(){
        Menu menu = new Menu();
        menu.start();
    }
    
    public static void main(String[] args){
        Driver run = new Driver();
        run.perform();
    }
}
