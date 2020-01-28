package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }
//TODO supression d'un voisin de la liste des voisin et de la liste des favoris
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
        assertFalse(service.getFavorites().contains(neighbourToDelete));
    }

// TODO supression du voisin de la liste des favoris
    @Test
    public void deleteNeighbourFavWithSuccess() {
        Neighbour neighbour = service.getFavorites().get(0);
        service.deleteFavorite(neighbour);
        assertFalse(service.getFavorites().contains(neighbour));
    }
//TODO ajout du voisin dans la lsite des favoris
    @Test
    public void getNeighbourFavoriWithSuccess() {
        Neighbour lNeighbourAdd = service.getNeighbours().get(2);
        service.getFavorites().add(lNeighbourAdd);
        assertTrue(service.getFavorites().contains(lNeighbourAdd));

    }

}



