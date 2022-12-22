package se.lexicon.model;

import java.util.Objects;

public class City {

    //fields
    private int id;
    private String Name;
    private String CountryCode;
    private String District;
    private int Population;


    //Constructor


    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    public City(String name, String countryCode, String district, int population) {
        Name = name;
        CountryCode = countryCode;
        District = district;
        Population = population;
    }

    public City(int id) {
        this.id = id;
    }
    //getters and setters

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public int getPopulation() {
        return Population;
    }

    public void setPopulation(int population) {
        Population = population;
    }

    //equals & hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Population == city.Population && Objects.equals(Name, city.Name) && Objects.equals(CountryCode, city.CountryCode) && Objects.equals(District, city.District);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Name, CountryCode, District, Population);
    }


    //toString()

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                ", District='" + District + '\'' +
                ", Population=" + Population +
                '}';
    }
}
