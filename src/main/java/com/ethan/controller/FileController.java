package com.ethan.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ASUS on 2020/1/13.
 */
public class FileController {

    /**
     *  获取一个文件夹下的所有文件全路径
     */
    public static void getAllFileName(String path, ArrayList<String> listFileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null) {
            String[] completNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                completNames[i] = path + names[i];
            }
            listFileName.addAll(Arrays.asList(completNames));
        }
        for (File a : files) {
            if (a.isDirectory()) {//如果文件夹下有子文件夹，获取子文件夹下的所有文件全路径。
                getAllFileName(a.getAbsolutePath() + "\\", listFileName);
            }
        }
    }


    public static void main(String[] args) {
        int png = 0;
        int jpg = 0;
        int total = 0;

        ArrayList<String> listFileName = new ArrayList<>();
        getAllFileName("G:\\U盘文件\\工作区", listFileName);
        for (String name : listFileName) {
            if (name.contains(".png")) {
                ++png;
                System.out.println(name);
            } else if (name.contains(".jpg")) {
                ++jpg;
                System.out.println(name);
            }
        }
        total = png + jpg;
        System.out.println("png:" + png + " ==== jpg:" + jpg);
        System.out.println("total:" + total);
    }
}
