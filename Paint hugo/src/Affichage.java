import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;




public class Affichage extends JFrame implements ActionListener{


    public Affichage(String Title,int x,int y){
        
       
        JFrame f = new JFrame("Paint"); 
        Container contentPanel = this.getContentPane() ;
        

        JMenuBar panelMenu = new JMenuBar();
        Dessin panelDessin = new Dessin();
        JPanel panelBoutons = new JPanel();
        setDefaultLookAndFeelDecorated(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelDessin.setBackground(Color.WHITE);
      
        
        
        JMenu fichierMenu = new JMenu("Fichier");
        JMenu aProposMenu = new JMenu("À Propos");
        
        

 
// bouton nouveau        



        
        JMenuItem itemNouveau = new JMenuItem("Nouveau") ; 
        itemNouveau.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            String nomFichier = JOptionPane.showInputDialog(getParent(),"Donne un nom à ton oeuvre", null);
            f.setTitle("Paint - " + nomFichier);
            panelDessin.reinitialisationDuDessin();
        }
    });
        fichierMenu.add(itemNouveau); 
        fichierMenu.addSeparator();
        
        
        
        
        
        
// bouton ouvrir        
        
        
        
        
        
        JMenuItem itemOuvrir = new JMenuItem("Ouvrir") ; 
        itemOuvrir.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            JFileChooser openFile = new JFileChooser("/Users/hugolamy/Dekstop");
            openFile.showOpenDialog(itemOuvrir);
            try{
                ArrayList<Figure> liste = new ArrayList<Figure>();
                String nameFile = openFile.getSelectedFile().getAbsolutePath();
                FileInputStream fis = new FileInputStream(nameFile);
                ObjectInputStream ois = new ObjectInputStream(fis);

                int nombreDeFigures = ois.readInt();
                for (int i = 0; i < nombreDeFigures; i++) {
                    Figure figure = (Figure) ois.readObject();
                    liste.add(figure);
                }
                panelDessin.ouvreFichier(liste);
                ois.close();
                }
            catch (Exception e2){
                e2.printStackTrace();
            }

        }
        });
        fichierMenu.add(itemOuvrir); 
        fichierMenu.addSeparator(); 
        
        
        
        
        
        
// bouton sauvegarder       
        
        
        
        
        
        
        JMenuItem itemSauver = new JMenuItem("Sauvegarder") ; 
        itemSauver.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            JFileChooser saveFile = new JFileChooser("/Users/hugolamy/Desktop");
                        saveFile.showSaveDialog(itemSauver);
                        try{
                            ArrayList<Figure> liste = new ArrayList<Figure>();
                            liste = panelDessin.getlisteDeFigure();
                            String nameFile = saveFile.getSelectedFile().getAbsolutePath();
                            FileOutputStream fos = new FileOutputStream(nameFile);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);

                            oos.writeInt(liste.size());
                            for (int i = 0; i < liste.size(); i++) {
                                oos.writeObject(liste.get(i));
                            }
                            oos.close();
                        }
                        catch (Exception e2){
                           e2.printStackTrace();
                        
        }
    }
    });
        fichierMenu.add(itemSauver); 
        fichierMenu.addSeparator(); 

        





// bouton quitter



        
        JMenuItem itemQuitter = new JMenuItem("Quitter") ;
        itemQuitter.addActionListener(new ActionListener(){ 
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    });
        fichierMenu.add(itemQuitter); 
        panelMenu.add(fichierMenu);

        

        


// bouton à propos




        JMenuItem aProposCreateur = new JMenu("Créateur"); 
        JButton boutonCreateur = new JButton("Entrer le nom du créateur"); 
            boutonCreateur.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        String nomCreateur = JOptionPane.showInputDialog(getParent(),"Your name bitch ?");
                        f.setTitle("Paint made by " + nomCreateur);
                        boutonCreateur.setText(nomCreateur);
                    }
                });
        aProposCreateur.add(boutonCreateur); 
        aProposMenu.add(aProposCreateur); 
        panelMenu.add(aProposMenu); 


// bouton retour




JMenuItem retour = new JMenu("Retour"); 
    retour.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.annuleLaDerniereFigure();
                
            }
        });



// bouton restaure


JMenuItem restaure = new JMenu("Restaure"); 
    restaure.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.reafficheLaDerniereFigure();
                
            }
        });


// bouton gomme

JButton btnGomme = new JButton("Gomme");
            btnGomme.setBackground(Color.WHITE);
            btnGomme.setOpaque(true);
            btnGomme.setBorderPainted(false);
            btnGomme.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.WHITE);
                }
            });


