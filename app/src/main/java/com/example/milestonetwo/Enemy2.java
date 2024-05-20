package com.example.milestonetwo;

import android.widget.ImageView;

import java.util.ArrayList;

public class Enemy2 extends Enemy {
    private ArrayList<Integer> position = new ArrayList<>();
    private ImageView b1;
    private ImageView b2;
    private ImageView b3;

    private ImageView[] arr = {b1, b2, b3};

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
    public ImageView getArr(int i) {
        return arr[i];
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
