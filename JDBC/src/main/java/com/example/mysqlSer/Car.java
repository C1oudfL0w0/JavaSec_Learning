package com.example.mysqlSer;

import java.io.*;

public class Car implements Serializable {

    private String name;
    public Car(){
        this.name ="car";
    }

    public static void main(String[] args) throws IOException, FileNotFoundException {
        Car car=new Car();
        FileOutputStream fos =new FileOutputStream("output");
        ObjectOutputStream oos =new ObjectOutputStream(fos);
        oos.writeObject(car);
        oos.close();
    }
}