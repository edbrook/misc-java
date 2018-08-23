package earthquake.entity;

import earthquake.util.Location;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class QuakeEntry {
    private final String id;
    private String title;
    private LocalDateTime updated;
    private URL link;
    private String summary;
    private Location location;
    private int elevation;
    private float magnitude;
    private final Map<String,String> categories;

    public QuakeEntry(String id) {
        this.id = id;
        this.categories = new HashMap<>();
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getUpdated() { return updated; }
    public URL getLink() { return link; }
    public String getSummary() { return summary; }
    public Location getLocation() { return location; }
    public int getElevation() { return elevation; }
    public float getMagnitude() { return magnitude; }
    public Map<String, String> getCategories() { return categories; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QuakeEntry[id=\"");
        sb.append(this.id);
        sb.append("\", title=\"");
        sb.append(this.title);
        sb.append("\", link=\"");
        sb.append(this.link.getPath());
        sb.append("\"]");
        return sb.toString();
    }

    public static class QuakeEntryBuilder {
        private String id;
        private String title;
        private LocalDateTime updated;
        private URL link;
        private String summary;
        private Location location;
        private int elevation;
        private float magnitude;
        private Map<String,String> categories;

        public QuakeEntryBuilder() {
            reset();
        }
        
        public QuakeEntryBuilder reset() {
            this.id = null;
            this.title = "";
            this.updated = LocalDateTime.now();
            this.link = null;
            this.summary = "";
            this.location = null;
            this.elevation = 0;
            this.magnitude = 0.0f;
            this.categories = new HashMap<>();
            return this;
        }
        public QuakeEntryBuilder setId(String id) { this.id = id; return this; }
        public QuakeEntryBuilder setTitle(String title) { this.title = title; return this; }
        public QuakeEntryBuilder setUpdatedAt(LocalDateTime updated) { this.updated = updated; return this; }
        public QuakeEntryBuilder setLinkUrl(URL link) { this.link = link; return this; }
        public QuakeEntryBuilder setSummary(String summary) { this.summary = summary; return this; }
        public QuakeEntryBuilder setLocation(Location location) { this.location = location; return this; }
        public QuakeEntryBuilder setElevation(int elevation) { this.elevation = elevation; return this; }
        public QuakeEntryBuilder setMagnitude(float magnitude) { this.magnitude = magnitude; return this; }
        public QuakeEntryBuilder addCategory(String label, String term) { this.categories.put(label, term); return this; }

        public QuakeEntry build() {
            if (this.id == null) {
                throw new RuntimeException("QuakeEntry id must not be null");
            }
            QuakeEntry entry = new QuakeEntry(this.id);
            entry.title = this.title;
            entry.updated = this.updated;
            entry.link = this.link;
            entry.summary = this.summary;
            entry.location = this.location;
            entry.elevation = this.elevation;
            entry.magnitude = this.magnitude;
            entry.categories.putAll(this.categories);
            return entry;
        }
    }
}