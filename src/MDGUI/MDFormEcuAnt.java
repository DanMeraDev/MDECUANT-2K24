package MDGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import MDBL.MDAlimento;
import MDBL.MDCarnivoro;
import MDBL.MDGenoAlimento;
import MDBL.MDHerviboro;
import MDBL.MDIngestaNativa;
import MDBL.MDInsectivoro;
import MDBL.MDLarva;
import MDBL.MDNectarivoros;
import MDBL.MDOmnivoro;
import MDBL.X;
import MDBL.XX;
import MDBL.XY;

import java.awt.*;

public class MDFormEcuAnt extends JFrame {

    // Variables globales estáticas
    static String mdCedula = "1850629039";
    static String mdNombres = "Mera Guerra Daniel Alejandro";
    private int mdCurrentRow = 0;

    public MDFormEcuAnt() {
        mdCustomerControls();
    }

    public void mdCustomerControls() {
        // Configuración de la ventana principal
        setTitle("EcuAnt 2K24A");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Sección del logo con tamaño ajustado
        ImageIcon mdLogoIcon = new ImageIcon(getClass().getResource("/MDGUI/Resource/MDLogo.jpeg"));
        Image mdImage = mdLogoIcon.getImage();
        Image mdScaledImage = mdImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        mdLogoIcon = new ImageIcon(mdScaledImage);

        JLabel mdLogoLabel = new JLabel(mdLogoIcon);
        mdLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        add(mdLogoLabel, gbc);

        // Sección de la tabla (JTable con 5 filas y 6 columnas)
        String[] mdColumnNames = {"RegNro", "TipoHormiga", "Sexo", "GenoAlimento","IngestaNativa", "Estado"};
        Object[][] mdData = new Object[5][6]; 

        JTable mdTable = new JTable(mdData, mdColumnNames);
        JScrollPane mdTableScrollPane = new JScrollPane(mdTable);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(mdTableScrollPane, gbc);

        // Sección de dropdowns
        JPanel mdDropdownPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        ArrayList<MDGenoAlimento> mdGenoAlimentoOptions = new ArrayList<>();
        mdGenoAlimentoOptions.add(new X());
        mdGenoAlimentoOptions.add(new XX());
        mdGenoAlimentoOptions.add(new XY());
        // String[] mdIngestaNativaOptions = {"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Nectavívoros"};
        ArrayList<MDIngestaNativa> mdIngestaNativaOptions = new ArrayList<>();
        mdIngestaNativaOptions.add(new MDCarnivoro());
        mdIngestaNativaOptions.add(new MDHerviboro());
        mdIngestaNativaOptions.add(new MDOmnivoro());
        // mdIngestaNativaOptions.add(new MDInsectivoro());
        mdIngestaNativaOptions.add(new MDNectarivoros());
        JComboBox<MDGenoAlimento> mdGenoAlimentoDropdown = new JComboBox<>(mdGenoAlimentoOptions.toArray(new MDGenoAlimento[0]));
        JComboBox<MDIngestaNativa> mdIngestaNativaDropdown = new JComboBox<>(mdIngestaNativaOptions.toArray(new MDIngestaNativa[0]));
        mdDropdownPanel.add(mdGenoAlimentoDropdown);
        mdDropdownPanel.add(mdIngestaNativaDropdown);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        add(mdDropdownPanel, gbc);

        // Sección de botones
        JPanel mdButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton mdCrearLarvaButton = new JButton("Crear Larva");
        JButton mdAlimentarButton = new JButton("Alimentar");
        JButton mdEliminarButton = new JButton("Eliminar");
        JButton mdGuardarButton = new JButton("Guardar");
        mdButtonPanel.add(mdCrearLarvaButton);
        mdButtonPanel.add(mdAlimentarButton);
        mdButtonPanel.add(mdEliminarButton);
        mdButtonPanel.add(mdGuardarButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(mdButtonPanel, gbc);

        // Sección inferior con la cédula y nombres
        JPanel mdInfoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JLabel mdProgramLabel = new JLabel("Programación II");
        JLabel mdCedulaLabel = new JLabel("Cédula: " + mdCedula);
        JLabel mdNombresLabel = new JLabel("Nombres: " + mdNombres);
        mdInfoPanel.add(mdProgramLabel);
        mdInfoPanel.add(mdCedulaLabel);
        mdInfoPanel.add(mdNombresLabel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(mdInfoPanel, gbc);

         // Acción para llenar la tabla al hacer clic en "Crear Larva"
         mdCrearLarvaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(
                    null, 
                    "¿Desea guardar una larva?", 
                    "Confirmar", 
                    JOptionPane.YES_NO_OPTION, 
                    JOptionPane.QUESTION_MESSAGE
                );
                if (response == JOptionPane.YES_OPTION) {
                    if (mdCurrentRow < mdData.length) {
                        mdTable.setValueAt(mdCurrentRow + 1, mdCurrentRow, 0);  // RegNro
                        mdTable.setValueAt(new MDLarva(), mdCurrentRow, 1);  // TipoLarva
                        mdTable.setValueAt("ASEXUAL", mdCurrentRow, 2);  // Sexo
                        mdTable.setValueAt("", mdCurrentRow, 3);  // GenoAlimento vacío
                        mdTable.setValueAt("", mdCurrentRow, 4);  // IngestaNativa vacío
                        mdTable.setValueAt("Activo", mdCurrentRow, 5);  // Estado
                        mdCurrentRow++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden agregar más larvas. La tabla está llena.");
                    }
                }
            }
        });

        // Alimentar
        mdAlimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mdCurrentRow > 0) {
                    mdTable.setValueAt(mdGenoAlimentoDropdown.getSelectedItem(), mdCurrentRow - 1, 3);  // GenoAlimento
                    mdTable.setValueAt(mdIngestaNativaDropdown.getSelectedItem(), mdCurrentRow - 1, 4);  // IngestaNativa
                } else {
                    JOptionPane.showMessageDialog(null, "No hay larvas creadas para alimentar.");
                }
            }
        });

        //Eliminar
        mdEliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpiar todos los registros de la tabla
                for (int i = 0; i < mdData.length; i++) {
                    for (int j = 0; j < mdData[i].length; j++) {
                        mdTable.setValueAt(null, i, j);
                    }
                }
                // Reiniciar el contador de filas
                mdCurrentRow = 0;
            }
        });

        //Guardar
        mdGuardarButton.addActionListener(e -> {
    int rowCount = mdTable.getRowCount();
    int colCount = mdTable.getColumnCount();
    String csvFilePath = new File("Data/MDHormiguero.csv").getAbsolutePath();

    try (FileWriter csvWriter = new FileWriter(csvFilePath)) {
        // Escribir la cabecera
        csvWriter.append("RegNro;TipoLarva;Sexo;GenoAlimento;IngestaNativa;Estado;\n");

        // Escribir los datos de las filas
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Object cellValue = mdTable.getValueAt(i, j);
                csvWriter.append(cellValue != null ? cellValue.toString() : "");
                if (j < colCount - 1) {
                    csvWriter.append(";");
                }
            }
            csvWriter.append("\n");
        }

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Registros guardados exitosamente");
        
        // Borrar los datos de la tabla después de guardar
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                mdTable.setValueAt("", i, j);
            }
        }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los registros.");
            ex.printStackTrace();
        }
    });

        

        

        // Mostrar la ventana
        setVisible(true);
    }
}
