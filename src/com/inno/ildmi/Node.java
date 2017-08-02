package com.inno.ildmi;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by mjazz on 02.08.2017.
 */
public class Node {
    private NODETYPE nodetype;
    Node west, east, north, south;

    private int xpos, ypos;
    int nodeNumber, N;


    public Node(NODETYPE nodetype, int xpos, int ypos, int N) {
        this.nodetype = nodetype;
        this.xpos = xpos;
        this.ypos = ypos;
        this.N = N;
        nodeNumber = calculateNodeNumber(xpos, ypos);

    }


    public Node() {
        nodetype = NODETYPE.EMPTY;
    }

    public int getXpos() {
        return xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public NODETYPE getNodetype() {
        return nodetype;
    }

    private int calculateNodeNumber(int x, int y){
        return N*(y+1) -(N-(x+1)) - 1;
    }

    public ArrayList<Node> getNeighbours(){
        ArrayList<Node> nodes = new ArrayList<>();
        if(north != null) nodes.add(north);
        if(south != null) nodes.add(south);
        if(east != null) nodes.add(east);
        if(west != null) nodes.add(west);
        return nodes;
    }

}
