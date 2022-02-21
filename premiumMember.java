package com.gym;

import java.sql.*;

public class premiumMember extends member{

    private String coach;
    private String shakes;

    premiumMember(String name,String cin,int age,int id,String coach,String shakes){
        super.setName(name);
        super.setCin(cin);
        super.setAge(age);
        super.setId(id);
        this.coach=coach;
        this.shakes=shakes;
    }


    public String getCoach() {
        return coach;
    }
    public String getShakes() {
        return shakes;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }
    public void setShakes(String shakes) {
        this.shakes = shakes;
    }

    public void addMember(String name,String cin,int age,int id,String coach,String shakes) {
        Connection c = null;
        Statement stmt = null;
        String nameAdh = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt = c.createStatement();
            String sql = "INSERT INTO premium_member (name,shakes,coach,cin,id,age) " +
                    "VALUES ('"+name+"','"+shakes+"','"+coach+"','"+cin+"','"+id+"','"+age+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in adding the member");
            e.printStackTrace();
        }
    }

    public static void removeMember(int id) {
        Connection c = null;
        Statement stmt = null;
        String nameAdh = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt = c.createStatement();

            ResultSet res = stmt.executeQuery("SELECT NAME FROM premium_member WHERE id=" + id + ";");
            res.next();
            nameAdh = res.getString("NAME");


            String sql = "DELETE from premium_member where id="+id+";";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
            res.close();
            System.out.println(nameAdh+" has been deleted successfully");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in deleting the member");
            e.printStackTrace();
        }
    }
}