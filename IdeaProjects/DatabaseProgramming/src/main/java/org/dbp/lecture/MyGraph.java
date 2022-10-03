package org.dbp.lecture;

import revised.Edge;
import revised.Graph;
import revised.Vertex;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

public class MyGraph implements Graph {

    private HashMap<String, Vertex> vertices;
    private HashMap<String, Edge> edges;

    public MyGraph() {
        this.vertices = HashMap<>();
        this.edges = HashMap<>() ;
    }

    @Override
    public Vertex addVertex(String id) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Vertex getVertex(String id) {
        return null;
    }

    @Override
    public void removeVertex(Vertex vertex) {

    }

    @Override
    public Collection<Vertex> getVertices() {
        return null;
    }

    @Override
    public Collection<Vertex> getVertices(String key, Object value) {
        return null;
    }

    @Override
    public Edge addEdge(Vertex outVertex, Vertex inVertex, String label) throws IllegalArgumentException, NullPointerException {
        return null;
    }

    @Override
    public Edge getEdge(Vertex outVertex, Vertex inVertex, String label) {
        return null;
    }

    @Override
    public Edge getEdge(String id) {
        return null;
    }

    @Override
    public void removeEdge(Edge edge) {

    }

    @Override
    public Collection<Edge> getEdges() {
        return null;
    }

    @Override
    public Collection<Edge> getEdges(String key, Object value) {
        return null;
    }

    @Override
    public void shutdown() throws SQLException {

    }
}
