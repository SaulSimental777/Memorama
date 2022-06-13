package Memorama;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.*;


public class JuegoMemoria extends JFrame implements ActionListener{
    JPanel panel;
    JLabel[][] matriz;
    JLabel etiqueta;
    JLabel nombreju;
    JLabel cronometro;
    JLabel lblfecha;
    JLabel lblhora;
    int[][] mat = new int[4][5];
    int[][] mat2 = new int[4][5];
    Random ran;
    int contador,ban,ban1,annum,anposx,anposy,acnum,acposx,acposy;
    Timer espera, espera2,tiempo;
    int consegund,seg,min;
    int hora,minutos,segundos;
    JButton reiniciar;


    
    public JuegoMemoria(){

        this.setTitle("Juego de Memoria");
        this.setSize(1000, 720);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);




        
        //se coloca un panel a la ventana
        panel = new JPanel();
        this.getContentPane().add(panel);
        panel.setLayout(null);
        
        //Cartas volteadas
        ran = new Random();
        this.numaleatorios();


        //Matriz de cuatro filas y cinco columnas
        matriz = new JLabel[4][5];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = new JLabel();
                matriz[i][j].setSize(matriz[i][j].getWidth(), matriz[i][j].getHeight());
                //Espacios entre las imagenes
                matriz[i][j].setBounds(350+(j*125),30+(i*156), 125, 156);
                //Num imagenes
                matriz[i][j].setIcon(new ImageIcon("src/imagenes/"+mat2[i][j]+".jpg"));

