package earthquake;

import earthquake.xml.QuakeXMLLoader;
import earthquake.xml.processor.QuakeEntryProcessor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Application {

    private static final String QUAKE_SOURCE_URI =
            "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.atom";

    private static final DateTimeFormatter dateFormat = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM);

    public static void main(String[] args) {
        QuakeXMLLoader.load(QUAKE_SOURCE_URI,
                qe -> System.out.println(
                        qe.getUpdated().format(dateFormat) + " -> " +
                        qe.getTitle()));
    }
}
