/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shanaconsulting.virtual.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class H2DatabaseInstaller extends ModuleInstall {

   
    static Connection connection;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());
    static private String app_data_home;
private Boolean dbinit;

    

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getApp_data_home() {
        return app_data_home;
    }

    public void setApp_data_home(String app_data_home) {
        this.app_data_home = app_data_home;
    }

    public Boolean isDbinit() {
        return dbinit;
    }

    public void setDbinit(Boolean dbinit) {
        this.dbinit = dbinit;
    }

    @Override
    public void restored() {
        if(initH2DB() == 0){
            dbinit=true;
        }else{
            dbinit=false;
        }
        
    }

    private Integer initH2DB() {
        final String sql_create_host_type = "create table  host_type(id int not null primary  key auto_increment,type_name char(32) not null unique);";
        final String sql_create_host = "create table host_name(name char(16) not null unique,id int not null primary key auto_increment,type_id int references host_type(id),parent_id int references host_name(id));";
        final String sql_create_lpar_stat = "create table lpar_stat(host_id int not null references host_name(id),proc_unit_used double not null,curr_procs double not null, "
                + "curr_mem int not null,curr_proc_units double not null,curr_uncap_weight int not null,curr_shared_proc_pool_name char(32) not null,"
                + "date_time timestamp not null,constraint uniq_lpar_stat primary key(date_time,host_id));";
        final String sql_create_pool = "create table pool_stat(host_id int not null references host_name(id),proc_units_used double not null,configurable_pool_proc_units double not null,curr_avail_pool_proc_units double not null,"
                + "borrowed_pool_proc_units double not null,date_time timestamp not null,constraint pk_pool_stat primary key(host_id,date_time));";
        final String sql_create_procpool_stat = "create table procpool_stat(host_id int not null references host_name(id),shared_proc_pool_name char(32) not null,proc_unit_used double not null,sys_time timestamp not null,constraint pk_procpool_stat primary key(host_id,sys_time,shared_proc_pool_name));";
        java.sql.Statement statement = null;
        final String sql_create_os = "create table os(id int not null primary  key,name char(16) not null,release char(16) not null,patch_level char(16),constraint pk_os unique(name,release,patch_level,id);";

        app_data_home = System.getProperty("user.home") + System.getProperty("file.separator") + "ptools" + System.getProperty("file.separator")+ "database";
        
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:" + app_data_home + System.getProperty("file.separator") + "database_h2", "sa", "sa");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage());
            return 1;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getLocalizedMessage());
            return 1;
        }

        try {
            statement.executeQuery("select * from host_name");
            List<String> v = new LinkedList<String>();
            v.add(sql_create_host_type);
            v.add(sql_create_host);
            v.add(sql_create_lpar_stat);
            v.add(sql_create_pool);
            v.add(sql_create_procpool_stat);

            for (String sql : v) {
                try {
                    statement.execute(sql);
                } catch (SQLException e) {
                }
            }
            statement.execute("insert into host_type (type_name) values('lpar')");
            statement.execute("insert into host_type (type_name) values('power')");
            statement.execute("insert into host_type (type_name) values('vm')");
            statement.execute("insert into host_type (type_name) values('standalone')");
            statement.execute("insert into host_type (type_name) values('hmc')");
            statement.execute("insert into host_type (type_name) values('lparvios')");
            statement.execute("insert into host_type (type_name) values('vmware')");
            statement.execute("insert into host_type (type_name) values('kvm')");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, e.getLocalizedMessage());
            return 1;
        }

        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException ex) {
                LOGGER.log(Level.WARNING, ex.getLocalizedMessage());
            }
        }
        
         return 0;
    }
    

    public Statement getStatement() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return statement;
    }
    
    public static  Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:h2:" + app_data_home + System.getProperty("file.separator") + "database_h2", "sa", "sa");
                
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
         return connection;
    }
}
