package com.buildstash;

import hudson.util.Secret;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BuildstashStepTest {

    @Test
    public void testDescriptorProperties() {
        BuildstashStep.DescriptorImpl descriptor = new BuildstashStep.DescriptorImpl();
        
        assertEquals("buildstash", descriptor.getFunctionName());
        assertEquals("Upload to Buildstash", descriptor.getDisplayName());
        assertTrue(descriptor.isAdvanced());
    }

    @Test
    public void testStepCreation() {
        BuildstashStep step = new BuildstashStep();
        step.setApiKey(Secret.fromString("test-key"));
        step.setPrimaryFilePath("test.apk");
        step.setVersionComponent1Major("1");
        step.setVersionComponent2Minor("0");
        step.setVersionComponent3Patch("0");
        step.setPlatform("android");
        step.setStream("default");
        
        assertEquals("test-key", Secret.toString(step.getApiKey()));
        assertEquals("test.apk", step.getPrimaryFilePath());
        assertEquals("1", step.getVersionComponent1Major());
        assertEquals("0", step.getVersionComponent2Minor());
        assertEquals("0", step.getVersionComponent3Patch());
        assertEquals("android", step.getPlatform());
        assertEquals("default", step.getStream());
    }

    @Test
    public void testDefaultValues() {
        BuildstashStep step = new BuildstashStep();
        
        assertEquals("file", step.getStructure());
        assertEquals("git", step.getVcHostType());
        assertEquals("github", step.getVcHost());
    }
}
