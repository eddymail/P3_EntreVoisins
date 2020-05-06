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

    /**
     *
     * @return
     */
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

    @Override
    public void deleteFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(false);
    }

    @Override
    public void addFavoriteNeighbour(Neighbour neighbour) {
        neighbour.setFavorite(true);
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
