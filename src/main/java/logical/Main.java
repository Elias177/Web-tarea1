package logical;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Digite una URL valida (Sin https://www.): ");
        Scanner scanner = new Scanner(System.in);
        String url = "https://www." + scanner.nextLine();
        Document page = Jsoup.connect(url).get();

        //a) Indicar la cantidad de lineas del recurso retornado.
        int lineas = page.html().split("\n").length;
        //b) Indicar la cantidad de párrafos (p) que contiene el documento HTML.
        int parrafos = page.getElementsByTag("p").size();

        System.out.println("A) Cantidad de lineas: " + lineas + "\n");
        pause(scanner);
        System.out.println("B) Cantidad de parrafos: " + parrafos + "\n");
        pause(scanner);
        System.out.println("C) Cantidad de imagenes en los parrafos: " + puntoC(page) + "\n");
        pause(scanner);
        System.out.println("D) Cantidad de form: \n\t-Metodo GET: " + puntoDget(page) + "\n\t-Metodo POST: " + puntoDpost(page) + "\n");
        pause(scanner);
        System.out.println("E)Form y sus inputs con su tipo:\n");
        puntoE(page);
        pause(scanner);
        System.out.println("\n");
        puntoF(page,url);
    }

    private static void pause(Scanner scanner){
        System.out.println("Presione enter para continuar...");
        String p = scanner.nextLine();

    }
    private static int puntoC(Document d){
        int imagenes = 0;
        for(Element e : d.getElementsByTag("p")){

           if(e.toString().contains("<img"))
               imagenes++;

            }

        return imagenes;
    }

    private static int puntoDpost(Document d){
        int post = 0;
        for(Element e : d.getElementsByTag("form")){
            for(Element e2 : d.getElementsByAttributeValue("method","post")){
                post++;
            }
        }
        return post;
    }

    private static int puntoDget(Document d){
        int get = 0;
        for(Element e : d.getElementsByTag("form")){
            for(Element e2 : d.getElementsByAttributeValue("method","get")){
                get++;
            }
        }
        return get;
    }

    private static void puntoE(Document d) {
        String id = " ";
        for (Element e1 : d.getElementsByTag("form")) {

            if(e1.attributes().get("id").equalsIgnoreCase("  ")){
                id = "-el form no tiene id-";
            }else{
                id= e1.attributes().get("id");
            }
            System.out.println("El form " + id + " tiene input de tipo: " +"\n");

            for (Element e : d.getElementsByTag("input")) {

                System.out.println(e.attributes().get("type"));
            }

        }

    }

    private static void puntoF(Document d, String url) throws IOException {
        Connection.Response response =
                Jsoup.connect(url)
                        .data("asignatura","practica1")
                        .header("matricula","2014-0717")
                        .method(Connection.Method.POST)
                        .execute();

        //parse the document from response
        System.out.println(response.parse());
        System.out.println("\n***Estado:"+ response.statusMessage());
        System.out.println("\n***Codigo:"+ response.statusCode());

    }
}