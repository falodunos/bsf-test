package com.bsf.resource.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Component
@Configuration
@ConfigurationProperties(prefix = "rest.security")
public class SecurityProperties {

    private boolean enabled;
    private String apiMatcher;
    private Cors cors;
    private String issuerUri;

    /**
     * @return CorsConfiguration
     */
    public CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(cors.getAllowedOrigins());
        corsConfiguration.setAllowedMethods(cors.getAllowedMethods());
        corsConfiguration.setAllowedHeaders(cors.getAllowedHeaders());
        corsConfiguration.setExposedHeaders(cors.getExposedHeaders());
        corsConfiguration.setAllowCredentials(cors.getAllowCredentials());
        corsConfiguration.setMaxAge(cors.getMaxAge());

        return corsConfiguration;
    }

    /**
     * @return boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return String
     */
    public String getApiMatcher() {
        return apiMatcher;
    }

    public void setApiMatcher(String apiMatcher) {
        this.apiMatcher = apiMatcher;
    }

    /**
     * @return Cors
     */
    public Cors getCors() {
        return cors;
    }

    /**
     * @param cors
     */
    public void setCors(Cors cors) {
        this.cors = cors;
    }

    /**
     * @return String
     */
    public String getIssuerUri() {
        return issuerUri;
    }

    /**
     * @param issuerUri
     */
    public void setIssuerUri(String issuerUri) {
        this.issuerUri = issuerUri;
    }

    public static class Cors {

        private List<String> allowedOrigins;
        private List<String> allowedMethods;
        private List<String> allowedHeaders;
        private List<String> exposedHeaders;
        private Boolean allowCredentials;
        private Long maxAge;

        /**
         * @return List
         */
        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }

        /**
         * @param allowedOrigins
         */
        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        /**
         * @return List
         */
        public List<String> getAllowedMethods() {
            return allowedMethods;
        }

        /**
         * @param allowedMethods
         */
        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = allowedMethods;
        }


        /**
         * @return List
         */
        public List<String> getAllowedHeaders() {
            return allowedHeaders;
        }


        /**
         * @param allowedHeaders
         */
        public void setAllowedHeaders(List<String> allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        /**
         * @return List
         */
        public List<String> getExposedHeaders() {
            return exposedHeaders;
        }

        /**
         * @param exposedHeaders
         */
        public void setExposedHeaders(List<String> exposedHeaders) {
            this.exposedHeaders = exposedHeaders;
        }

        /**
         * @return Boolean
         */
        public Boolean getAllowCredentials() {
            return allowCredentials;
        }

        /**
         * @param allowCredentials
         */
        public void setAllowCredentials(Boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }

        /**
         * @return Long
         */
        public Long getMaxAge() {
            return maxAge;
        }

        /**
         * @param maxAge
         */
        public void setMaxAge(Long maxAge) {
            this.maxAge = maxAge;
        }
    }
}
