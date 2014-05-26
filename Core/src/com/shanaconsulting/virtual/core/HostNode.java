/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shanaconsulting.virtual.core;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.InstanceContent;

/**
 *
 * @author pierre jacques
 */
class HostNode extends AbstractNode{

    public HostNode(HostListChild nodechild,Children child) {
        super(child);
        setName(nodechild.getName());
    }
    
    
    
}

