/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package views;

import Models.ModeloTablaProducto;
import Models.Productos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import jpaControllers.ProductosJpaController;

/**
 *
 * @author miguel
 */
public class Cliente extends javax.swing.JDialog {

    /**
     * Creates new form Cliente
     *
     * @param parent
     * @param modal
     */
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("repaso_DAWFoodFinal_jar_1.0-SNAPSHOTPU");
    private static final ProductosJpaController pjc = new ProductosJpaController(emf);
    private Map<Productos, Integer> carritoMap = new HashMap<>();

    public Cliente(Programa parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        cargarDatosJTable();
    }
    
    public Map<Productos, Integer> getCarritoMap(){
        return this.carritoMap;
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonVerCarrito = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabelMostrarComidas = new javax.swing.JLabel();
        jLabelMostrarBebidas = new javax.swing.JLabel();
        jLabelMostrarPostres = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProductos = new javax.swing.JTable();
        jButtonAniadirAlCarrito = new javax.swing.JButton();
        jSpinnerCantProd = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jButtonCerrar.setBackground(new java.awt.Color(153, 0, 0));
        jButtonCerrar.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCerrar.setText("Cerrar");
        jButtonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCerrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("¡ Seleccione el tipo de producto que desea ver !");

        jButtonVerCarrito.setBackground(new java.awt.Color(0, 153, 153));
        jButtonVerCarrito.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonVerCarrito.setForeground(new java.awt.Color(255, 255, 255));
        jButtonVerCarrito.setText("Ver carrito");
        jButtonVerCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerCarritoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Comida");

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Bebida");

        jLabel6.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Postre");

        jLabelMostrarComidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow-square-solid-24.png"))); // NOI18N
        jLabelMostrarComidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMostrarComidasMouseClicked(evt);
            }
        });

        jLabelMostrarBebidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow-square-solid-24.png"))); // NOI18N
        jLabelMostrarBebidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMostrarBebidasMouseClicked(evt);
            }
        });

        jLabelMostrarPostres.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right-arrow-square-solid-24.png"))); // NOI18N
        jLabelMostrarPostres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMostrarPostresMouseClicked(evt);
            }
        });

        jTableProductos.setBackground(new java.awt.Color(204, 204, 204));
        jTableProductos.setForeground(new java.awt.Color(51, 51, 51));
        jTableProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableProductos);

        jButtonAniadirAlCarrito.setBackground(new java.awt.Color(255, 153, 153));
        jButtonAniadirAlCarrito.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        jButtonAniadirAlCarrito.setForeground(new java.awt.Color(0, 0, 0));
        jButtonAniadirAlCarrito.setText("Añadir al Carrito");
        jButtonAniadirAlCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAniadirAlCarritoActionPerformed(evt);
            }
        });

        jSpinnerCantProd.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        jSpinnerCantProd.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMostrarComidas))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMostrarPostres))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelMostrarBebidas)))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAniadirAlCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinnerCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(jButtonVerCarrito)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCerrar)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel5, jLabel6});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelMostrarComidas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelMostrarBebidas)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6))
                            .addComponent(jLabelMostrarPostres)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCerrar)
                    .addComponent(jSpinnerCantProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAniadirAlCarrito)
                    .addComponent(jButtonVerCarrito))
                .addGap(16, 16, 16))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonAniadirAlCarrito, jButtonVerCarrito});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCerrarActionPerformed

    private void jButtonVerCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerCarritoActionPerformed
        new VentanaCarrito(this, true).setVisible(true);
    }//GEN-LAST:event_jButtonVerCarritoActionPerformed

    private void jLabelMostrarComidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMostrarComidasMouseClicked
        cargarDatosJTableComida();
        Icon icon = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24(1).png");
        Icon icon2 = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24.png");
        jLabelMostrarComidas.setIcon(icon);
        jLabelMostrarBebidas.setIcon(icon2);
        jLabelMostrarPostres.setIcon(icon2);
    }//GEN-LAST:event_jLabelMostrarComidasMouseClicked

    private void jLabelMostrarBebidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMostrarBebidasMouseClicked
        cargarDatosJTableBebida();
        Icon icon = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24(1).png");
        Icon icon2 = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24.png");
        jLabelMostrarBebidas.setIcon(icon);
        jLabelMostrarPostres.setIcon(icon2);
        jLabelMostrarComidas.setIcon(icon2);
    }//GEN-LAST:event_jLabelMostrarBebidasMouseClicked

    private void jLabelMostrarPostresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMostrarPostresMouseClicked
        cargarDatosJTablePostre();
        Icon icon = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24(1).png");
        Icon icon2 = new ImageIcon("./src/main/resources/images/right-arrow-square-solid-24.png");
        jLabelMostrarPostres.setIcon(icon);
        jLabelMostrarBebidas.setIcon(icon2);
        jLabelMostrarComidas.setIcon(icon2);
    }//GEN-LAST:event_jLabelMostrarPostresMouseClicked

    private void jButtonAniadirAlCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAniadirAlCarritoActionPerformed
        aniadirCarrito();
    }//GEN-LAST:event_jButtonAniadirAlCarritoActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAniadirAlCarrito;
    private javax.swing.JButton jButtonCerrar;
    private javax.swing.JButton jButtonVerCarrito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelMostrarBebidas;
    private javax.swing.JLabel jLabelMostrarComidas;
    private javax.swing.JLabel jLabelMostrarPostres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerCantProd;
    private javax.swing.JTable jTableProductos;
    // End of variables declaration//GEN-END:variables

    public void cargarDatosJTable() {
        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();

        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        EntityManager em = emf.createEntityManager();

        try {
            List<Productos> listProd = em.createNamedQuery("Productos.findAll", Productos.class).getResultList();

            for (int i = 0; i < listProd.size(); i++) {
                fila[0] = listProd.get(i).getIdProducto();
                fila[1] = listProd.get(i).getPrecio();
                fila[2] = listProd.get(i).getStock();
                fila[3] = listProd.get(i).getIva();
                fila[4] = listProd.get(i).getDescripcion();
                fila[5] = listProd.get(i).getIdTipoProducto().getNomCategoria();
                // Agregamos esta fila a nuestro modelo
                modelo.addRow(fila);
            } // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        } catch (Exception e) {
        }
        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        // Decimos al JTable el modelo a usar
        jTableProductos.setModel(modelo);
    }

    public void cargarDatosJTableComida() {
        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();

        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        EntityManager em = emf.createEntityManager();

        try {
            List<Productos> listProd = em.createNamedQuery("Productos.findAll", Productos.class).getResultList();
            List<Productos> listComida = new ArrayList<>();

            for (Productos prod : listProd) {
                if (prod.getIdTipoProducto().getIdTipoProducto() == 1) {
                    listComida.add(prod);
                }
            }

            for (int i = 0; i < listComida.size(); i++) {
                fila[0] = listComida.get(i).getIdProducto();
                fila[1] = listComida.get(i).getPrecio();
                fila[2] = listComida.get(i).getStock();
                fila[3] = listComida.get(i).getIva();
                fila[4] = listComida.get(i).getDescripcion();
                fila[5] = listComida.get(i).getIdTipoProducto().getNomCategoria();
                // Agregamos esta fila a nuestro modelo
                modelo.addRow(fila);
            } // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        } catch (Exception e) {
        }
        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        // Decimos al JTable el modelo a usar
        jTableProductos.setModel(modelo);
    }

    public void cargarDatosJTableBebida() {
        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();

        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        EntityManager em = emf.createEntityManager();

        try {
            List<Productos> listProd = em.createNamedQuery("Productos.findAll", Productos.class).getResultList();
            List<Productos> listBebida = new ArrayList<>();

            for (Productos prod : listProd) {
                if (prod.getIdTipoProducto().getIdTipoProducto() == 2) {
                    listBebida.add(prod);
                }
            }

            for (int i = 0; i < listBebida.size(); i++) {
                fila[0] = listBebida.get(i).getIdProducto();
                fila[1] = listBebida.get(i).getPrecio();
                fila[2] = listBebida.get(i).getStock();
                fila[3] = listBebida.get(i).getIva();
                fila[4] = listBebida.get(i).getDescripcion();
                fila[5] = listBebida.get(i).getIdTipoProducto().getNomCategoria();
                // Agregamos esta fila a nuestro modelo
                modelo.addRow(fila);
            } // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        } catch (Exception e) {
        }
        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        // Decimos al JTable el modelo a usar
        jTableProductos.setModel(modelo);
    }

    public void cargarDatosJTablePostre() {
        // Se crea el modelo de datos que contendrá el JTable
        // Este modelo se rellena de datos y luego se asocia al JTable
        ModeloTablaProducto modelo = new ModeloTablaProducto();

        // Array de object con el número de columnas del jtable
        // Para guardar cada campo de cada Persona de la lista
        Object[] fila = new Object[modelo.getColumnCount()];

        EntityManager em = emf.createEntityManager();

        try {
            List<Productos> listProd = em.createNamedQuery("Productos.findAll", Productos.class).getResultList();
            List<Productos> listPostre = new ArrayList<>();

            for (Productos prod : listProd) {
                if (prod.getIdTipoProducto().getIdTipoProducto() == 3) {
                    listPostre.add(prod);
                }
            }

            for (int i = 0; i < listPostre.size(); i++) {
                fila[0] = listPostre.get(i).getIdProducto();
                fila[1] = listPostre.get(i).getPrecio();
                fila[2] = listPostre.get(i).getStock();
                fila[3] = listPostre.get(i).getIva();
                fila[4] = listPostre.get(i).getDescripcion();
                fila[5] = listPostre.get(i).getIdTipoProducto().getNomCategoria();
                // Agregamos esta fila a nuestro modelo
                modelo.addRow(fila);
            } // Al finalizar el bucle el modelo tendrá tantas filas como nuestra lista
        } catch (Exception e) {
        }
        // Iteramos por la lista y asignamos a
        // cada celda del array el valor del atributo de esa persona
        // Decimos al JTable el modelo a usar
        jTableProductos.setModel(modelo);
    }

    public void aniadirCarrito() {
        try {
            int row = jTableProductos.getSelectedRow();
            
            int id = (int) jTableProductos.getValueAt(row, 0);
            
            Productos p1 = pjc.findProductos(id);
            
            System.out.println(p1);
            
            if (carritoMap.containsKey(p1)) {
                int count = carritoMap.get(p1);
                carritoMap.put(p1, count + Integer.parseInt(jSpinnerCantProd.getValue().toString().trim()));
            } else {
                carritoMap.put(p1, Integer.parseInt(jSpinnerCantProd.getValue().toString().trim()));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Producto no seleccionado o cantidad erronea.");
        }
    }
}
