package com.inno.ildmi;

import javafx.util.Pair;
import java.util.ArrayList;

/**
 * Created by mjazz on 02.08.2017.
 */
public class Graph {

    ArrayList<ArrayList<Boolean>> adjacencyMatrix;
    ArrayList<NODETYPE> nodetypes;
    ArrayList<Node> vertices;
    ArrayList<Edge> edges;
    int N;

    public Graph(int N) {
        this.N = N;
        adjacencyMatrix = new ArrayList<>();
        nodetypes = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        for(int i = 0; i < N; i++){
            adjacencyMatrix.add(new ArrayList<>());
            for(int j = 0; j < N; j++){
                adjacencyMatrix.get(i).add(true);
                nodetypes.add(NODETYPE.EMPTY);
                vertices.add(new Node(NODETYPE.EMPTY, j, i, N));
            }
        }

    }

    public void buildMatrix(ArrayList<Node> nodes, ArrayList<Wall> walls){
        for(Node n: nodes){
            nodetypes.set(calculateNodeNumber(n.getXpos(), n.getYpos()), n.getNodetype());
        }
        for(Wall w: walls){
            int nodeNum1 = calculateNodeNumber(w.getX1(), w.getY1());
            int nodeNum2 = calculateNodeNumber(w.getX2(), w.getY2());
            adjacencyMatrix.get(w.getX1()).set(nodeNum2, false);
            adjacencyMatrix.get(nodeNum2).set(nodeNum1, false);
        }
    }


    public Node getEndNode(Node node, DIRECTION direction, ArrayList<Wall> walls){
        switch (direction){
            case NORTTH:
                if(node.north.equals(node)) return node;
                else if(walls.contains(new Wall(node.getXpos(), node.getYpos(), node.getXpos(), node.getYpos()-1))) return node;
                else return getEndNode(node.north, DIRECTION.NORTTH, walls);
            case EAST:
                if(node.east.equals(node)) return node;
                else if(walls.contains(new Wall(node.getXpos(), node.getYpos(), node.getXpos()+1, node.getYpos()))) return node;
                else return getEndNode(node.east, DIRECTION.EAST, walls);
            case WEST:
                if(node.west.equals(node)) return node;
                else if(walls.contains(new Wall(node.getXpos(), node.getYpos(), node.getXpos()-1, node.getYpos()))) return node;
                else return getEndNode(node.west, DIRECTION.WEST, walls);
            case SOUTH:
                if(node.south.equals(node)) return node;
                else if(walls.contains(new Wall(node.getXpos(), node.getYpos(), node.getXpos(), node.getYpos()+1))) return node;
                else return getEndNode(node.south, DIRECTION.SOUTH, walls);
        }
        return null;
    }

    public void fillVerticies(ArrayList<Wall> walls){
        for(Node n: vertices){
            int xpos = n.getXpos();
            int ypos = n.getYpos();
            if(xpos - 1 >= 0) n.west = vertices.get(n.nodeNumber - 1);
            else n.west = n;
            if(xpos + 1 < N) n.east = vertices.get(n.nodeNumber + 1);
            else n.east = n;
            if(ypos - 1 >= 0) n.north = vertices.get(n.nodeNumber - N);
            else n.north = n;
            if(ypos + 1 < N) n.south = vertices.get(n.nodeNumber + N);
            else n.south = n;
        }
        for(Node n: vertices){
            int xpos = n.getXpos();
            int ypos = n.getXpos();
            n.south = getEndNode(n, DIRECTION.SOUTH, walls);
            n.west = getEndNode(n, DIRECTION.WEST, walls);
            n.north = getEndNode(n, DIRECTION.NORTTH, walls);
            n.east = getEndNode(n, DIRECTION.EAST, walls);
        }
    }

    public void placeBallsAndHoles(ArrayList<Node> nodes){
        for(Node n: nodes){
            nodetypes.add(calculateNodeNumber(n.getXpos(), n.getYpos()), n.getNodetype());
        }
    }

    public ArrayList<Node> getVertices() {
        return vertices;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void fillEdges(){
        for(Node n: vertices){
            edges.add(new Edge(n, n.north));
            edges.add(new Edge(n, n.south));
            edges.add(new Edge(n, n.east));
            edges.add(new Edge(n, n.west));
        }

    }

    private int calculateNodeNumber(int x, int y){
        return N*(y+1) -(N-(x+1)) - 1;
    }

    public Pair<Integer, Integer> getMatrixPosition(int number){
        Pair<Integer, Integer> position = null;
        int y = ((int) Math.ceil((number+1.0)/N))-1;
        int x = number % (y+1);
        return new Pair<>(x, y);
    }


}
