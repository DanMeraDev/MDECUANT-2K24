package MDBL.Entities;

import Infra.AppException;

public class EntomologoImpl implements IEntomologo{
    @Override
    public void educar(MDHormiga hormiga) throws AppException {
        if (hormiga.getTipo().equals("Larva")) {
            throw new AppException("Las larvas no pueden ser entrenadas.");
        }
        hormiga.setEntrenada(true);  
    }
}
