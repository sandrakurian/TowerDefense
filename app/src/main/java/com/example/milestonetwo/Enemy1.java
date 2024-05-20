package com.example.milestonetwo;


import android.widget.ImageView;

import java.util.ArrayList;

public class Enemy1 extends Enemy {
    private ArrayList<Integer> position = new ArrayList<>();
    private ImageView a1;
    private ImageView a2;
    private ImageView a3;

    private ImageView[] arr = {a1, a2, a3};

    @Override
    public ArrayList<Integer> getList() {
        return position;
    }

    @Override
    public ImageView[] getArray() {
        return arr;
    }
    @Override
    public void setArr(ImageView x, int i) {
        arr[i] = x;
    }
    @Override
    public ImageView getArr(int i) {
        return arr[i];
    }
    @Override
    public void update(ArrayList<Integer> position) {
        super.update(position);
    }


    @Override
    public void setVisible(ImageView[] arr, ArrayList<Integer> position) {
        super.setVisible(arr, position);
    }

    @Override
    public void setXval(int i, ImageView ele) {
        super.setXval(i, ele);
    }
    @Override
    public void setYval(int i, ImageView ele) {
        super.setYval(i, ele);
    }

}
