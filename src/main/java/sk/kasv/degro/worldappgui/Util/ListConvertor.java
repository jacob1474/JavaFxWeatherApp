package sk.kasv.degro.worldappgui.Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sk.kasv.degro.worldappgui.model.Country;

import java.util.List;

public class ListConvertor {
    public static ObservableList<String> convertList(List<Country> list) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (Country country : list) {
            observableList.add(country.getName());
        }
        return observableList;
    }
}
