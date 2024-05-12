package sk.kasv.degro.worldappgui.model;

public class Country {
    private String name;
    private String code2;
    private String code3;
    private String officialLanguage;

    public Country(String name, String code2, String code3, String officialLanguage) {
        this.name = name;
        this.code2 = code2;
        this.code3 = code3;
        this.officialLanguage = officialLanguage;
    }

    public Country(String name, String code2, String code3) {
        this.name = name;
        this.code2 = code2;
        this.code3 = code3;
    }

    public String getName() {
        return name;
    }
}
