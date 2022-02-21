package az.hotel.ms.main;
import java.util.List;
import java.util.Scanner;

import az.hotel.ms.dao.*;
import az.hotel.ms.dao.Impl.BookingDaoImpl;
import az.hotel.ms.dao.Impl.CleaningDaoImpl;
import az.hotel.ms.dao.Impl.ClientDaoImpl;
import az.hotel.ms.dao.Impl.EmployeeDaoImpl;
import az.hotel.ms.dao.Impl.ExpenseDaoImpl;
import az.hotel.ms.dao.Impl.LoginUserDaoImpl;
import az.hotel.ms.dao.Impl.MenuDaoImpl;
import az.hotel.ms.dao.Impl.OrdersDaoImpl;
import az.hotel.ms.dao.Impl.PaymentDaoImpl;
import az.hotel.ms.dao.Impl.PositionsDaoImpl;
import az.hotel.ms.dao.Impl.ReserveDaoImpl;
import az.hotel.ms.dao.Impl.RoomDaoImpl;
import az.hotel.ms.gui.LoginFrame;
import az.hotel.ms.gui.MainFrame;

public class MainClass {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
               
                try{
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                
            }
               LoginFrame loginFrame = new LoginFrame();
               loginFrame.setVisible(true);
               // MainFrame mainFrame = new MainFrame(clientDao, employeeDao,expenseDao,roomDao,bookingDao,reserveDao, ordersDao,paymentDao,positionsDao,
                //menuDao);
                //mainFrame.setVisible(true);
                }catch(Exception ex){
                    
                    ex.printStackTrace();
                }
	

}
}