aProposCreateur.add(boutonCreateur); 
aProposMenu.add(aProposCreateur); 
panelMenu.add(aProposMenu); 
panelMenu.add(retour);
panelMenu.add(restaure);
panelMenu.add(btnGomme);
        




// boutons de couleur




        

        JButton btnNoir = new JButton("Noir");
            btnNoir.setBackground(Color.black);
            btnNoir.setForeground(Color.white);
            btnNoir.setOpaque(true);
            btnNoir.setBorderPainted(false);
            btnNoir.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        panelDessin.setCouleur(Color.black);
                    }
                });
        JButton btnRouge = new JButton("Rouge");
            btnRouge.setBackground(Color.RED);
            btnRouge.setOpaque(true);
            btnRouge.setBorderPainted(false);
            btnRouge.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.RED);
                }
            });
        JButton btnVert = new JButton("Vert");
            btnVert.setBackground(Color.GREEN);
            btnVert.setOpaque(true);
            btnVert.setBorderPainted(false);
            btnVert.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.GREEN);
                }
            });
        JButton btnBleu = new JButton("Bleu");
            btnBleu.setBackground(Color.BLUE);
            btnBleu.setForeground(Color.white);
            btnBleu.setOpaque(true);
            btnBleu.setBorderPainted(false);
            btnBleu.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.BLUE);
                }
            });
        JButton btnJaune = new JButton("Jaune");
            btnJaune.setBackground(Color.YELLOW);
            btnJaune.setOpaque(true);
            btnJaune.setBorderPainted(false);
            btnJaune.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.YELLOW);
                }
            });
        JButton btnRose = new JButton("Rose");
            btnRose.setBackground(Color.PINK);
            btnRose.setOpaque(true);
            btnRose.setBorderPainted(false);
            btnRose.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.PINK);
                }
            });
        JButton btnMagenta = new JButton("Magenta");
            btnMagenta.setBackground(Color.MAGENTA);
            btnMagenta.setOpaque(true);
            btnMagenta.setBorderPainted(false);
            btnMagenta.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.MAGENTA);
                }
            });
        JButton btnOrange = new JButton("Orange");
            btnOrange.setBackground(Color.ORANGE);
            btnOrange.setOpaque(true);
            btnOrange.setBorderPainted(false);
            btnOrange.addActionListener(new ActionListener(){ 
                public void actionPerformed(ActionEvent e){
                    panelDessin.setCouleur(Color.ORANGE);
                }
            });
        

            


// bouton de forme




        
        JButton btnRectangle = new JButton("Rectangle");
        btnRectangle.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.setfigureSuivante("Rectangle");
                        }
                    });
        JButton btnCarre = new JButton("Carré");
        btnCarre.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.setfigureSuivante("Carré");
            }
        });
        JButton btnEllipse = new JButton("Ellipse");
        btnEllipse.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.setfigureSuivante("Ellipse");
            }
        });
        JButton btnCercle = new JButton("Cercle");
        btnCercle.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.setfigureSuivante("Cercle");
            }
        });


// bouton de dessin à la main

        JButton btnALaMain = new JButton("À la main");
        btnALaMain.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){
                panelDessin.setfigureSuivante("A la main");
            }
        });
        
        JButton btnTaille = new JButton("Taille curseur"); 
            btnTaille.addActionListener(new ActionListener(){ 
                    public void actionPerformed(ActionEvent e){
                        String demandeDeTaille = JOptionPane.showInputDialog(getParent(),"Donner la taille du curseur en pixels");
                        int tailleCurseur = Integer.parseInt(demandeDeTaille);
                        panelDessin.setTaille(tailleCurseur);
                    }
                });
        
// remplissage des panels


        panelBoutons.setLayout(new GridLayout(2,7));
        panelBoutons.add(btnNoir);
        panelBoutons.add(btnRouge);
        panelBoutons.add(btnVert);
        panelBoutons.add(btnBleu);
        panelBoutons.add(btnRectangle);
        panelBoutons.add(btnCarre);
        panelBoutons.add(btnALaMain);
        panelBoutons.add(btnJaune);
        panelBoutons.add(btnOrange);
        panelBoutons.add(btnMagenta);
        panelBoutons.add(btnRose);
        panelBoutons.add(btnEllipse);
        panelBoutons.add(btnCercle);
        panelBoutons.add(btnTaille);


        
        contentPanel.add(panelMenu, BorderLayout.NORTH);
        contentPanel.add(panelDessin);
        contentPanel.add(panelBoutons, BorderLayout.SOUTH);

        f.add(contentPanel);
        f.pack();
        f.setSize(x,y); // Taille de la fenêtre créée
        f.setVisible(true);
    
    }

    
    
    
    
 // override pour rendre heureux l'Ide   
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
    }
}


    