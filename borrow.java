package com.gym;

import java.math.BigInteger;
import java.sql.*;

public class borrow {
    private int borrowId;
    private Date borrowDate;
    private String borrowerCin;
    private String imm;

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public static String borrowBicyle(int borrowId, Date borrowDate, String borrowerCin, String imm){
        Connection c=null;
        Statement stmt=null;
        ResultSet res=null;
        boolean dispo =false;
//        boolean bor=false;
        String ret="";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");


            stmt=c.createStatement();

            res = stmt.executeQuery("SELECT * FROM bicycle WHERE registration='"+imm+"';");
            res.next();
            dispo=res.getBoolean("disponibility");


            res = stmt.executeQuery("SELECT * FROM cardio_member WHERE cin='"+borrowerCin+"';");
            res.next();
            boolean bor = res.getBoolean("borr");


            if(dispo && !bor){
                    String sql = "INSERT INTO borrow VALUES ('"+borrowerCin+"', '"+borrowId+"','" +borrowDate+"', '"+imm+"');";
                    stmt.executeUpdate(sql);
                    sql = "UPDATE bicycle SET disponibility = false WHERE registration='"+imm+"';";
                    stmt.executeUpdate(sql);
                    sql = "UPDATE cardio_member SET borr = true WHERE cin='"+borrowerCin+"';";
                    stmt.executeUpdate(sql);
                    ret= "Bicycle borrowed successfully!";
                }
                else ret= "bicycle unavailable";


            stmt.close();
            c.commit();
            c.close();
            res.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error in adding the member");
            e.printStackTrace();
        }
        System.out.println(ret);
        return ret;
    }
    public static String returnBicycle(String imm,String borrowerCin){
        Connection c=null;
        Statement stmt=null;
        ResultSet res =null;
        boolean testBor;
        String ret="";

        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
            c.setAutoCommit(false);
            System.out.println("Connection Established !");

            stmt=c.createStatement();

            res=stmt.executeQuery("SELECT * FROM borrow WHERE borrowerCin='"+borrowerCin+"' AND imm='"+imm+"';");
            res.next();
            testBor = res.getString("imm").isEmpty();


            if (!testBor){
                String sql = "DELETE FROM borrow WHERE imm='"+imm+"';";
                stmt.executeUpdate(sql);
                sql = "UPDATE bicycle SET disponibility = true WHERE registration='"+imm+"';";
                stmt.executeUpdate(sql);
                sql = "UPDATE cardio_member SET borr = false WHERE cin='"+borrowerCin+"';";
                stmt.executeUpdate(sql);
                ret= "Bicycle returned successfully!";
            }
            else ret="Error, bicycle can not be returned";


        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("");
            e.printStackTrace();
        }
        System.out.println(ret);
        return ret;
    }

}
