/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.hotel.ms.gui;

import az.hotel.ms.dao.CleaningDao;
import az.hotel.ms.dao.RoomDao;
import az.hotel.ms.dao.RoomTypeDao;
import az.hotel.ms.dao.StatusDao;
import az.hotel.ms.model.Cleaning;
import az.hotel.ms.model.Room;
import az.hotel.ms.model.RoomType;
import az.hotel.ms.model.Status;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class NewRoomFrame extends javax.swing.JFrame {

    private RoomDao roomDao;
    private RoomTypeDao roomTypeDao;
    private StatusDao statusDao;
    private CleaningDao cleaningDao;
    private MainFrame aThis;
    public NewRoomFrame() {
        initComponents();
    }

    NewRoomFrame(RoomDao roomDao, RoomTypeDao roomTypeDao, StatusDao statusDao, CleaningDao cleaningDao, MainFrame aThis) {
        initComponents();
       this.roomDao = roomDao;
       this.roomTypeDao = roomTypeDao;
       this.statusDao = statusDao;
       this.cleaningDao = cleaningDao;
       this.aThis = aThis;
       showCleaningCombo();
       showStatusCombo();
       showRoomTypeCombo();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        typeCmb = new javax.swing.JComboBox<>();
        statusCmb = new javax.swing.JComboBox<>();
        costTxt = new javax.swing.JTextField();
        conditionCmb = new javax.swing.JComboBox<>();
        saveBtn = new javax.swing.JButton();
        clearBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        roomTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("New Room");

        typeCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Type" }));

        statusCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Status" }));

        conditionCmb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Condition" }));

        saveBtn.setText("Save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        clearBtn.setText("Clear");
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("Room Number ");

        jLabel2.setText("Room Type and Description");

        jLabel3.setText("Room Status");

        jLabel4.setText("Cost");

        jLabel5.setText("Cleaning Condition");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(172, 172, 172)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(typeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(statusCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(costTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(conditionCmb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(roomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(roomTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(95, 95, 95)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(typeCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(statusCmb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)
                                .addComponent(jLabel4))
                            .addComponent(costTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addComponent(jLabel5))
                    .addComponent(conditionCmb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     private void showCleaningCombo() {
        
        try {
            DefaultComboBoxModel cleaningCombo = (DefaultComboBoxModel) conditionCmb.getModel();
            
            List<Cleaning> cleaningList = cleaningDao.getCleaningList();
            for(Cleaning cleaning: cleaningList){
                
                cleaningCombo.addElement(cleaning);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 }
         private void showStatusCombo() {
        
        try {
            DefaultComboBoxModel statusCombo = (DefaultComboBoxModel) statusCmb.getModel();
            
            List<Status> statusList = statusDao.getStatusList();
            for(Status status: statusList){
                
                statusCombo.addElement(status);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
         }
         private void showRoomTypeCombo() {
        
        try {
            DefaultComboBoxModel roomTypeCombo = (DefaultComboBoxModel) typeCmb.getModel();
            
            List<RoomType> roomTypeList = roomTypeDao.getRoomTypeList();
            for(RoomType roomType: roomTypeList ){
                
                roomTypeCombo.addElement(roomType);
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
         }
    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        try{

            RoomType roomType = (RoomType) typeCmb.getSelectedItem();
            Status status = (Status) statusCmb.getSelectedItem();
            Cleaning cleaning =  (Cleaning) conditionCmb.getSelectedItem();
            double amount = Double.parseDouble(costTxt.getText());
            String roomNumber = roomTxt.getText();
            Room room = new Room();
            room.setStatus(status);
            room.setCleaning(cleaning);
            room.setRoomType(roomType);
            room.setRoomNumber(roomNumber);
            room.setCost(amount);
            System.out.println("3 " + room.getRoomNumber());
            roomDao.addRoom(room);
            JOptionPane.showMessageDialog(null,"Room has been successfully added!");
            
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,"Problem! Room has not been successfully added!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        this.dispose();
        try {
            aThis.showRoomData(roomDao.getRoomList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        roomTxt.setText("");
        typeCmb.setSelectedIndex(0);
        statusCmb.setSelectedIndex(0);
        costTxt.setText("");
        conditionCmb.setSelectedIndex(0);
    }//GEN-LAST:event_clearBtnActionPerformed


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearBtn;
    private javax.swing.JComboBox<String> conditionCmb;
    private javax.swing.JTextField costTxt;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField roomTxt;
    private javax.swing.JButton saveBtn;
    private javax.swing.JComboBox<String> statusCmb;
    private javax.swing.JComboBox<String> typeCmb;
    // End of variables declaration//GEN-END:variables
}
