/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ISociosDAO;

/**
 *
 * @author lv1013
 */
public class SocioForm extends javax.swing.JFrame {
    
    private final ISociosDAO socioDAO;
    
    /**
     * Creates new form SocioForm
     * @param socioDAO
     */
    public SocioForm(ISociosDAO socioDAO) {
        initComponents();
        this.socioDAO = socioDAO;
        this.llenarTabla();
    }
    
    private void guardar(){
        if(this.txtIDSocio.getText().isEmpty()){
            this.agregar();
        }else{
            this.actualizar();
        }
        
    }
    
    private void agregar(){
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        //TODO: Agregar validaciones
        Socio socioNuevo = new Socio(nombre, curp);
        boolean seAgregoSocio = this.socioDAO.agregar(socioNuevo);
        if(seAgregoSocio){
            JOptionPane.showMessageDialog(this, "Se agregó el nuevo socio", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarFormulario();
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "No fue posible agregar el socio", "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizar(){
        Long idSocio = Long.parseLong(this.txtIDSocio.getText());
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        boolean seActualizo = this.socioDAO.actualizar(new Socio(idSocio, nombre, curp));
        
        if(seActualizo){
            JOptionPane.showMessageDialog(this, "Se actualizó el socio", "Información", JOptionPane.INFORMATION_MESSAGE);
            this.limpiarFormulario();
            this.llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "No fue posible actualizar el socio", "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void llenarTabla(){
        List<Socio> listaSocios = this.socioDAO.consultarTodos();
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblSocios.getModel();
        modeloTabla.setRowCount(0);
        listaSocios.forEach(socio -> {
            Object[] fila = new Object[3];
            fila[0] = socio.getIdSocio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getCurp();
            modeloTabla.addRow(fila);
        });
    }
    
    private void limpiarFormulario(){
        this.txtIDSocio.setText("");
        this.txtNombre.setText("");
        this.txtCurp.setText("");
    }
    
    private void eliminar(){
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if(idSocioSeleccionado == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Información", JOptionPane.WARNING_MESSAGE);
        }else{
            int opcionSeleccionada = JOptionPane.showConfirmDialog(this, "¿Estás seguro de elimianr al socio?", "Confirmación", JOptionPane.YES_NO_OPTION);
            
            if(opcionSeleccionada == JOptionPane.NO_OPTION){
                return;
            }
            
            boolean seEliminoSocio = this.socioDAO.eliminar(idSocioSeleccionado);
            
            if(seEliminoSocio){
                JOptionPane.showMessageDialog(this, "Se eliminó el socio", "Información", JOptionPane.INFORMATION_MESSAGE);
                this.llenarTabla();
            }else{               
                JOptionPane.showMessageDialog(this, "No se puedo eliminar el socio", "Información", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void editar(){
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if(idSocioSeleccionado == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Información", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Socio socio = this.socioDAO.consultar(idSocioSeleccionado);
        if(socio != null){
            this.llenarFormulario(socio);
        }
    }
    
    private void llenarFormulario(Socio socio){
        this.txtIDSocio.setText(socio.getIdSocio().toString());
        this.txtNombre.setText(socio.getNombre());
        this.txtCurp.setText(socio.getCurp());
    }
    
    private Long getIdSocioSeleccionado(){
        int indiceFilaSeleccionada = this.tblSocios.getSelectedRow();
        if(indiceFilaSeleccionada != -1){
            DefaultTableModel modelo = (DefaultTableModel) this.tblSocios.getModel();
            int indiceColumna = 0;
            Long idSocioSeleccionado = (Long) modelo.getValueAt(indiceFilaSeleccionada, indiceColumna);
            return idSocioSeleccionado;
        }else{
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        lblIDSocio = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        txtIDSocio = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlSocios = new javax.swing.JScrollPane();
        tblSocios = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Control de Socios");

        lblIDSocio.setText("ID Socio");

        lblNombre.setText("Nombre");

        lblCurp.setText("CURP");

        txtIDSocio.setEditable(false);
        txtIDSocio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDSocioActionPerformed(evt);
            }
        });

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtCurp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCurpActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        tblSocios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Socio", "Nombre", "Curp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlSocios.setViewportView(tblSocios);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(84, 84, 84)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIDSocio)
                            .addComponent(lblNombre)
                            .addComponent(lblCurp))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIDSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre)
                            .addComponent(txtCurp, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))))
                .addGap(82, 82, 82)
                .addComponent(pnlSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addGap(39, 39, 39)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIDSocio)
                            .addComponent(txtIDSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombre))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCurp)
                            .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar))
                        .addGap(50, 50, 50))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIDSocioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDSocioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDSocioActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCurpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCurpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCurpActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lblIDSocio;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JScrollPane pnlSocios;
    private javax.swing.JTable tblSocios;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtIDSocio;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
