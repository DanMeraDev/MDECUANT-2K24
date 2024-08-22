package MDDAC;

import java.io.FileWriter;
import java.io.IOException;

import Infra.AppException;
import Infra.Config;

public class HormigueroDAO {

    public void saveHormigueroToCSV(String fullDataHormiguero) throws AppException {
        try (FileWriter writer = new FileWriter(Config.DATAFILE)) {
            writer.write(fullDataHormiguero);
        } catch (IOException e) {
            throw new AppException("Error al guardar el archivo CSV");
        }
    }
}
