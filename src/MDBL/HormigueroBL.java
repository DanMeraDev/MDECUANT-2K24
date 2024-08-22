package MDBL;

import Infra.AppException;
import MDBL.Entities.MDCarnivoro;
import MDBL.Entities.MDGenoAlimento;
import MDBL.Entities.MDHerviboro;
import MDBL.Entities.MDHormiga;
import MDBL.Entities.MDIngestaNativa;
import MDBL.Entities.MDLarva;
import MDBL.Entities.MDNectarivoros;
import MDBL.Entities.MDOmnivoro;
import MDBL.Entities.X;
import MDBL.Entities.XX;
import MDBL.Entities.XY;

import java.util.ArrayList;

public class HormigueroBL {
    public ArrayList<MDHormiga> lstHormiguero = new ArrayList<>();

    public String crearLarva() throws AppException {
        MDHormiga hormiga = new MDLarva(lstHormiguero.size() + 1);
        lstHormiguero.add(hormiga);
        return "HORMIGA LARVA, agregada al hormiguero";
    }

    public String alimentarHormiga(int idHormiga, String alimentoGeno, String alimentoNativo) throws AppException {
        MDGenoAlimento aGeno;
        MDIngestaNativa aNativo;
        MDHormiga hormiga = null;

        switch (alimentoGeno) {
            case "XX":
                aGeno = new XX();
                break;
            case "XY":
                aGeno = new XY();
                break;
            default:
                aGeno = new X();
                break;
        }

        System.out.println("alimentoNativo: " + alimentoNativo); // Línea de depuración

        switch (alimentoNativo.toLowerCase()) {
            case "carnivoro":
                aNativo = new MDCarnivoro();
                break;
            case "herviboro":
                aNativo = new MDHerviboro();
                break;
            case "Omnivoro":
                aNativo = new MDOmnivoro();
                break;
            case "nectarivoros":
                aNativo = new MDNectarivoros();
                break;
            default:
                return "Ingesta nativa inválida";
        }

        for (MDHormiga h : lstHormiguero) {
            if (h.getId() == idHormiga) {
                hormiga = h;
                break;
            }
        }

        if (hormiga == null || hormiga.getEstado().equals("MUERTA")) {
            return "Ups...! Hormiga no válida o ya está muerta.";
        }

        aNativo.inyectar(aGeno);
        MDHormiga nuevaHormiga = hormiga.comer(aNativo);
        lstHormiguero.set(lstHormiguero.indexOf(hormiga), nuevaHormiga);

        return nuevaHormiga.getTipo() + " Alimentada";
    }
}
