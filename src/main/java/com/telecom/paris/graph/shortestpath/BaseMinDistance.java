package com.telecom.paris.graph.shortestpath;

import com.telecom.paris.graph.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class BaseMinDistance implements MinDistance {
    private final Map<Vertex, Integer> minDistances = new HashMap<>();
    @Override
    public int getMinDistance(final Vertex vertex) {
        return this.minDistances.get(vertex);
    }

    @Override
    public void setMinDistance(final Vertex vertex, int minimalDistance) {
        this.minDistances.put(vertex, minimalDistance);
    }

    @Override
    public Vertex getMinDistanceVertex(final ProcessedVertexesSet processedVertexesSet,final Set<Vertex> vertexes) {
        // First of all, we should sort the vertexes by their distance to the start vertex
        final List<Entry<Vertex, Integer>> sortedVertexes = new ArrayList<Entry<Vertex, Integer>>(this.minDistances.entrySet());
        Comparator<Entry<Vertex, Integer>> comparator = new Comparator<Entry<Vertex, Integer>>() {

			@Override
			public int compare(Entry<Vertex, Integer> arg0, Entry<Vertex, Integer> arg1) {
				Entry<Vertex, Integer> entryOne =  (Entry<Vertex, Integer>) arg0;
				Entry<Vertex, Integer> entryTwo =  (Entry<Vertex, Integer>) arg1;
				return Integer.compare(entryOne.getValue(), entryTwo.getValue());
			}
        	
        };
        
        Collections.sort(sortedVertexes, comparator);
        
        for (Entry<Vertex, Integer> entry : sortedVertexes) {
        	Vertex vertex = entry.getKey();
            if (!processedVertexesSet.contains(vertex)) {
                return vertex;
            }
        }

        return null;
    }
}
