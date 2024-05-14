package sk.kasv.degro.worldappgui.Util;

public class CityNameEditor {
    public static String editCityName(String cityName) {
        if(cityName == null) {
            return null;
        }
        return cityName.replace(" ", "%20");
    }
}
