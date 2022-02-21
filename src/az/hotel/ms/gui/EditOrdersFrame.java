/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.gui;

import az.hotel.ms.dao.ClientDao;
import az.hotel.ms.dao.MenuDao;
import az.hotel.ms.dao.OrdersDao;
import az.hotel.ms.dao.RoomDao;
import az.hotel.ms.model.Client;
import az.hotel.ms.model.Menu;
import az.hotel.ms.model.Orders;
import az.hotel.ms.model.Room;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class EditOrdersFrame extends javax.swing.JFrame {

  private OrdersDao ordersDao;
  private MenuDao menuDao;
  private ClientDao clientDao;
  private RoomDao roomDao;
  private Long selectedId;
  private MainFrame aThis;
    public EditOrdersFrame() {
        initComponents();
    }

    
    EditOrdersFrame(OrdersDao ordersDao, MenuDao menuDao, ClientDao clientDao, RoomDao roomDao, Long selectedId, MainFrame aThis) {
       try {
           initComponents();
           this.ordersDao = ordersDao;
           this.menuDao = menuDao;
           this.clientDao = clientDao;
           this.roomDao = roomDao;
           this.selectedId = selectedId;
           this.aThis = aThis;
           Orders orders = ordersDao.getOrdersById(selectedId);
           editMenuCombo(orders.getMenu());
           editRoomCombo(orders.getRoom());
           orderCostTxt.setText(String.valueOf(orders.getCost()));
       } catch (Exception ex) {
           ex.printStackTrace();
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

        clearBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menuCmb = new javax.swing.JComboBox<>();
        roomCmb = new javax.swing.JComboBox<>();
        orderCostTxt = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jButton1.setText("New Order");

        jLabel1.setText("Menu Name and Description");

        jLabel4.setText("Room Number");

        menuCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Menu" }));

        roomCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Room" }));

        orderCostTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                orderCostTxtActionPerformed(evt);
            }
        });

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Cost");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260)
                        .addComponent(orderCostTxt))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(254, 254, 254)
                        .addComponent(roomCmb, 0, 448, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(220, 220, 220))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(134, 134, 134)
                            .addComponent(menuCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(210, 210, 210)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(roomCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(orderCostTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(103, 103, 103)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(menuCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(474, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        menuCmb.setSelectedIndex(0);
        //menuDescCmb.setSelectedIndex(0);
        //clientCmb.setSelectedIndex(0);
        roomCmb.setSelectedIndex(0);
        orderCostTxt.setText("");
    }//GEN-LAST:event_clearBtnActionPerformed

    private void orderCostTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_orderCostTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_orderCostTxtActionPerformed
    private void editMenuCombo(Menu m) {
       
         try {
            DefaultComboBoxModel menuCombo = (DefaultComboBoxModel) menuCmb.getModel();
            
            //DefaultComboBoxModel menuuCombo = (DefaultComboBoxModel) menuDescCmb.getModel();
            List<Menu> menuList = menuDao.getMenuList();
            for(Menu menu: menuList){
                
                menuCombo.addElement(menu);
               // menuuCombo.addElement();
            }
            menuCombo.setSelectedItem(m);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void editRoomCombo(Room r) {
         try {
            DefaultComboBoxModel roomCombo = (DefaultComboBoxModel) roomCmb.getModel();
            
            List<Room> roomList = roomDao.getRoomList();
            for(Room room: roomList){
                
                roomCombo.addElement(room);
            }
            roomCombo.setSelectedItem(r);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        try{
            Menu menu = (Menu) menuCmb.getSelectedItem();
            //Client client = (Client) clientCmb.getSelectedItem();
            Room room = (Room) roomCmb.getSelectedItem();
            double ordersCost = Double.parseDouble(orderCostTxt.getText());
            Orders orders = ordersDao.getOrdersById(selectedId);
            orders.setMenu(menu);
            //orders.setClient(client);
            orders.setRoom(room);
            orders.setCost(ordersCost);
            ordersDao.updateOrders(orders);
            JOptionPane.showMessageDialog(null,"Orders has been successfully created!");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Problem! Orders has not been successfully created!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
      try {
          aThis.showOrdersData(ordersDao.getOrdersList());
      } catch (Exception ex) {
          ex.printStackTrace();
      }
    }//GEN-LAST:event_updateBtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> menuCmb;
    private javax.swing.JTextField orderCostTxt;
    private javax.swing.JComboBox<String> roomCmb;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}