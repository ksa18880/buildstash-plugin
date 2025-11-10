package com.buildstash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Data model for Buildstash upload response.
 * Represents the response from the Buildstash API after a successful upload.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BuildstashUploadResponse implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String message;
    
    @JsonProperty("build_id")
    private String buildId;
    
    @JsonProperty("pending_processing")
    private boolean pendingProcessing;
    
    @JsonProperty("build_info_url")
    private String buildInfoUrl;
    
    @JsonProperty("download_url")
    private String downloadUrl;
    
    private BuildInfo build;

    // Default constructor for JSON deserialization
    public BuildstashUploadResponse() {}

    public BuildstashUploadResponse(String message, String buildId, boolean pendingProcessing, String buildInfoUrl, String downloadUrl) {
        this.message = message;
        this.buildId = buildId;
        this.pendingProcessing = pendingProcessing;
        this.buildInfoUrl = buildInfoUrl;
        this.downloadUrl = downloadUrl;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getBuildId() { return buildId; }
    public void setBuildId(String buildId) { this.buildId = buildId; }

    public boolean isPendingProcessing() { return pendingProcessing; }
    public void setPendingProcessing(boolean pendingProcessing) { this.pendingProcessing = pendingProcessing; }

    public String getBuildInfoUrl() { return buildInfoUrl; }
    public void setBuildInfoUrl(String buildInfoUrl) { this.buildInfoUrl = buildInfoUrl; }

    public String getDownloadUrl() { return downloadUrl; }
    public void setDownloadUrl(String downloadUrl) { this.downloadUrl = downloadUrl; }

    public BuildInfo getBuild() { return build; }
    public void setBuild(BuildInfo build) { this.build = build; }
    
    /**
     * Get the platform short name from the build object.
     * @return Platform short name, or null if not available
     */
    public String getPlatformShortName() {
        return build != null && build.getPlatform() != null ? build.getPlatform().getShortName() : null;
    }

    /**
     * Nested class for build information from the API response.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BuildInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private PlatformInfo platform;
        
        public PlatformInfo getPlatform() { return platform; }
        public void setPlatform(PlatformInfo platform) { this.platform = platform; }
    }
    
    /**
     * Nested class for platform information.
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PlatformInfo implements Serializable {
        private static final long serialVersionUID = 1L;
        
        @JsonProperty("short_name")
        private String shortName;
        
        public String getShortName() { return shortName; }
        public void setShortName(String shortName) { this.shortName = shortName; }
    }
} 