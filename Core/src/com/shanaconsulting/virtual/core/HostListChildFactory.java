/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shanaconsulting.virtual.core;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author pierre jacques
 */
public class HostListChildFactory  extends  ChildFactory<HostListModel>{

    private List<HostListModel> model;
    
    
    public HostListChildFactory(List<HostListModel> model){
        this.model = model;
    }
    
    @Override
    protected boolean createKeys(List<HostListModel> toPopulate) {
        return toPopulate.addAll(model);
    }
    
    @Override
    protected Node createNodeForKey(HostListModel model){
        return new HostNode(model);
    } 
    
}
