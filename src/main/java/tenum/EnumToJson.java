package tenum;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EnumToJson {

    public static void main(String[] args) throws JsonProcessingException {
        StatusData statusData = new StatusData();
        statusData.status = Status.DONE;
        statusData.name = "Kire bhai";

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(statusData));
    }
}
