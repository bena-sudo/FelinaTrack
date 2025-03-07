package com.pfc.felinatrack_back.model.enums;

public enum PermissionName {
    EDITAR_USUARIO("Permite editar la información de los usuarios"),
    ELIMINAR_USUARIO("Permite eliminar usuarios"),
    VER_USUARIOS("Permite ver la lista de usuarios"),
    VER_USUARIO("Permite ver la información de un usuario"),
    ASIGNAR_ROLES("Permite asignar roles a los usuarios");

    private final String descripcion;

    PermissionName(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
