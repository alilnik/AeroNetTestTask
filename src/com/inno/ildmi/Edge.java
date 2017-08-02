package com.inno.ildmi;

/**
 * Created by mjazz on 02.08.2017.
 */
public class Edge {
    Node strart;
    Node end;

    public Edge(Node strart, Node end) {
        this.strart = strart;
        this.end = end;
    }

    public Node getDestination(){
        return end;
    }

    public Node getSource(){
        return strart;
    }


}
