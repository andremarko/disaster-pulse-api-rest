package disaster.pulse.api.dto;

public record JwtDto(String jwt, String type) {
    public JwtDto(String jwt) {
        this(jwt, "Bearer");
    }
}
