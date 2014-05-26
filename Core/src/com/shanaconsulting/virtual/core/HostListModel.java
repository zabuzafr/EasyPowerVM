/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shanaconsulting.virtual.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.openide.util.Exceptions;

/**
 *
 * @author pierre jacques
 */
public class HostListModel {
    
    private String title;
    private List<HostListChild> children;

    public List<HostListChild> getChildren() {
        return children;
    }

    public void setChildren(List<HostListChild> children) {
        this.children = children;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public HostListModel(){
        String query="select host_name.name,host_type.type_name  from host_name,host_type where host_name.type_id=host_type.id;";
        children = new LinkedList<>();
         try {
            Connection c = H2DatabaseInstaller.getConnection();
            if(c == null){
                return;
            }
            Statement s = c.createStatement();
            ResultSet set= s.executeQuery(query);
            while(set.next()){
                HostListChild child = new HostListChild(set.getString(1),set.getInt(2),set.getString(3));
                children.add(child);
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }  
    }
}
