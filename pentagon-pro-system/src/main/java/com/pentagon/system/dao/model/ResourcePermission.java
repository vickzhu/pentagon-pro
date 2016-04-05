package com.pentagon.system.dao.model;

public class ResourcePermission {
    private Long resourceId;

    private String resourceName;

    private Long resourceGroupId;

    private String uris;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public Long getResourceGroupId() {
        return resourceGroupId;
    }

    public void setResourceGroupId(Long resourceGroupId) {
        this.resourceGroupId = resourceGroupId;
    }

    public String getUris() {
        return uris;
    }

    public void setUris(String uris) {
        this.uris = uris == null ? null : uris.trim();
    }
}