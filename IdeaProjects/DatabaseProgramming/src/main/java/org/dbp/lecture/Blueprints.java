package org.dbp.lecture;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.util.function.Consumer;

public class Blueprints {
    public static void main(String args[]) throws Exception{
        Graph g = new TinkerGraph();
        BufferedReader r = new BufferedReader(new FileReader("/Users/songseunghun/Desktop/DBP/Data/email-EuAll.txt"));
        String str;
        String person[];
        Vertex sendV;
        Vertex receiverV;

        while ((str = r.readLine()) != null) {
            if (str.startsWith("#"))
                continue;
            person = str.split("\t");
            sendV = g.getVertex(person[0]);
            if (sendV == null)
                sendV = g.addVertex(person[0]);
            receiverV = g.getVertex(person[1]);
            if (receiverV == null)
                receiverV = g.addVertex(person[1]);
            g.addEdge(person[0] + "|send|" + person[1], sendV, receiverV, "send");
        }

        g.getVertices().forEach(e -> {
            System.out.println(e);
            e.getVertices(Direction.OUT).forEach(w -> {
                System.out.println("==>" + w);
                w.getVertices(Direction.OUT).forEach(q -> {
                    System.out.println("====>" + q);
                });
            });
        });
    }
}
