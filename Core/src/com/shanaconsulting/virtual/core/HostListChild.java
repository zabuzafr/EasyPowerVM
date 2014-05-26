/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shanaconsulting.virtual.core;

/**
 *
 * @author pierre jacques
 */
class HostListChild {

    private String parentName;
    private Integer id;
    private String name;
    public HostListChild(String name,Integer id,String parent) {
        this.parentName = parent;
        this.id=id;
        this.name=name;
    }
public HostListChild(){}
    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
