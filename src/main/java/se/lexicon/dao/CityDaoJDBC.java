package se.lexicon.dao;

import se.lexicon.model.City;

import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao{

    private List<City> cityStorage;

    public CityDaoJDBC(){
        cityStorage=new ArrayList<>();
    }

    @Override
    public City findByID(int id) {
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        return null;
    }

    @Override
    public List<City> findAll() {
        return new ArrayList<>(cityStorage);
    }

    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
