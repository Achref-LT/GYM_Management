package com.gym;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class postgresConnection {
    public static void main(String[] arg) {
        ArrayList<String> bdFill = new ArrayList<String>();
        bdFill.add("CREATE TABLE CARDIO_MEMBER"
                    +"(NAME TEXT NOT NULL,"
                    +"MACHINES TEXT NOT NULL,"
                    +"CIN TEXT PRIMARY KEY,"
                    +"ID SERIAL NOT NULL,"
                    +"AGE INT NOT NULL);");
        bdFill.add("CREATE TABLE PREMIUM_MEMBER"
                +"(NAME TEXT NOT NULL,"
                +"SHAKES TEXT NOT NULL,"
                +"COACH TEXT NOT NULL,"
                +"CIN TEXT PRIMARY KEY,"
                +"ID SERIAL NOT NULL,"
                +"AGE INT NOT NULL);");
        bdFill.add("CREATE TABLE BODYBUILDER"
                +"(NAME TEXT NOT NULL,"
                +"COACH TEXT NOT NULL,"
                +"CIN TEXT PRIMARY KEY,"
                +"ID SERIAL NOT NULL,"
                +"AGE INT  NOT NULL);");
        bdFill.add("CREATE TABLE SWIMMING_SESSION"
                +"(CIN TEXT PRIMARY KEY,"
                +"DURATION INT NOT NULL);");

        for (int i = 0; i < 4; i++) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gym", "postgres", "122113");
                System.out.println("Connection Established");

                Statement stmt = c.createStatement();
                stmt.executeUpdate(bdFill.get(i));
                c.close();
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error in tables creation");
                e.printStackTrace();
            }
        }

    }
}
