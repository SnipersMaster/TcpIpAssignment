import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by androiddevelopment on 5/31/15.
 */
public class Assingment1_a {

    static int a,b,c,d,e,f;
    public static void main(String []arg) throws IOException
    {
        BufferedReader bufferedReader=new BufferedReader(new FileReader("Books/book1.html"));
        while (bufferedReader.ready())
        {
            char search= (char) bufferedReader.read();
            switch (search)
            {
                case 'a':
                    ++a;
                    break;
                case 'b':
                    ++b;
                    break;
                case 'c':
                    ++c;
                    break;
                case 'd':
                    ++d;
                    break;
                case 'e':
                    ++e;
                    break;
                case 'f':
                    ++f;
                    break;
            }

        }
        System.out.println("Counters " + "\n" + "a : "+(a)+"\n"+"b : "+(b)+"\n"+"c : "+(c)+"\n"+"d :"+(d)+"\n"+"e :"+(e)+"\n"+"f :"+(f));

    }
}
