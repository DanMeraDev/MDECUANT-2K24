package MDBL.Entities;

public abstract class MDAlimento {
    private String tipo;

    @Override
    public String toString() {
        if(tipo.equals(null)) {
            tipo = "";
        }   
        return tipo.toUpperCase();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
