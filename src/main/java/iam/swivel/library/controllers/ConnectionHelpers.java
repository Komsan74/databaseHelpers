package iam.swivel.library.controllers;

import java.sql.*;

import static java.sql.DriverManager.*;

public class ConnectionHelpers {
    private String dbDriver;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbURI;

    private Connection conn;
    private Statement stmt;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public void setDbDriver(String dbDriver) {
        this.dbDriver = dbDriver;
    }

    public String getDbURI() {
        return dbURI;
    }

    public void setDbURI(String dbURI) {
        this.dbURI = dbURI;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    /* คอนสตรัคเตอร์ */
    public ConnectionHelpers() {
    }

    /* เชื่อมต่อฐานข้อมูล */
    public void open(){
        try {
            Class.forName(getDbDriver());
            conn = getConnection(getDbURI(), getDbUser(), getDbPassword());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /* ปิดการเชื่อมต่อฐานข้อมูล */
    public void close(){
        try{
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* คิวรี่ข้อมูลแบบนำผลลัพท์มาแสดง */
    public ResultSet getResult(String Query){
        ResultSet rs;
        try {
            open();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(Query);
        } catch (Exception e) {
            rs = null;
        }
        return rs;
    }

    /* คิวรี่ข้อมูลแบบไม่ต้องการผลลัพท์ */
    public boolean execute(String Query){
        boolean rs;
        try {
            open();
            stmt = conn.createStatement();
            stmt.execute(Query);
            rs = true;
        } catch (Exception e) {
            rs = false;
        }
        return rs;
    }
}
