package HTTP;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class RESTResponse {
    private int code;
    private String responseBody;
}
