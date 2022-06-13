package top.abosen.inetutil;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;
import org.springframework.cloud.commons.util.UtilAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(value = InetUtilsCustomProperties.ENABLED, matchIfMissing = true)
@AutoConfigureBefore(UtilAutoConfiguration.class)
@EnableConfigurationProperties({InetUtilsCustomProperties.class})
public class UtilHackAutoConfiguration {

    @Bean
    public InetUtils inetUtils(InetUtilsProperties properties, InetUtilsCustomProperties customProperties) {
        return new InetHackUtils(properties, customProperties);
    }

}
