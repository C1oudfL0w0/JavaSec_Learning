import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationTest {
    public static void main(String[] args) throws Exception {
        User2 test = new User2("0w0", 16);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enableDefaultTyping();

        String json = mapper.writeValueAsString(test);
        System.out.println(json);

        User2 u2 = mapper.readValue(json, User2.class);
        System.out.println(u2);
    }
}