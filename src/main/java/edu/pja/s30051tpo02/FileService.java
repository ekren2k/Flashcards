package edu.pja.s30051tpo02;


import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@ConfigurationProperties(prefix = "pl.edu.pja.tpo02")
public class FileService {

    private String filename;
    private EntryRepository er;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public EntryRepository getEntryRepository() {
        return er;
    }

    public FileService() {
    }

    @PostConstruct
    public void init() {
        this.er = readFromCSV();
    }

    public EntryRepository readFromCSV() {
        EntryRepository er = new EntryRepository();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Entry entry = new Entry(data[0], data[1], data[2]);
                er.addEntry(entry);
            }
            for (Entry entry : er.getEntries())
                System.out.println(entry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return er;
    }

    public void writeToCSV(Entry entry) {
        String line = entry.getEnglishTranslation()+","+entry.getPolishTranslation()+","+entry.getGermanTranslation();
        System.out.println(line);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(line);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
