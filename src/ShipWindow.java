/*
 * ShipWindow class
 * Authors: Ethan Campbell and Justin Delgado
 * Modified on 9-26-2022
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class ShipWindow extends JFrame{
    
    private int win_wid = 650;
    private int win_hei = 240;
    private int grid_rows = 3;
    private int grid_col = 1;
    private int mediumFontSize = 15;
    
    JComboBox selectShipBox;
    ArrayList<Ship> flotilla = new ArrayList();
    
    JTextField nameText, nationText, yearText, lenText, draftText, beamText;
    
    public ShipWindow(){
    
        this.setTitle("            Shipping News                      Ethan Campbell and Justin Delgado");
        this.setSize(win_wid, win_hei);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
        initializeShips();
        initInfoPanel();
        initSelectPanel();
        initButtonPanel();
        
        this.setVisible(true);
    }
    
    public void initializeShips(){
    
        File inFile = new File ("csv/Tia_RosasFleet.csv");
        
        try{
        
            Scanner inScan = new Scanner(inFile).useDelimiter("[,\n]");
            while(inScan.hasNext()){
                
                String name = inScan.next();
                String nation = inScan.next();
                int yearBuilt = inScan.nextInt();
                int length = inScan.nextInt();
                int draft = inScan.nextInt();
                int beam = inScan.nextInt();
                
                flotilla.add(new Ship(name, nation, yearBuilt, length, draft, beam));
            }
        }
        
        catch(IOException ioe){
        
            JOptionPane.showMessageDialog(null, "Cannot see file!", "Warning!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void initSelectPanel(){
    
        int font_size = 26;
        Font bigFont = new Font("Arial", 1, font_size);
        JLabel lab = new JLabel("Select a Ship");
        lab.setFont(bigFont);
        
        selectShipBox = new JComboBox();
        selectShipBox.addItemListener(new ItemListener(){
    
            public void itemStateChanged(ItemEvent ie){
            
                String shipName = (String) ie.getItem();
                for(int getShip = 0; getShip < flotilla.size(); getShip++){
                
                    if(shipName == flotilla.get(getShip).getName()){
                    
                        String info = " ";
                        nameText.setText(info + flotilla.get(getShip).getName() + info);
                        nationText.setText(info + flotilla.get(getShip).getNation() + info);
                        yearText.setText(info + flotilla.get(getShip).getYearBuilt() + info);
                        lenText.setText(info + flotilla.get(getShip).getLength() + info);
                        draftText.setText(info + flotilla.get(getShip).getDraft() + info);
                        beamText.setText(info + flotilla.get(getShip).getBeam() + info);
                    }
                }
            }
    });
        
        String[] numShips= new String [flotilla.size()];
        
        for(int currentship = 0; currentship < numShips.length; currentship++){
    
            numShips[currentship] = flotilla.get(currentship).getName();
        }
        
        selectShipBox.setModel(new javax.swing.DefaultComboBoxModel<>(numShips));
        
        ImageIcon icon = new ImageIcon("icon/yacht2.png");
        JLabel iconLab = new JLabel();
        iconLab.setIcon(icon);
                
        JPanel northPan = new JPanel();
        
        northPan.add(iconLab);
        northPan.add(lab);
        northPan.add(selectShipBox);
        this.add(northPan, BorderLayout.NORTH);
    }
    
    public void initInfoPanel(){
    
        JPanel west_pan = new JPanel(new GridLayout(grid_rows, grid_col));
        
        JLabel name_lab = new JLabel("Ship Name: ");
        JLabel country_lab = new JLabel("Country of Registration: ");
        JLabel year_lab = new JLabel("Year of Construction: ");
        JLabel length_lab = new JLabel("Length at waterline: ");
        JLabel draft_lab = new JLabel("Draft at waterline: ");
        JLabel beam_lab = new JLabel("Beam at waterline: ");
        
        nameText = new JTextField();
        nationText = new JTextField();
        yearText = new JTextField();
        lenText = new JTextField();
        draftText = new JTextField();
        beamText = new JTextField();
        
        west_pan.add(name_lab);
        west_pan.add(nameText);
        west_pan.add(country_lab);
        west_pan.add(nationText);
        west_pan.add(year_lab);
        west_pan.add(yearText);
        
        Font med_font = new Font("Arial", 0, mediumFontSize);
        
        name_lab.setFont(med_font);
        country_lab.setFont(med_font);
        year_lab.setFont(med_font);
        
        name_lab.setHorizontalAlignment(JLabel.RIGHT);
        country_lab.setHorizontalAlignment(JLabel.RIGHT);
        year_lab.setHorizontalAlignment(JLabel.RIGHT);
        
        this.add(west_pan, BorderLayout.WEST);
        
        JPanel east_pan = new JPanel(new GridLayout(grid_rows, grid_col));
        
        east_pan.add(length_lab);
        east_pan.add(lenText);
        east_pan.add(draft_lab);
        east_pan.add(draftText);
        east_pan.add(beam_lab);
        east_pan.add(beamText);
        
        length_lab.setFont(med_font);
        draft_lab.setFont(med_font);
        beam_lab.setFont(med_font);
        
        length_lab.setHorizontalAlignment(JLabel.RIGHT);
        draft_lab.setHorizontalAlignment(JLabel.RIGHT);
        beam_lab.setHorizontalAlignment(JLabel.RIGHT);
        
        this.add(east_pan, BorderLayout.EAST);
    }
    
    public void initButtonPanel(){
    
        JPanel south_pan = new JPanel();
        JButton clear_but = new JButton("Clear");
        clear_but.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent ae){
            
                nameText.setText(null);
                nationText.setText(null);
                yearText.setText(null);
                lenText.setText(null);
                draftText.setText(null);
                beamText.setText(null);
            }
        });
        
        JButton quit_but = new JButton("Quit");
        quit_but.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent ae){
            
                System.exit(0);
            }
        });
        
        south_pan.add(clear_but);
        south_pan.add(quit_but);
        
        this.add(south_pan, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
         
        ShipWindow sw = new ShipWindow();
    }
}   