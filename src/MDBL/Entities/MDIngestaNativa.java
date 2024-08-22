package MDBL.Entities;

public abstract class MDIngestaNativa extends MDAlimento implements IIngestaNativa {
    private MDGenoAlimento genoAlimentoInyectado;

    @Override
    public boolean inyectar(MDGenoAlimento genoAlimento) {
        if (genoAlimento != null) {
            this.genoAlimentoInyectado = genoAlimento;
            return true;
        }
        return false;
    }

    public MDGenoAlimento getGenoAlimentoInyectado() {
        return this.genoAlimentoInyectado;
    }
}
