package MDBL.Entities;

public class MDRastreadora extends MDHormiga {

    public MDRastreadora(int id) {
        super(id, "Rastreadora");
        this.setEstado("VIVA");
        this.setSexo("HEMBRA");
    }

    @Override
    public MDHormiga comer(MDIngestaNativa ingestaNativa) {
        if (ingestaNativa instanceof MDHerviboro && ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("XX")) {
            return this;
        }

        this.setEstado("MUERTA");
        return this;
    }
}
