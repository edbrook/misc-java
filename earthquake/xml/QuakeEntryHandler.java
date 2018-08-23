package earthquake.xml;

import earthquake.entity.QuakeEntry;
import earthquake.util.Location;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

class QuakeEntryHandler extends DefaultHandler {
    private final Consumer<QuakeEntry> entryProcessor;
    private final QuakeEntry.QuakeEntryBuilder entryBuilder;
    private final StringBuilder sb;
    private final DateTimeFormatter dateTimeFormat;

    QuakeEntryHandler(Consumer<QuakeEntry> entryProcessor) {
        this.entryProcessor = entryProcessor;
        this.entryBuilder = new QuakeEntry.QuakeEntryBuilder();
        this.sb = new StringBuilder();
        this.dateTimeFormat = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sb.setLength(0);
        switch (qName) {
            case "entry":
                entryBuilder.reset();
                break;
            case "link":
                processLink(attributes);
                break;
            case "category":
                processCategory(attributes);
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "entry":
                this.entryProcessor.accept(entryBuilder.build());
                entryBuilder.reset();
                sb.setLength(0);
            case "id":
                entryBuilder.setId(sb.toString());
                break;
            case "title":
                String title = sb.toString();
                entryBuilder.setTitle(title);
                if (title.startsWith("M ")) {
                    try {
                        float magnitude = Float.parseFloat(title.split(" ")[1]);
                        entryBuilder.setMagnitude(magnitude);
                    } catch (NumberFormatException nfe) {
                        //
                    }
                }
                break;
            case "updated":
                entryBuilder.setUpdatedAt(
                        LocalDateTime.parse(sb.toString(), dateTimeFormat));
                break;
            case "summary":
                entryBuilder.setSummary(sb.toString());
                break;
            case "georss:point":
                processGeoPoint(sb.toString().split(" "));
                break;
            case "georss:elev":
                entryBuilder.setElevation(
                        Integer.parseInt(sb.toString()));
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    private void processLink(Attributes attributes) {
        try {
            String href = attributes.getValue("href");
            this.entryBuilder.setLinkUrl(new URL(href));
        } catch (MalformedURLException e) {
            //
        }
    }

    private void processGeoPoint(String[] lonLat) {
        Location location = new Location();
        location.setLatitude(Double.parseDouble(lonLat[0]));
        location.setLongitude(Double.parseDouble(lonLat[1]));
        this.entryBuilder.setLocation(location);
    }

    private void processCategory(Attributes attributes) {
        String label = attributes.getValue("label");
        String term = attributes.getValue("term");
        if (!label.isEmpty() && !term.isEmpty()) {
            this.entryBuilder.addCategory(label, term);
        }
    }
}
/*
<entry>
    <id>urn:earthquake-usgs-gov:ak:20158183</id>
    <title>M 1.3 - 70km SSW of Kobuk, Alaska</title>
    <updated>2018-08-23T06:41:53.448Z</updated>
    <link rel="alternate" type="text/html" href="https://earthquake.usgs.gov/earthquakes/eventpage/ak20158183"/>
    <summary type="html">
        <![CDATA[
        <dl><dt>Time</dt><dd>2018-08-23 06:38:36 UTC</dd><dd>2018-08-22 21:38:36 -09:00 at epicenter</dd><dt>Location</dt><dd>66.293&deg;N 157.234&deg;W</dd><dt>Depth</dt><dd>0.10 km (0.06 mi)</dd></dl>
        ]]>
    </summary>
    <georss:point>66.2929 -157.2339</georss:point>
    <georss:elev>-100</georss:elev>
    <category label="Age" term="Past Hour"/>
    <category label="Magnitude" term="Magnitude 1"/>
    <category label="Contributor" term="ak"/>
    <category label="Author" term="ak"/>
</entry>
*/