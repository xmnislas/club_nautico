/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author xmnis
 */
public class Socio {
    private Long idSocio;
    private String nombre, curp;

    public Socio() {
    }

    public Socio(String nombre, String curp) {
        this.nombre = nombre;
        this.curp = curp;
    }

    public Socio(Long idSocio, String nombre, String curp) {
        this.idSocio = idSocio;
        this.nombre = nombre;
        this.curp = curp;
    }

    public Long getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Long idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idSocio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Socio other = (Socio) obj;
        if (!Objects.equals(this.idSocio, other.idSocio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Socio{" + "idSocio=" + idSocio + ", nombre=" + nombre + ", curp=" + curp + '}';
    }
    
}
