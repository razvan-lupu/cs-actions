package io.cloudslang.content.jclouds.entities.inputs;

import io.cloudslang.content.jclouds.entities.aws.Providers;
import io.cloudslang.content.jclouds.entities.constants.Constants;
import io.cloudslang.content.jclouds.utils.InputsUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by persdana on 5/27/2015.
 */
public class CommonInputs {
    private String provider;
    private String endpoint;
    private String identity;
    private String credential;
    private String proxyHost;
    private String proxyPort;
    private String delimiter;

    private boolean debugMode;

    private CommonInputs(CommonInputsBuilder builder) {
        this.provider = builder.provider;
        this.endpoint = builder.endpoint;
        this.identity = builder.identity;
        this.credential = builder.credential;
        this.proxyHost = builder.proxyHost;
        this.proxyPort = builder.proxyPort;
        this.delimiter = builder.delimiter;
        this.debugMode = builder.debugMode;
    }

    public String getProvider() {
        return provider;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getIdentity() {
        return identity;
    }

    public String getCredential() {
        return credential;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public String getProxyPort() {
        return proxyPort;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public static class CommonInputsBuilder {
        private String provider;
        private String endpoint;
        private String identity;
        private String credential;
        private String proxyHost;
        private String proxyPort;
        private String delimiter;

        private boolean debugMode;

        public CommonInputs build() {
            return new CommonInputs(this);
        }

        public CommonInputsBuilder withProvider(String inputValue) throws Exception {
            provider = Providers.getValue(inputValue);
            return this;
        }

        public CommonInputsBuilder withEndpoint(String inputValue) throws MalformedURLException {
            if (StringUtils.isBlank(inputValue)) {
                throw new RuntimeException("The required " + inputValue + " input is not specified!");
            }
            endpoint = new URL(inputValue.toLowerCase()).toString();
            return this;
        }

        public CommonInputsBuilder withIdentity(String inputValue) {
            identity = inputValue;
            return this;
        }

        public CommonInputsBuilder withCredential(String inputValue) {
            credential = inputValue;
            return this;
        }

        public CommonInputsBuilder withProxyHost(String inputValue) {
            proxyHost = inputValue;
            return this;
        }

        public CommonInputsBuilder withProxyPort(String inputValue) {
            proxyPort = inputValue;
            return this;
        }

        public CommonInputsBuilder withDelimiter(String inputValue) {
            delimiter = InputsUtil.getDefaultStringInput(inputValue, Constants.Miscellaneous.COMMA_DELIMITER);
            return this;
        }

        public CommonInputsBuilder withDebugMode(String inputValue) {
            debugMode = Boolean.parseBoolean(inputValue);
            return this;
        }
    }
}
