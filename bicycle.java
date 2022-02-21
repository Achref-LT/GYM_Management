package com.gym;

import java.sql.*;

public class bicycle {
    private String registration;
    public static boolean disponibility;

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getRegistration() {
        return registration;
    }

    public static void addBicycle(String imm){
        Statement stmt = null;
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt=c.createStatement();
            String sql="INSERT INTO bicycle (registration) VALUES ('"+imm+"');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("Error in adding the member");
            e.printStackTrace();
        }
    }
}
