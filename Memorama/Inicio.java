package Memorama;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Inicio extends JFrame implements ActionListener {
    JLabel etiqueta;
    JButton iniciarjuego,salir;
    JTextField nomjugador;
   
    public Inicio(){
        this.setTitle("Menu");
        this.setSize(1010, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        componentes();
    }
    

    public void componentes(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.getContentPane().add(panel);
        
        etiqueta = new JLabel("MEMORAMA");
        etiqueta.setBounds(410,60,250,60);
        etiqueta.setFont(new Font("Berlin Sans FB", Font.PLAIN, 30));
        panel.add(etiqueta);
        
        etiqueta = new JLabel("Nombre del jugador");
        etiqueta.setBounds(400,250,250,40);
        etiqueta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
        panel.add(etiqueta);
        
        //Nombre del Jugador
        nomjugador = new JTextField();
        nomjugador.setBounds(300,300,400,40);
        nomjugador.setHorizontalAlignment(JTextField.CENTER);
        nomjugador.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 20));
        panel.add(nomjugador);
        
        //Boton para iniciar
        iniciarjuego = new JButton("Jugar");
        iniciarjuego.setBounds(370,400,250,40);
        iniciarjuego.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        iniciarjuego.addActionListener(this);
        panel.add(iniciarjuego);
        

        //Boton para salir
        salir = new JButton("Salir");
        salir.setBounds(370,460,250,40);
        salir.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        salir.addActionListener(this);
        panel.add(salir);
        
        
    }


    //Eventos
    @Override
    public void actionPerformed(ActionEvent e) {
        //Iniciar
       if(e.getSource() == iniciarjuego){
             //Si no se coloca nombre
             if(nomjugador.getText().equals("")){
             JOptionPane.showMessageDialog(null, "Coloca el nombre del jugador");
            }else{
                 JuegoMemoria ventana = new JuegoMemoria();
                 ventana.nombreju.setText(nomjugador.getText());
                 ventana.tiempo.start();
                 ventana.setVisible(true);
                 this.setVisible(false);
             }
        }

       //Salir
       if(e.getSource() == salir){
           if (JOptionPane.showConfirmDialog(rootPane, "¿Está seguro de querer cerrar el juego?",
                "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION)
                System.exit(0);
       }
      
    }

}
