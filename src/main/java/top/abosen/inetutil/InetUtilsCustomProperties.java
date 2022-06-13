package top.abosen.inetutil;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(InetUtilsCustomProperties.PREFIX)
@Data
public class InetUtilsCustomProperties {
    public static final String PREFIX = "spring.cloud.inetutils.custom";
    public static final String ENABLED = PREFIX + ".enabled";
    public static final String ADDRESS = PREFIX + ".address";
    public static final String ENV_HOLDER = "CUSTOM_API_HOST";

    boolean enabled;
    String address;

    @Value("${" + ADDRESS + ":${" + ENV_HOLDER + ":}}")
    @Setter(value = AccessLevel.NONE)
    String addressHolder;
}
