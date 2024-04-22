package com.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * ClassName: UserProperties
 * Package: com.spzx.manager.properties
 * Description:
 * Author: zhj
 * Create: 2024/4/12 - 17:29
 * Version: v1.0
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserProperties {
    private List<String> noAuthUrls;
}
