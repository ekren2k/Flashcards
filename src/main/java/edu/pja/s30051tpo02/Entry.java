package edu.pja.s30051tpo02;

public class Entry {
    private String englishTranslation;
    private String polishTranslation;
    private String germanTranslation;

    public Entry(String englishTranslation, String polishTranslation, String germanTranslation) {
        this.englishTranslation = englishTranslation;
        this.polishTranslation = polishTranslation;
        this.germanTranslation = germanTranslation;
    }

    public String getEnglishTranslation() {
        return englishTranslation;
    }
    public String getGermanTranslation() {
        return germanTranslation;
    }
    public String getPolishTranslation() {
        return polishTranslation;
    }


    @Override
    public String toString() {
       return polishTranslation+", "+englishTranslation+", "+germanTranslation;
    }
}
