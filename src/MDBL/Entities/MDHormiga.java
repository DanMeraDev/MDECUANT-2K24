package MDBL.Entities;

public abstract class MDHormiga implements IHormiga{
    private Integer id;
    private String tipo;
    private String sexo;
    private String estado;

    

    public MDHormiga() {
    }
    public MDHormiga(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    public MDHormiga(Integer id, String tipo, String sexo, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.sexo = sexo;
        this.estado = estado;
    }
    @Override
    public String toString() {
        return tipo.toUpperCase();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }   

    @Override
    public MDHormiga comer(MDIngestaNativa ingestaNativa) {
        // Supongamos que la larva solo sobrevive si es alimentada con un tipo específico de GenoAlimento
        if (ingestaNativa.getGenoAlimentoInyectado() != null && ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("X")) {
            // La larva sobrevive y se convierte en otro tipo de hormiga
            this.setEstado("VIVA");
            this.setTipo("Hormiga Obrera");
        } else {
            // La larva muere si el GenoAlimento no es compatible
            this.setEstado("MUERTA");
        }
        return this;
    }
}
