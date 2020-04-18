package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    @Override
    public List<Neighbour> getFavoritesNeighbours() {
        List<Neighbour> result = new ArrayList<>();
        for(Neighbour neighbour: neighbours){
            if(neighbour.isFavorite()){
                result.add(neighbour);
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public Neighbour getNeighbourById(int id) {
        for(Neighbour neighbour: neighbours){
            if(neighbour.getId()==id)
                return neighbour;
        }
        return null;
    }
}
