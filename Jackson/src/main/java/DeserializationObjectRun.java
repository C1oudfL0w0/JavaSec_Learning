import com.fasterxml.jackson.databind.ObjectMapper;

public class DeserializationObjectRun {

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        String json = "{\"age\":16,\"name\":\"0w0\",\"object\":[\"Evil\",{\"cmd\":\"calc\"}]}";
        Person4 p2 = mapper.readValue(json, Person4.class);
        System.out.println(p2);

    }
}