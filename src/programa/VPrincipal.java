package programa;

public class VPrincipal extends javax.swing.JFrame {

    public VPrincipal() {
        initComponents();
        iniciar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Imprimir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        comboImpresoras = new javax.swing.JComboBox();
        orientacion = new javax.swing.JComboBox();
        numCopias = new javax.swing.JSpinner();
        nombre = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Imprimir.setText("Imprimir");
        Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImprimirActionPerformed(evt);
            }
        });

        jScrollPane1.setOpaque(false);

        orientacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vertical", "Horizontal", "Horizontal Inv." }));

        numCopias.setValue(1);

        nombre.setText("borjaro2000.tk - Prueba de impresión");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Imprimir)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombre, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(comboImpresoras, 0, 224, Short.MAX_VALUE))))
                        .addGroup(layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(orientacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCopias, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboImpresoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(orientacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(numCopias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 203, Short.MAX_VALUE)
                        .addComponent(Imprimir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImprimirActionPerformed
        //Para mostrar como se usan los métodos, no es necesario usarlos para cada impresión.
        //Mirar las impresora seleccionada
        impresora.cambiarImpresoraSeleccionada((String) comboImpresoras.getSelectedItem());

        //Mirar la orientación
        impresora.cambiarOrientacionPapel(orientacion.getSelectedIndex());

        //Mirar el número de copias
        impresora.cambiarNumeroCopias((Integer) numCopias.getValue());

        //Cambiar nombre de impresión
        impresora.cambiarNombreImpresion(nombre.getText());

        //Mandamos imprimir
        impresora.imprimir(imprimible);
    }//GEN-LAST:event_ImprimirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Imprimir;
    private javax.swing.JComboBox comboImpresoras;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField nombre;
    private javax.swing.JSpinner numCopias;
    private javax.swing.JComboBox orientacion;
    // End of variables declaration//GEN-END:variables
    //variables
    private Imprimible imprimible; //Lo que vamos a imprimir
    private ImpresoraGrafica impresora; //Representa el trabajo de impresión. Impresora, orientación del papel, nº copias...

    private void iniciar() {
        //Poner título a la ventana
        this.setTitle("Prueba Impresión");

        //Creamos el trabajo de impresión
        impresora = new ImpresoraGrafica();

        //Rellenar los campos
        for (int i = 0; i < impresora.listaImpresoras().size(); i++) {
            comboImpresoras.addItem(impresora.listaImpresoras().get(i));
        }

        //Creamos el objeto donde pondremos lo que queremos imprimir
        imprimible = new Imprimible();

        //Esto es para verlo en pantalla
        jScrollPane1.getViewport().add(imprimible, null);
    }
}