                matriz[i][j].setVisible(true);
                panel.add(matriz[i][j],0);
            }
        }

        
        seg = 0;
        min = 0;
        //Tiempo en el juego
        tiempo = new Timer (1000, e -> {
            seg++;
            if(seg == 60){
                min++;
                seg=0;
            }
        cronometro.setText(min+":"+seg);
        });

        //Otra variable para el tiempo
        consegund = 0;
        espera = new Timer (1000, e -> consegund++);
        espera.start();
        espera.stop();
        consegund = 0;
        ban=0;
        ban1=0;
        
        //Clic en las cartas
        contador = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j].addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e){
                        for (int k = 0; k < 4; k++) {
                            for (int l = 0; l < 5; l++) {
                                if(e.getSource() == matriz[k][l]){
                                   
                                   //Voltear las cartas al dar clic
                                   if(mat2[k][l] == 0 && contador !=2){
                                       mat2[k][l] = mat[k][l];
                                       matriz[k][l].setIcon(new ImageIcon("src/imagenes/"+mat2[k][l]+".jpg"));
                                       contador++;
                                       acnum = mat[k][l];
                                       acposx = k;
                                       acposy = l;
                                       if(contador == 1){
                                            annum = mat[k][l];
                                            anposx = k;
                                            anposy = l;
                                       }
                                       
                                       //Tiempo en voltear
                                       espera2 = new Timer (500, e1 -> {
                                          if(contador == 2 && ban1 == 0){
                                              espera.restart();
                                              ban1=1;
                                           }
                                           if(contador == 2 && consegund == 2){
                                               espera.stop();
                                               consegund = 0;

                                               //Desaparecer cartas encontradas
                                               if(mat2[acposx][acposy]==mat2[anposx][anposy]){

                                                   mat2[acposx][acposy] = -1;
                                                   mat2[anposx][anposy] = -1;
                                                   matriz[acposx][acposy].setIcon(new ImageIcon("src/imagenes/"+mat2[acposx][acposy]+".jpg"));
                                                   matriz[anposx][anposy].setIcon(new ImageIcon("src/imagenes/"+mat2[anposx][anposy]+".jpg"));
                                                   contador=0;

                                                   //Ganar si mat2 es -1
                                                   int acum = 0;
                                                   for (int m = 0; m < 4; m++) {
                                                      for (int n = 0; n < 5; n++) {
                                                         if (mat2[m][n] == -1)
                                                             acum++;
                                                         }
                                                   }


                                                   //Cuando no se encuentre ninguna carta entonces aparece mensaje de ganador
                                                   //Ejecuta Recordjugador
                                                      if(acum == 20){
                                                          JOptionPane.showMessageDialog(panel, "Felicidades, ganaste!");

                                                           Record ventana = new Record();
                                                           ventana.setVisible(true);
                                                           tiempo.stop();
                                                           ventana.lbltiempoju.setText(min+":"+seg);
                                                           ventana.lblnombrejuga.setText(nombreju.getText());
                                                           ventana.lblhorainicio.setText(lblhora.getText());
                                                           ventana.lblfechaju.setText(lblfecha.getText());
                                                      }
                                               }
                                               for (int m = 0; m < 4; m++) {
                                                   for (int n = 0; n < 5; n++) {
                                                       //Se coloca el valor -1 a las cartas pares
                                                       if(mat2[m][n]!=0 && mat2[m][n]!=-1){
                                                           mat2[m][n] = 0;
                                                           matriz[m][n].setIcon(new ImageIcon("src/imagenes/"+mat2[m][n]+".jpg"));
                                                           contador=0;

                                                       }
                                                       System.out.println("Correcto");

                                                   }

                                               }
                                               espera2.stop();
                                               ban1=0;
                                           }
                                       });
                                       if(ban == 0)
                                           espera2.start();
                                           ban = 1;
                                       if(contador == 2)
                                               espera2.restart();
                                   }
                                }
                            }
                        }
                    }
                });
            }
        }
        
        
        componentes();
        lblfecha.setText(fecha());
        hora();

    }

    //Obtener hora
    private void hora(){ 
      Calendar calendario = new GregorianCalendar();
      hora= calendario.get(Calendar.HOUR_OF_DAY);
      minutos = calendario.get(Calendar.MINUTE);
      segundos = calendario.get(Calendar.SECOND);
      lblhora.setText(hora + ":" + minutos + ":" + segundos);   
    } 
    
    //Obtener fecha
    private String fecha(){
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatofecha.format(fecha);
        
    }
    

    //Ordenar aleatoriamente las cartas para que cada vez sean en diferentes lugares

    private void numaleatorios(){
        int acumulador;
         for (int i = 0; i < 4; i++) 
            for (int j = 0; j < 5; j++){
                mat[i][j] = 0;

                
            }    
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = ran.nextInt(10)+1;
                
                do{
                    acumulador = 0;
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 5; l++) {
                             if(mat[i][j]== mat[k][l]){
                                acumulador +=1;
                            }
                        }
                    }
                    //Obtener dos veces la misma carta
                if(acumulador == 3){
                    mat[i][j] = ran.nextInt(10)+1;
                }
                }while(acumulador == 3); 
            }   
        }
    }
    
    
   
   //Componentes
    private void componentes(){
        etiqueta = new JLabel("Jugador: ");
        etiqueta.setBounds(40,40,150,40);
        etiqueta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(etiqueta);
        
        nombreju = new JLabel();
        nombreju.setBounds(135,40,150,40);
        nombreju.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(nombreju);
        
        etiqueta = new JLabel("Tiempo: ");
        etiqueta.setBounds(40,80,150,40);
        etiqueta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(etiqueta);
        
        //Mostrar tiempo en el juego
        cronometro = new JLabel();
        cronometro.setBounds(135,80,150,40);
        cronometro.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(cronometro);
        
        etiqueta = new JLabel("Hora de inicio: ");
        etiqueta.setBounds(40,120,150,40);
        etiqueta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(etiqueta);
        
        //Mostrar fecha
        lblfecha = new JLabel();
        lblfecha.setBounds(135,160,150,40);
        lblfecha.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lblfecha);
        
        etiqueta = new JLabel("Fecha: ");
        etiqueta.setBounds(40,160,150,40);
        etiqueta.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        panel.add(etiqueta);
        
        //Mostrar hora
        lblhora = new JLabel();
        lblhora.setBounds(150,120,150,40);
        lblhora.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        panel.add(lblhora);
        
        //Reinicio del juego
        reiniciar = new JButton("Reiniciar");
        reiniciar.setBounds(115,560,120,40);
        reiniciar.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
        reiniciar.addActionListener(this);
        panel.add(reiniciar);
        
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        //Preguntar si quiere reiniciar
        //si es si,  se reinicia
        // si es no seguira en la partida
         if(e.getSource() == reiniciar){
              if (JOptionPane.showConfirmDialog(rootPane, "¿Está seguro de querer reiniciar el juego?\n Al hacer esto regresará a menu de inicio y su progreso se borrará.",
                      "Reinicio de Juego", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE) == JOptionPane.YES_OPTION){
                  Inicio ventana = new Inicio();
                  ventana.setVisible(true);
                  this.setVisible(false);
              }
              else{
                setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
              }
         }
    }

}



