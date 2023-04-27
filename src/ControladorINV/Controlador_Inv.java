/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ControladorINV;

import ModeloINV.Frm_MAR;
import ModeloINV.Frm_Mat;
import VistaINV.Frm_INV;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesar
 */
public class Controlador_Inv implements ActionListener {

    Frm_Mat Fr = new Frm_Mat();
    Frm_MAR Mar = new Frm_MAR();
    Frm_INV vis = new Frm_INV();
    DefaultTableModel modelotb = new DefaultTableModel();

    private int cod, cant;
    private String marc, arti, anot, fil, valor;

    public Controlador_Inv(Frm_INV vis) {
        this.vis = vis;
        vis.setVisible(true);
        addevents();
        listart();
    }

    private void addevents() {
        vis.getBtnSAVE().addActionListener(this);
        vis.getBtnSEARCH().addActionListener(this);
        vis.getBtnCLEAN().addActionListener(this);
        vis.getBtnDELETE().addActionListener(this);
        vis.getBtnUPDATE().addActionListener(this);
        vis.getjTable1().addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent x) {
                llcampo(x);

            }
        });
    }

    private void listart() {
        String[] titulos = new String[]{"ID", "ARTICULO", "MARCA", "CANTIDAD", "ANOTACION", "FECHA_CREACION"};
        modelotb = new DefaultTableModel(titulos, 0);
        List<Frm_Mat> ListaFR = Mar.Listar();
        for (Frm_Mat Fr : ListaFR) {
            modelotb.addRow(new Object[]{Fr.getId(), Fr.getArticulo(), Fr.getMarca(), Fr.getCant(), Fr.getAnot(), Fr.getCrea()});
        }
        vis.getjTable1().setModel(modelotb);
        vis.getjTable1().setPreferredSize(new Dimension(350, modelotb.getRowCount() * 16));
    }

    private void llcampo(MouseEvent x) {

        JTable target = (JTable) x.getSource();
        //int cod;
        cod = (int) vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 0);
        //System.out.println(cod);
        vis.getTxtMARCA().setText(vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 2).toString());
        vis.getTxtARTICULO().setText(vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 1).toString());
        vis.getTxtCANTIDAD().setText(vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 3).toString());
        vis.getTxtANOT().setText(vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 4).toString());
        /*vis.getTxtARTICULO().setText(vis.getjTable1().getModel().getValueAt(target.getSelectedRow(), 5).toString());*/
    }

    private boolean valid() {
        if ("".equals(vis.getTxtARTICULO())) {
            JOptionPane.showMessageDialog(null, "El campo no puede estar vacio", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean cardat() {
        try {
            cant = Integer.parseInt(vis.getTxtCANTIDAD().getText());
            marc = vis.getTxtMARCA().getText();
            arti = vis.getTxtARTICULO().getText();
            anot = vis.getTxtANOT().getText();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error al cargar datos: " + e);
            return false;
        }

    }

    private void limpiar() {

        vis.getTxtMARCA().setText("");
        vis.getTxtARTICULO().setText("");
        vis.getTxtCANTIDAD().setText("");
        vis.getTxtANOT().setText("");
        cant = 0;
        marc = "";
        arti = "";
        anot = "";
    }

    private void agreAr() {
        try {
            if (valid()) {
                if (cardat()) {
                    Frm_Mat pro = new Frm_Mat(cant, marc, arti, anot);
                    Mar.agregar(pro);
                    //Mar.del(pro);
                    //Mar.del1(pro);
                    //Mar.del2(pro);
                    //Mar.del3(pro);
                    JOptionPane.showMessageDialog(null, "Registro exitoso");
                    limpiar();
                }
            }
        } catch (HeadlessException e) {
            System.out.println("Error en la carga: " + e);
        } finally {
            listart();
        }

    }

    private void actu() {
        try {
            if (valid()) {
                if (cardat()) {
                    Frm_Mat pro = new Frm_Mat(cod, cant, marc, arti, anot);
                    Mar.act(pro);
                    JOptionPane.showMessageDialog(null, "Registro actualizado");
                    limpiar();
                }

            }
        } catch (HeadlessException e) {
            System.out.println("Error en actu: " + e);

        } finally {
            listart();
        }

    }

    private void delete() {
        try {
            if (cod != 0) {
                Mar.delete(cod);
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "debe selecionar un pro", "error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (HeadlessException e) {

            System.out.println("Error en delete cont: " + e);
        } finally {
            listart();
        }

    }

    private void filtro() {

        try {
            fil = vis.getBtnFiltro().getSelectedItem().toString();
            System.out.println(fil);
            valor = vis.getTxtBUSCAR().getText();
            System.out.println(valor);
            Mar.search(fil, valor);
        } catch (Exception e) {
            System.out.println("Error en filtro: " + e);
        } finally {
            String[] titulos = new String[]{"ID", "ARTICULO", "MARCA", "CANTIDAD", "ANOTACION", "FECHA_CREACION"};
            modelotb = new DefaultTableModel(titulos, 0);
            List<Frm_Mat> ListaFR = Mar.search(fil, valor);
            for (Frm_Mat Fr : ListaFR) {
                modelotb.addRow(new Object[]{Fr.getId(), Fr.getArticulo(), Fr.getMarca(), Fr.getCant(), Fr.getAnot(), Fr.getCrea()});

                vis.getjTable1().setModel(modelotb);
                vis.getjTable1().setPreferredSize(new Dimension(350, modelotb.getRowCount() * 16));
            }

        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vis.getBtnSAVE()) {
            agreAr();

        }
        if (e.getSource() == vis.getBtnUPDATE()) {
            actu();
        }

        if (e.getSource() == vis.getBtnCLEAN()) {
            limpiar();
        }
        if (e.getSource() == vis.getBtnDELETE()) {
            delete();
        }
        if (e.getSource() == vis.getBtnSEARCH()) {
            filtro();
        }
    }

}
