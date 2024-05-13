package sk.kasv.degro.worldappgui.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.kasv.degro.worldappgui.model.City;
import sk.kasv.degro.worldappgui.model.Country;

import java.util.List;

public class ListConvertor {
    public static ObservableList<String> convertListCountry(List<Country> list) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Country country : list) {
            observableList.add(country.getName());
        }
        return observableList;
    }

    public static ObservableList<String> convertListCity(List<City> list) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (City city : list) {
            observableList.add(city.getName());
        }
        return observableList;
    }


}
