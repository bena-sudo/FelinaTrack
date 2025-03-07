package com.pfc.felinatrack_back.model.enums;

public enum RoleName {
    ROL_ADMINISTRADOR("Acceso completo al sistema, puede gestionar usuarios, roles y configuraciones"),
    ROL_VOLUNTARIO("Puede ver y gestionar las colonias de gatos asignadas"),
    ROL_GESTOR_COLONIA("Supervisa múltiples colonias y actividades de voluntarios"),
    ROL_GESTOR_USUARIOS("Puede crear, asignar y gestionar usuarios y sus roles"),
    ROL_SUPERVISOR("Puede aprobar o rechazar adopciones"),
    ROL_USUARIO("Accede a la plataforma y ve su historial de acreditación");

    private final String description;

    RoleName(String description) {
        this.description = description;
    }

    public String getdescription() {
        return description;
    }
}
