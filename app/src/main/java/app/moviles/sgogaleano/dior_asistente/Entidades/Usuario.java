package app.moviles.sgogaleano.dior_asistente.Entidades;

import java.util.Date;

public class Usuario {

    private Integer id;
    private Date fecha;
    private String nitCliente;

    public Usuario(Integer id, Date fecha, String nitCliente) {
        this.id = id;
        this.fecha = fecha;
        this.nitCliente = nitCliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getnitCliente() {
        return nitCliente;
    }

    public void setnitCliente(String nombre) {
        this.nitCliente = nitCliente;
    }

    public Date getfecha() {
        return fecha;
    }

    public void setfecha(Date fecha) {
        this.fecha= fecha;
    }
}