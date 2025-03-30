package edu.pja.s30051tpo02;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EntryRepository {
    private List<Entry> entries;
    public EntryRepository() {
        entries = new ArrayList<>();
    }
    public List<Entry> getEntries() {
        return entries;
    }
    public void addEntry(Entry entry) {
        entries.add(entry);
    }
}
