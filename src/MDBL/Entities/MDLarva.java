package MDBL.Entities;

public class MDLarva extends MDHormiga {

    public MDLarva(int id) {
        super(id, "Larva");
        this.setSexo("Asexual");  // Comienza como Asexual
        this.setEstado("VIVA");
    }

    @Override
    public MDHormiga comer(MDIngestaNativa ingestaNativa) {
        if (ingestaNativa instanceof MDHerviboro && ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("XX")) {
            MDRastreadora rastreadora = new MDRastreadora(this.getId());
            rastreadora.setSexo("Hembra");  // Cambio a Hembra
            return rastreadora;
        }

        if (ingestaNativa instanceof MDNectarivoros &&
            (ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("X") ||
             ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("XX") ||
             ingestaNativa.getGenoAlimentoInyectado().getTipo().equals("XY"))) {
            return this;  // Sin cambios, la larva sigue viva
        }

        this.setEstado("MUERTA");
        return this;
    }
}
