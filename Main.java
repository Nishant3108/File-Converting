import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        try {
            // Fetch HTML content from a website
            Document doc = Jsoup.connect("https://example.com").get();

            // Print the title of the page
            System.out.println("Page Title: " + doc.title());

            // Select and print all paragraph texts
            Elements paragraphs = doc.select("p");
            for (Element p : paragraphs) {
                System.out.println("Paragraph: " + p.text());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}