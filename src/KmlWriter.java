import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class KmlWriter {
    private PrintWriter printWriter;
    String colors[] = {"green","red","yellow","blue","brown","black","pink"};
    String colorHex[] = {"ff009900","ff0000ff","ff14f0fa","fff03214","ff143c8c","ff000000","ff7846F0"};
    int i = 0;
    KmlWriter(String fileName) {
        File file = new File(fileName);
        Locale.setDefault(new Locale("en", "US"));
        try {
            printWriter = new PrintWriter(file, "UTF-8");
        } catch (IOException we) {
            System.out.println("cant create kml: " + we.toString());
        }

    }

    public void printIntroKml() {
        printWriter.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "\t<kml xmlns=\"http://earth.google.com/kml/2.1\">\n");
        printWriter.printf("\t<Document>\n" +
                "\t\t<name>Taxi Routes</name>\n");
        for (int i = 0; i < colors.length; i++) {                       // define colors
            printWriter.printf("\t\t<Style id=\"%s\">\n" +
                    "\t\t\t<LineStyle>\n" +
                    "\t\t\t\t<color>%s</color>\n" +
                    "\t\t\t\t<width>4</width>\n" +
                    "\t\t\t</LineStyle>\n" +
                    "\t\t</Style>\n", colors[i], colorHex[i]);
        }
    }

    public void newTaxiRoute(LinkedList<Node> path) {
        Node nextNode;
        // Print multiple taxis with same path
        for (int id : path.getLast().getStartingIds()) {
            Iterator<Node> curNode = path.descendingIterator();
            printWriter.print("\t\t<Placemark>\n");
            printWriter.printf("\t\t\t<name>Taxi %d</name>\n", id);
            printWriter.print("\t\t\t<styleUrl>#" + colors[(i++)%colors.length] + "</styleUrl>\n" +
                    "\t\t\t<LineString>\n" +
                    "\t\t\t\t<altitudeMode>relative</altitudeMode>\n" +
                    "\t\t\t\t<coordinates>\n");
            // Add path points and coordinates
            while (curNode.hasNext()) {
                nextNode = curNode.next();
                printWriter.printf("\t\t\t\t\t%f,%f,0\n", nextNode.getX(), nextNode.getY());
            }
            printWriter.print("\t\t\t\t</coordinates>\n" +
                    "\t\t\t</LineString>\n" +
                    "\t\t</Placemark>\n");

        }
    }

    public void endKml() {
        printWriter.print("\t</Document>\n" +
                "\t</kml>");
        printWriter.close();
    }

}
