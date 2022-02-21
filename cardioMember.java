package com.gym;

import java.sql.*;
import java.util.ArrayList;

public class cardioMember extends member {
    private String machines;
    private int nbrStrikes ;

    cardioMember(String name,String cin,int age,int id,String machines){
        super.setName(name);
        super.setCin(cin);
        super.setAge(age);
        super.setId(id);
        this.machines=machines;
    }

    public int getNbrStrikes() {
        return nbrStrikes;
    }

    public void setNbrStrikes(int nbrStrikes) {
        this.nbrStrikes = nbrStrikes;
    }

    public String getMachines() {
        return machines;
    }

    public void setMachines(String machines) {
        this.machines = machines;
    }

    public static String addMember(String name, String cin, int age, int id, String machines) {
        Connection c = null;
        Statement stmt = null;
        String nameAdh = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt = c.createStatement();
            String sql = "INSERT INTO cardio_member (name,machines,cin,id,age) " +
                    "VALUES ('"+name+"','"+machines+"','"+cin+"','"+id+"','"+age+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();

            return "Added successfully";
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in adding the member");
            e.printStackTrace();

            return "Error";
        }
    }
    public static String removeMember(int id) {
        Connection c = null;
        Statement stmt = null;
        String nameAdh = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt = c.createStatement();

            ResultSet res = stmt.executeQuery("SELECT NAME FROM cardio_member WHERE id=" + id + ";");
            res.next();
            nameAdh = res.getString("NAME");


            String sql = "DELETE from cardio_member where id="+id+";";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
            res.close();
            System.out.println(nameAdh+" has been deleted successfully");

            return "operation successfully done ";
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in deleting the member");
            e.printStackTrace();

            return "Error";
        }
    }

    public static String memberControl(){
        Statement stmt=null;
        Connection c=null;
        int nbrTest;
        ArrayList<String> memRem = new ArrayList<String>();
        StringBuilder ret=new StringBuilder("");
        int i;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt = c.createStatement();

            ResultSet res = stmt.executeQuery("SELECT COUNT(*) AS num FROM cardio_member WHERE strikes>=3;");
            res.next();
            nbrTest=res.getInt("num");

            if(nbrTest>0){
                res = stmt.executeQuery("SELECT name FROM cardio_member WHERE strikes>=3;");
                while(res.next()){
                    memRem.add(res.getString("name"));
                }
                for(i=0;i< memRem.size();i++){
                    ret.append(memRem.get(i)).append(", ");
                }

            }
            else ret.append("no one had been removed");



            String sql = "DELETE FROM cardio_member WHERE strikes>=3;";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
            res.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in deleting the member(s)");
            e.printStackTrace();
        }
        System.out.print(ret.toString()+" had been removed");

       return ret.toString();
    }
}