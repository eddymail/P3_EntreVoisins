package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();
    List<Neighbour> getFavoritesNeighbours();
    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Get a neighbour by Id
     * @param id
     * @return
     */
    Neighbour getNeighbourById(int id);

    /**
     * add a neighbour to favorites
     * @param neighbour
     * @return
     */
    void addFavoriteNeighbour(Neighbour neighbour);


    /**
     * Deletes a favorits neighbour
     * @param neighbour
     * @return
     */
    void deleteFavoriteNeighbour(Neighbour neighbour);

}
