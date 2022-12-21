package se.lexicon.model;

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
}
