package lk.ijse.apigateway.util;

@Component
public class JwtUtil {

    private final String SECRET = "mysecret";

    public boolean validateToken(String token) {
        return token != null && token.startsWith("valid");
    }
}