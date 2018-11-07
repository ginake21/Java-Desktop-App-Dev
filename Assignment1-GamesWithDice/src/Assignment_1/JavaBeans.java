/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_1;
import java.io.Serializable;
/**
 *
 * @author gina0
 */
public class JavaBeans implements Serializable{
    public static final int SIDEOFDICE = 6;
    private int bankRoll;
    private int diceValue;
    private int betAmount;

    public int getBankRoll() {
        return bankRoll;
    }

    public void setBankRoll(int bankRoll) {
        this.bankRoll = bankRoll;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    @Override
    public String toString() {
        return "JavaBeans{" + "bankRoll=" + bankRoll + ", diceValue=" + diceValue + ", betAmount=" + betAmount + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.bankRoll;
        hash = 59 * hash + this.diceValue;
        hash = 59 * hash + this.betAmount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JavaBeans other = (JavaBeans) obj;
        if (this.bankRoll != other.bankRoll) {
            return false;
        }
        if (this.diceValue != other.diceValue) {
            return false;
        }
        if (this.betAmount != other.betAmount) {
            return false;
        }
        return true;
    }
    
    // still need serialize
    
}
