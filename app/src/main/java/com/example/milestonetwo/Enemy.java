package com.example.milestonetwo;

import android.widget.ImageView;

import java.util.ArrayList;

public class Enemy {
    private ArrayList<Integer> pos = new ArrayList<>();
    private ImageView[] arr = new ImageView[10];
    public ArrayList<Integer> getList() {
        return pos;
    }
    public ImageView[] getArray() {
        return arr;
    }
    public void setArr(ImageView x, int i) {

    }
    public ImageView getArr(int i) {
        return arr[i];
    }
    public void update(ArrayList<Integer> position) {
        for (int i = 0; i < position.size(); i++) {
            position.set(i, position.get(i) + 1);
        }
    }
    public void setVisible(ImageView[] arr, ArrayList<Integer> position) {

    }

    public void setXval(int i, ImageView ele) {
        if (i == 0) {
            ele.setX(193);
        } else if (i >= 1 && i <= 4) {
            ele.setX(391);
        } else if (i == 5) {
            ele.setX(585);
        } else if (i >= 6 && i <= 9) {
            ele.setX(783);
        } else if (i == 10) {
            ele.setX(971);
        } else if (i == 11) {
            ele.setX(1171);
        } else if (i >= 12 && i <= 15) {
            ele.setX(1363);
        } else if (i == 16) {
            ele.setX(1549);
        } else if (i >= 17 && i <= 20) {
            ele.setX(1739);
        } else if (i == 21) {
            ele.setX(1941);
        } else if (i == 22 && i == 23) {
            ele.setX(2143);
        }

    }

    public void setYval(int i, ImageView ele) {
        if ((i >= 0 && i <= 1) || (i >= 9 && i <= 12) || (i >= 20 && i <= 22)) {
            ele.setY(267);
        } else if (i == 2 || i == 8 || i == 13 || i == 19 || i == 23) {
            ele.setY(467);
        } else if (i == 3 || i == 7 || i == 14 || i == 18) {
            ele.setY(621);
        } else if ((i >= 4 && i <= 6) || (i >= 15 && i <= 17)) {
            ele.setY(797);
        }
    }

    public static String enemyCombat (int enemiesForCombat) {
        if (enemiesForCombat > 0) {
            return "enemy configured";
        } else {
            return "enemies not created";
        }
        //return "Error";
    }

    public static int healthAttack(int enemyHealth) {
        return enemyHealth - 30;
    }

    public static boolean depleteHealth (int enemyHealth) {
        return enemyHealth == 0;

    }

}
