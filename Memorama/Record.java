package Memorama;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Record extends JFrame implements ActionListener{
    
    JLabel lblnombrejuga, lbltiempoju, lblhorainicio, lblfechaju,etiqueta;
    JButton regresar;

    public Record(){
        this.setTitle("Record");
        this.setSize(1010, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        
        //Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        etiqueta = new JLabel("Record personal");
        etiqueta.setBounds(390,60,320,40);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 35));
        panel.add(etiqueta);
        
        etiqueta = new JLabel("Nombre del Jugador: ");
        etiqueta.setBounds(320,180,200,40);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(etiqueta);
        
        //Nombre del jugador colocado anteriormente
        lblnombrejuga = new JLabel("nombre");
        lblnombrejuga.setBounds(515,180,150,40);
        lblnombrejuga.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(lblnombrejuga);
        
        etiqueta = new JLabel("Tiempo Final: ");
        etiqueta.setBounds(320,240,150,40);
        etiqueta.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(etiqueta);
        
        //Cuanto tiempo se tardo en completar el juego
        lbltiempoju = new JLabel("Tiempo");
        lbltiempoju.setBounds(515,240,150,40);
        lbltiempoju.setFont(new Font("Arial", Font.PLAIN, 20));
        panel.add(lbltiempoju);
        
        etiqueta = new JLabel("Hora de inicio: ");
        etiqueta.setBounds(320,300,150,40);
        etiqueta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(etiqueta);
        
        //Mostrar la hora de inicio
        lblhorainicio = new JLabel("Hora");
        lblhorainicio.setBounds(515,300,150,40);
        lblhorainicio.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lblhorainicio);

        etiqueta = new JLabel("Fecha: ");
        etiqueta.setBounds(320,360,150,40);
        etiqueta.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(etiqueta);

        //Mostrar la fecha
        lblfechaju = new JLabel("fecha");
        lblfechaju.setBounds(515,360,150,40);
        lblfechaju.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lblfechaju);

        regresar = new JButton("Regresar al Menu");
        regresar.setBounds(370,560,250,40);
        regresar.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 16));
        regresar.addActionListener(this);
        panel.add(regresar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Regresar
        if(e.getSource() == regresar){
            Inicio ventana = new Inicio();
            JuegoMemoria ventana2 = new JuegoMemoria();
            ventana2.setVisible(false);
            ventana.setVisible(true);
            this.setVisible(false);
        
        }
    }
    
}
