package org.example.libs;

import org.aeonbits.owner.Config;

@Config.Sources(value = "file:./src/main/java/resources/config.properties")
public interface ConfigProperties extends Config{

    String base_url();
    String API_KEY();

    String API_BASE_URL();
    long TIME_FOR_DEFAULT_WAIT();
    long TIME_FOR_EXPLICIT_WAIT_LOW();
    long TIME_FOR_EXPLICIT_WAIT_HIGH();
}
