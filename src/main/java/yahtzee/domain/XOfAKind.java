/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahtzee.domain;

import java.util.ArrayList;

/**
 *
 * @author rpulkka
 */
public class XOfAKind implements Combination {

    private ArrayList<Die> dice;
    private String type;
    private boolean isAvailable;
    private boolean alreadyScored;

    public XOfAKind(ArrayList<Die> dice, String type) {
        this.dice = dice;
        this.type = type;
        this.isAvailable = false;
        this.alreadyScored = false;
    }

    public int score() {
        this.isAvailable = false;
        
        ChosenDiceList correctDice = new ChosenDiceList();
        dice = correctDice.chosenList(dice);

        DataList datalist = new DataList(dice);
        ArrayList<Integer> value;
        if (type.equals("three")) {
            value = datalist.list(3);
            if (value.isEmpty() == false) {
                return value.get(0) * 3;
            }else{
                return 0;
            }
        } else if (type.equals("four")) {
            value = datalist.list(4);
            if (value.isEmpty() == false) {
                return value.get(0) * 4;
            }else{
                return 0;
            }
        }

        return 0;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setIsAvailable(boolean b) {
        this.isAvailable = b;
    }

    @Override
    public boolean getAlreadyScored() {
        return this.alreadyScored;
    }

    @Override
    public void setAlreadyScored(boolean b) {
        this.alreadyScored = b;
    }
}
