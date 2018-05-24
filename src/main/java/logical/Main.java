package logical;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Digite una URL valida (Sin https://www.): ");
        Scanner scanner = new Scanner(System.in);
        String url = "https://www." + scanner.nextLine();
        Document page = Jsoup.connect(url).get();

        int lineas = page.html().split("\n").length;
        int parrafos = page.getElementsByTag("p").size();

        System.out.println("A) Cantidad de lineas: " + lineas + "\n");
        pause(scanner);
        System.out.println("B) Cantidad de parrafos: " + parrafos + "\n");
        pause(scanner);
        System.out.println("C) Cantidad de imagenes en los parrafos: " + puntoC(page) + "\n");
        pause(scanner);
        System.out.println("D) Ccantidad de form: \n\t-Metodo GET: " + puntoDget(page) + "\n\t-Metodo POST: " + puntoDpost(page) + "\n");
        pause(scanner);

    }

    public static void pause(Scanner scanner){
        System.out.println("Presione enter para continuar...");
        String p = scanner.nextLine();
    }
    public static int puntoC(Document d){
        int imagenes = 0;
        for(Element e : d.getElementsByTag("p")){
           imagenes =  e.getElementsByTag("img").size();
            }

        return imagenes;
    }
    public static int puntoDpost(Document d){
        int post = 0;
        for(Element e : d.getElementsByTag("form")){
            e.getElementsByAttributeValue("method","post").size();
        }
        return post;
    }

    public static int puntoDget(Document d){
        int get = 0;
        for(Element e : d.getElementsByTag("form")){
            e.getElementsByAttributeValue("method","get").size();
        }
        return get;
    }

}