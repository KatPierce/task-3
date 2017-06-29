
package MyGame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eugenia
 */
public class Game extends JFrame {
    Matrix matr; // Класс обработки матрицы
    int size_pict = 200; // размеры картинок 200х200
    int size_matr = 4; // матрица 4х4
    private Image two, four, eight, sixteen,
            thirtyT, sixtyF, oneH, twoH, fiveH, oneT, twoT;    
    
    private Icon im_2, im_4, im_8, im_16, im_32, im_64, im_128, im_256,
            im_512, im_1024, im_2048;     
    JTable jTable1;
    public Game(){
        setTitle("Biathlon_2048");
        setBounds(0, 0, size_pict*size_matr+30, size_pict*size_matr+30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        setResizable(false);
        matr = new Matrix();
        
        //Импортируем картинки

         two = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/2.png")); 
         four = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/4.png")); 
        eight = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/8.png")); 
         sixteen = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/16.png")); 
         thirtyT = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/32.png")); 
         sixtyF = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/64.png")); 
         oneH = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/128.png")); 
         twoH = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/256.png")); 
         fiveH = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/512.png")); 
         oneT = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/1024.png")); 
         twoT = 
        Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/2048.png"));
         MediaTracker tr = new MediaTracker(this);
         tr.addImage(two, 1);
         tr.addImage(four, 2);
         tr.addImage(eight, 3);
         tr.addImage(sixteen, 4);
         tr.addImage(thirtyT, 5);
         tr.addImage(sixtyF, 6);
         tr.addImage(oneH, 7);
         tr.addImage(twoH, 8);
         tr.addImage(fiveH, 9);
         tr.addImage(oneT, 10);
         tr.addImage(twoT, 11);
         try{
         tr.waitForAll();
         } catch(InterruptedException e) {}
         
        
        
        
        im_2 = new ImageIcon(two);
        im_4 = new ImageIcon(four);
        im_8 = new ImageIcon(eight);
        im_16 = new ImageIcon(sixteen);
        im_32 = new ImageIcon(thirtyT);
        im_64 = new ImageIcon(sixtyF);
        im_128 = new ImageIcon(oneH);
        im_256 = new ImageIcon(twoH);
        im_512 = new ImageIcon(fiveH);
        im_1024 = new ImageIcon(oneT);
        im_2048 = new ImageIcon(twoT);
        
        
        // таблица          
            
        jTable1 = new JTable();
        jTable1.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},                
            },
            new String [] {
                "null", "null", "null", "null", 
            }
        ) {
            Class[] types = new Class [] {
                Icon.class, Icon.class, Icon.class, Icon.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoscrolls(false);
        jTable1.setRowHeight(200);        
        jTable1.setEnabled(false);              
        jTable1.setBackground(Color.LIGHT_GRAY); 
        for(int i=0;i<2;i++){        //Создаем 2 начальные ячейки
        matr.newStep();
        }
        fill_tab();        
        add(jTable1);  
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);
            }            
        });
        
        setFocusable(true); // для обработки клавиатуры
    }
    
    // Заполнить таблицу картинками в соответствии с матрицей
    void fill_tab()
    {
        for(int i=0; i<4; i++)
        {
            for(int j=0; j<4;j++)
            {
                int a = matr.get(i,j);
                switch( a ) {
                    case 2: jTable1.setValueAt(im_2, i, j); break;
                    case 4: jTable1.setValueAt(im_4, i, j); break;
                    case 8: jTable1.setValueAt(im_8, i, j); break;
                    case 16: jTable1.setValueAt(im_16, i, j); break;
                    case 32: jTable1.setValueAt(im_32, i, j); break;
                    case 64: jTable1.setValueAt(im_64, i, j); break;
                    case 128: jTable1.setValueAt(im_128, i, j); break;
                    case 256: jTable1.setValueAt(im_256, i, j); break;
                    case 512: jTable1.setValueAt(im_512, i, j); break;
                    case 1024: jTable1.setValueAt(im_1024, i, j); break;
                    case 2048: jTable1.setValueAt(im_2048, i, j); break;
                    default: jTable1.setValueAt(null, i, j);     // Свободна
                }

            }
        }
    }

    void formKeyPressed(KeyEvent evt) {
        int key = evt.getKeyCode();
        switch(key)
        {           
            case KeyEvent.VK_UP:  if( matr.m_up() ) {
                                        fill_tab();
                                        break;
                                    }
                                    else { // нет свободных мест
                                        JOptionPane.showMessageDialog(this, "ПОТРАЧЕНО");
                                        this.dispose(); // конец - окно закрывается
                                    }
            case KeyEvent.VK_LEFT:  if( matr.m_left() ) {
                                        fill_tab();
                                        break;
                                    }
                                    else { // нет свободных мест
                                        JOptionPane.showMessageDialog(this, "ПОТРАЧЕНО");
                                        this.dispose(); // конец - окно закрывается
             
                                    }
            case KeyEvent.VK_DOWN:  if( matr.m_down() ) {
                                        fill_tab();
                                        break;
                                    }
                                    else { // нет свободных мест
                                        JOptionPane.showMessageDialog(this, "ПОТРАЧЕНО");
                                        this.dispose(); // конец - окно закрывается
             
                                    }
            case KeyEvent.VK_RIGHT: if( matr.m_right() ) {
                                        fill_tab();
                                        break;
                                    }
                                    else { // нет свободных мест
                                        JOptionPane.showMessageDialog(this, "ПОТРАЧЕНО");
                                        this.dispose(); // конец - окно закрывается
             
                                    }
        }   
        
    }
    
    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            new Game().setVisible(true);
        });
    }
        
 }
    

