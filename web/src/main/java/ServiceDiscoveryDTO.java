import lombok.Data;

@Data
public class ServiceDiscoveryDTO {
    private String id;
    private String name;
    private String address;
    private int port;
    private boolean enableTagOverride;
}