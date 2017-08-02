package com.inno.ildmi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Wall> walls = new ArrayList<>();
        ArrayList<Node> nodes = new ArrayList<>();

        Scanner scanner = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader("input.txt");
            scanner = new Scanner(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int M = scanner.nextInt();

        for(int i = 0; i < K; i++){
            nodes.add(new Node(NODETYPE.BALL, scanner.nextInt()-1, scanner.nextInt()-1, N));
        }
        for(int i = 0; i < K; i++){
            nodes.add(new Node(NODETYPE.HOLE, scanner.nextInt()-1, scanner.nextInt()-1, N));
        }
        for(int i = 0; i < M; i++){
            walls.add(new Wall(scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextInt()-1, scanner.nextInt()-1));
        }

        scanner.close();
        try {
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph(N);

        graph.placeBallsAndHoles(nodes);
        graph.fillVerticies(walls);
        graph.fillEdges();



        Dijkstra dijkstra = new Dijkstra(graph.getVertices(), graph.getEdges());

        dijkstra.execute(graph.getVertices().get(5));
        LinkedList<Node> path = dijkstra.getPath(graph.getVertices().get(0));
        for(Node n: path){
            System.out.println(n.nodeNumber);
        }

    }
}
