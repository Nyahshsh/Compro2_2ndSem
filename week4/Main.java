import java.io.IOException;
import java.io.FileWriter;

public class Main{
    public static void main(String[] args) {
        
        try(FileWriter fw = new FileWriter("data.txt")) {
            fw.write("Blehblehbleh");
            fw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }finally{
            fw.close();
        }
    }
}