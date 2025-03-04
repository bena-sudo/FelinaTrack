package com.pfc.felinatrack_back.model.enums;

public enum RoleName {
    ROLE_ADMIN("Full system access, can manage users, roles, and settings"),
    ROLE_VOLUNTEER("Can view and manage assigned cat colonies"),
    ROLE_VETERINARIAN("Can update cat health records and treatments"),
    ROLE_ADOPTER("Can apply for cat adoption"),
    ROLE_MANAGER("Oversees multiple colonies and volunteer activities"),
    ROLE_SUPERVISOR("Can approve or reject adoptions"),
    ROLE_USER("Accesses the platform and views their accreditation history");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }

    public String getdescription() {
        return description;
    }
}
