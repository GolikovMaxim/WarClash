package HTTP;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class RESTResponse {
    private int code;
    private String responseBody;
}
