package top.abosen.inetutil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.cloud.commons.util.InetUtilsProperties;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetHackUtils extends InetUtils {
    private final InetUtilsCustomProperties customProperties;
    private final Log log = LogFactory.getLog(InetHackUtils.class);

    public InetHackUtils(InetUtilsProperties properties, InetUtilsCustomProperties customProperties) {
        super(properties);
        this.customProperties = customProperties;
    }

    @Override
    public InetAddress findFirstNonLoopbackAddress() {
        InetAddress address = null;
        String customAddress = customProperties.getAddressHolder();
        if (customAddress != null && customAddress.length() != 0) {
            try {
                address = InetAddress.getByName(customAddress);
            } catch (UnknownHostException e) {
                log.error("cant parse the address config: " + customAddress, e);
            }
        }

        if (address == null) {
            address = super.findFirstNonLoopbackAddress();
        }
        return address;
    }

    public HostInfo findFirstNonLoopbackHostInfo() {
        InetAddress address = findFirstNonLoopbackAddress();
        if (address instanceof Inet6Address) {
            HostInfo standHostInfo = convertAddress(address);
            standHostInfo.setIpAddress("[" + standHostInfo.getIpAddress() + "]");
            return standHostInfo;
        }
        return super.findFirstNonLoopbackHostInfo();
    }
}
