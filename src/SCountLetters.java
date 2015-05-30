import java.io.*;

public class SCountLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Sniper\\Desktop\\New Text Document.txt"));
        int ch;
        char search = 'e';
        int counter=0;
        while((ch=reader.read()) != -1) {if(search == (char)ch) {counter++;}}
        reader.close();
        System.out.println(search+": "+counter);
    }}
