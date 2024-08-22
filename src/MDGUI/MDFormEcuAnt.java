package MDGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import Infra.AppException;
import MDBL.HormigueroBL;
import MDBL.Entities.MDAlimento;
import MDBL.Entities.MDCarnivoro;
import MDBL.Entities.MDGenoAlimento;
import MDBL.Entities.MDHerviboro;
import MDBL.Entities.MDHormiga;
import MDBL.Entities.MDIngestaNativa;
import MDBL.Entities.MDInsectivoro;
import MDBL.Entities.MDLarva;
import MDBL.Entities.MDNectarivoros;
import MDBL.Entities.MDOmnivoro;
import MDBL.Entities.X;
import MDBL.Entities.XX;
import MDBL.Entities.XY;
import MDDAC.HormigueroDAO;

import java.awt.*;

public class MDFormEcuAnt extends JFrame {

    // Variables globales estáticas
    static String mdCedula = "1850629039";
    static String mdNombres = "Mera Guerra Daniel Alejandro";
    private int mdCurrentRow = 0;
     private HormigueroBL hormigueroBL = new HormigueroBL();

    public MDFormEcuAnt() {
        mdCustomerControls();
    }

    private void mdCustomerControls() {
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
        String[] mdColumnNames = {"IdHormiga", "TipoHormiga", "Sexo","Alimentación", "Estado"};
        Object[][] mdData = new Object[5][5]; 

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
        mdIngestaNativaOptions.add(new MDInsectivoro());
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
                try {
                    // Crear la larva usando HormigueroBL
                    String result = hormigueroBL.crearLarva();
                    JOptionPane.showMessageDialog(null, result);

                    // Obtener la última larva agregada a la lista
                    MDHormiga larva = hormigueroBL.lstHormiguero.get(hormigueroBL.lstHormiguero.size() - 1);

                    // Mostrar la larva en la tabla
                    if (mdCurrentRow < mdData.length) {
                        mdTable.setValueAt(larva.getId(), mdCurrentRow, 0);  // idHormiga
                        mdTable.setValueAt(larva.getTipo(), mdCurrentRow, 1);  // TipoLarva
                        mdTable.setValueAt(larva.getSexo(), mdCurrentRow, 2);  // Sexo
                        mdTable.setValueAt("", mdCurrentRow, 3);  // GenoAlimento vacío
                        mdTable.setValueAt(larva.getEstado(), mdCurrentRow, 4);  // Estado
                        mdCurrentRow++;
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pueden agregar más larvas. La tabla está llena.");
                    }
                } catch (AppException ex) {
                    JOptionPane.showMessageDialog(null, "Error al crear larva: " + ex.getMessage());
                }
            }
        }
    });

        // Alimentar
        mdAlimentarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mdCurrentRow > 0) {
                    int idHormiga = (int) mdTable.getValueAt(mdCurrentRow - 1, 0);
                    String geno = mdGenoAlimentoDropdown.getSelectedItem().toString();
                    String nativa = mdIngestaNativaDropdown.getSelectedItem().toString();
        
                    try {
                        String result = hormigueroBL.alimentarHormiga(idHormiga, geno, nativa);
                        JOptionPane.showMessageDialog(null, result);
        
                        // Actualizar la tabla con el nuevo estado de la hormiga
                        MDHormiga hormiga = hormigueroBL.lstHormiguero.get(mdCurrentRow - 1);
                        mdTable.setValueAt(hormiga.getTipo(), mdCurrentRow - 1, 1);
                        mdTable.setValueAt(hormiga.getEstado(), mdCurrentRow - 1, 4);
                        mdTable.setValueAt(geno + " - " + nativa, mdCurrentRow - 1, 3);  // Actualizar Alimentación
                    } catch (AppException ex) {
                        JOptionPane.showMessageDialog(null, "Error al alimentar hormiga: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No hay larvas creadas para alimentar.");
                }
            }
        });

        //Eliminar
        mdEliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si hay alguna fila seleccionada
                int selectedRow = mdTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Obtener el tipo de hormiga de la fila seleccionada
                    String tipoHormiga = (String) mdTable.getValueAt(selectedRow, 1);
        
                    // Mostrar el diálogo de confirmación
                    int respuesta = JOptionPane.showConfirmDialog(
                        null,
                        "¿Está seguro de eliminar la " + tipoHormiga + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );
        
                    // Si la respuesta es "Sí", cambiar el estado de la hormiga a "muerta"
                    if (respuesta == JOptionPane.YES_OPTION) {
                        MDHormiga hormiga = hormigueroBL.lstHormiguero.get(selectedRow);
                        hormiga.setEstado("MUERTA");
        
                        // Actualizar la tabla para reflejar el nuevo estado
                        mdTable.setValueAt(hormiga.getEstado(), selectedRow, 4);
        
                        JOptionPane.showMessageDialog(null, "La hormiga ha sido eliminada (cambiada a estado muerta).");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione una hormiga para eliminar.");
                }
            }
        });

    // Guardar
    mdGuardarButton.addActionListener(e -> {
    int rowCount = mdTable.getRowCount();
    int colCount = mdTable.getColumnCount();
    StringBuilder fullDataHormiguero = new StringBuilder();
    
    // Escribir la cabecera
    fullDataHormiguero.append("IdHormiga;TipoHormiga;Sexo;Alimentación;Estado;\n");
    
    // Escribir los datos de las filas
    for (int i = 0; i < rowCount; i++) {
        for (int j = 0; j < colCount; j++) {
            Object cellValue = mdTable.getValueAt(i, j);
            fullDataHormiguero.append(cellValue != null ? cellValue.toString() : "");
            if (j < colCount - 1) {
                fullDataHormiguero.append(";");
            }
        }
        fullDataHormiguero.append("\n");
    }

    try {
        HormigueroDAO hormigueroDAC = new HormigueroDAO();
        hormigueroDAC.saveHormigueroToCSV(fullDataHormiguero.toString());

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Registros guardados exitosamente");

    } catch (AppException ex) {
        JOptionPane.showMessageDialog(this, "Error al guardar los registros.");
        ex.printStackTrace();
        }
    });


        

        

        // Mostrar la ventana
        setVisible(true);
    }
}
