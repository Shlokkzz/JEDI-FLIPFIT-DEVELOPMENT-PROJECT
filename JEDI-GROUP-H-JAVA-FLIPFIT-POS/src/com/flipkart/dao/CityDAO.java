package com.flipkart.dao;

import com.flipkart.bean.City;
import com.flipkart.bean.GymCenter;
import com.flipkart.bean.Slot;

import java.util.List;

public interface CityDAO {
    public boolean cityExists(String cityName);
    public List<City> getAllCities();
    public boolean addCity(City city);
    public List<GymCenter> fetchGymCenters(String city);
    public List<Slot> fetchSlotsByGymID(String gymID);
}
