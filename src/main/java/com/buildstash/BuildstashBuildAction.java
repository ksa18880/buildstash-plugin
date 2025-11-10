package com.buildstash;

import hudson.model.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * Build action to store Buildstash upload results.
 * This allows the upload results to be displayed on the build page.
 * Supports multiple uploads in a single pipeline run.
 */
public class BuildstashBuildAction implements Action {

    private final List<BuildstashUploadResponse> responses;

    public BuildstashBuildAction(BuildstashUploadResponse response) {
        this.responses = new ArrayList<>();
        if (response != null) {
            this.responses.add(response);
        }
    }

    /**
     * Add a new upload response to the list.
     * @param response The upload response to add
     */
    public void addResponse(BuildstashUploadResponse response) {
        if (response != null) {
            this.responses.add(response);
        }
    }

    @Override
    public String getIconFileName() {
        return "symbol-cube";
    }

    @Override
    public String getDisplayName() {
        return "Buildstash Upload";
    }

    @Override
    public String getUrlName() {
        return "buildstash";
    }

    /**
     * Get all upload responses.
     * @return List of all upload responses
     */
    public List<BuildstashUploadResponse> getResponses() {
        return responses;
    }

    /**
     * Get the first response (for backward compatibility).
     * @return The first upload response, or null if none
     */
    public BuildstashUploadResponse getResponse() {
        return responses.isEmpty() ? null : responses.get(0);
    }

    /**
     * Check if there are any responses.
     * @return true if there are responses, false otherwise
     */
    public boolean hasResponses() {
        return !responses.isEmpty();
    }

    /**
     * Check if any builds are pending processing.
     * @return true if any build has pending processing, false otherwise
     */
    public boolean hasAnyPendingProcessing() {
        return responses.stream().anyMatch(BuildstashUploadResponse::isPendingProcessing);
    }
} 