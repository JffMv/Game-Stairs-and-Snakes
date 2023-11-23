/**
 * 
 */
package presentation;
import javax.swing.*;
import java.util.Random;
import java.awt.geom.Line2D;
import java.awt.Graphics.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import domain.*;
import domain.Box;
/**
 * @author USUARIO
 *
 */
public class PoobStairsGUI extends JFrame{
	
	
	private JPanel boardGeneral, mainPanel, panel1, panel2, panel3, panel4,panelPrimer,
	panelSegundo,header,center,footer;
    private JButton  players, playDice,dice,paintAll ,machine, selectColorPrim_1
    ,selectColorPrim,play,view, 
    back,playPlayers, back1,playPlayer,selectColorSeg;
    
    private CardLayout cardLayout;
    private GridLayout grid;
    private GridBagConstraints auxiliar;
    private BorderLayout border, borderLayaoutPanel3;
    private Color colorSegundo,colorPrimero, colorSegundoBox, colorPrimerBox;
    private Boolean flag, otherPlayer, player, primeraVez, update, 
    archivoAbierto, transformBox, machinePlay,machineActive;
    private boolean positionStart_Player1,positionStart_Player2;
    private String namePlayer1, namePlayer2, colorPlayer1, colorPlayer2;
    private int nStairs, nSnakes, persentageSpecialBox,persentageModifiers, 
    nRows, nColumns, dado,indexAfterPlayer2, indexAfterPlayer1, typeMachine;
    private int[] positionPlayer_1, positionPlayer_2;
    private ArrayList<Line2D> paintedLines = new ArrayList<>();

    
    private PoobStairs poobStairs;
    private JComboBox<Boolean> transform,transform_1;
    private JComboBox<String>selectMachine;
    private JSpinner snakes,stairs,rows,columns,percentModifiers,
    percentBoxesEspecial, percentModifiers_1,
    percentBoxesEspecial_1,snakes_1,stairs_1,
    rows_1,columns_1;
    //private JTextField percentBoxesEspecial;
    private JLabel inicio;
    private JTextField name2, name,name_1;
    private Graphics  player1, player2;
    private Graphics2D g;
    private ImageIcon imgDice, iconoEscaladoDice;
    private Image imagenOriginalDice, imagenEscaladaDice;
    
    private JMenuBar menuBar;
    private JMenu archivoMenu, colorMenu;
    private JMenuItem nuevoItem, abrirItem, importItem, salvarASItem,salirItem, colorBoxesEven, colorBoxesOdd;
    private JFileChooser fileChooser;
	/**
	 * constructor
	 */
	public PoobStairsGUI() {
		super("Stairs && Snakes");
		machineActive = false;
		machinePlay = false;
		nColumns = 1;
		nRows = 1;
		flag = false;
		otherPlayer = false;
		player = true;
		primeraVez = false;
		dado = 1;
		fileChooser = new JFileChooser();
		update = true;
		archivoAbierto = false;
		positionPlayer_1 = new int[2];
		positionPlayer_2 = new int[2];
		positionStart_Player1 = true;
		positionStart_Player2= true;
		prepareElements();
		prepareActions();
				
	}
	
	
	/**
	 * prepara toda la parte gráfica.
	 */
	public void prepareElements() {
		
		
		
		//Se configura la pantalla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Toolkit.getDefaultToolkit().getScreenSize().width /2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        setLocationRelativeTo(null);
        
        
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(77, 168, 96, 66));
        menuBar.setBorderPainted(false);
        setJMenuBar(menuBar);
        
        archivoMenu = new JMenu("File");
        
        archivoMenu.setForeground(new Color(30, 30, 30, 200));
        menuBar.add(archivoMenu);

        nuevoItem = new JMenuItem("New");
        nuevoItem.setBackground(new Color(77, 168, 96, 66));
        //nuevoItem.setBorderPainted(false);
        abrirItem = new JMenuItem("Open");
        abrirItem.setBackground(new Color(77, 168, 96, 66));
        
       
        salvarASItem = new JMenuItem("Save as");
        salvarASItem.setBackground(new Color(77, 168, 96, 66));
        salvarASItem.setEnabled(false);
     
        salirItem = new JMenuItem("Out");
        salirItem.setBackground(new Color(77, 168, 96, 66));
       

        archivoMenu.add(nuevoItem);
        archivoMenu.addSeparator();
        archivoMenu.add(abrirItem);
        archivoMenu.addSeparator();
        
       
        archivoMenu.add(salvarASItem);
        archivoMenu.addSeparator();
        archivoMenu.add(salirItem);
        
        
        colorMenu = new JMenu("Color_Background");
        
        colorMenu.setForeground(new Color(30, 30, 30, 200));
        menuBar.add(colorMenu);
        
        colorBoxesEven = new JMenuItem("Boxes Even");
        colorBoxesEven.setBackground(new Color(77, 168, 96, 66));
        colorBoxesEven.setEnabled(false);
        colorBoxesOdd = new JMenuItem("Boxes Odd");
        colorBoxesOdd.setBackground(new Color(77, 168, 96, 66));
        colorBoxesOdd.setEnabled(false);
        
        colorMenu.add(colorBoxesEven);
        colorMenu.addSeparator();
        colorMenu.add(colorBoxesOdd);
        
        
        
        //mainPanel es un CardLayout es decir: es el que guarda todas las pantallas o JPanels
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        
        
        
        // panel1 es el JPanel HOME
        panel1 = new JPanel();
        panel1.setBackground(Color.pink);
        grid = new GridLayout(10,3);
        border = new BorderLayout();
        panel1.setLayout(border); 
        
        JPanel one = new JPanel();
        one.setBackground(Color.PINK);
        play = new JButton("play");
        JLabel mid = new JLabel();
        view = new JButton("rules");
         
        ImageIcon icon = new ImageIcon("img/snake_inicio.png");
        Image imagenOriginal = icon.getImage();
        Image imagenEscalada = imagenOriginal.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        JLabel label = new JLabel(iconoEscalado);
       
        panel1.add(label,border.CENTER);
        Border borde = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true);
        one.setLayout(grid);
        one.add(play);
        one.add(mid);
        one.add(view);
        one.setPreferredSize(new Dimension(150, 0));
       
        play.setBorderPainted(false);
        view.setBorderPainted(false);
        play.setBackground(Color.GRAY);
        view.setBackground(Color.GRAY);
        
        panel1.add(one,border.WEST);
        panel1.add(new JLabel("\n \n \n \n"), border.NORTH);
        mainPanel.add(panel1, "panel1");
        
       
        
        //panel2 es donde hay dos jugadores y cada uno llena con sus datos
        panel2 = new JPanel();
        panel2.add(new JLabel("Panel 2"));
        panel2.setLayout(new BorderLayout());
        JPanel PanelGeneral = new JPanel(new GridLayout(1,3));
        // Crear un nuevo panel con un GridBagLayout
        JPanel PanelPlayer1 = new JPanel(new GridBagLayout());
        JLabel nombre = new JLabel("Name");
        JLabel colorPrimero = new JLabel("Pick a Color");
        
        name = new JTextField(6);
        name.setText("Player 1");
        selectColorPrim = new JButton();
        selectColorPrim.setBackground(Color.BLACK);
        selectColorPrim.setBorderPainted(false);
       
        PanelPlayer1.setBackground(Color.PINK);
        PanelGeneral.add(PanelPlayer1);
        // Agregar los botones al panel con el GridBagLayout
        GridBagConstraints gbc_players = new GridBagConstraints();
  
        gbc_players.gridx = 1;
        gbc_players.gridy = 0;
        gbc_players.insets = new Insets(10, 10, 10, 10);
        PanelPlayer1.add(name, gbc_players);
        
        gbc_players.gridx = 0;
        PanelPlayer1.add(nombre,gbc_players);
        
        gbc_players.gridx = 1;
        gbc_players.gridy = 1;
        PanelPlayer1.add(selectColorPrim, gbc_players);
        gbc_players.gridx = 0;
        PanelPlayer1.add(colorPrimero,gbc_players);
        
       

        JPanel gameGeneral = new JPanel(new GridBagLayout());
        gameGeneral.setBackground(Color.PINK);
        
        JLabel porcentajeModificadores = new JLabel("Percent of Modifiers");
        JLabel porcentajeCajasEs = new JLabel("Percent of Boxes Specials");
        JLabel numberSnakes = new JLabel("How many snakes?");
        JLabel numberStairs = new JLabel("How many stairs?");
        JLabel numeRows = new JLabel("How mant rows?");
        JLabel numeColumns = new JLabel("How mant columns?");
        JLabel transforms = new JLabel("Do you want transforms?");
        
        SpinnerNumberModel spinnerModel_percentModifiers = new SpinnerNumberModel(50, 0, 100, 1);
        SpinnerNumberModel spinnerModel_percentBoxesEspecial =  new SpinnerNumberModel(50, 0, 100, 1);
         
         
         
         percentModifiers = new JSpinner(spinnerModel_percentModifiers);
         percentBoxesEspecial = new JSpinner(spinnerModel_percentBoxesEspecial);
         snakes = new JSpinner();
         stairs = new JSpinner();
         rows = new JSpinner();
         columns = new JSpinner();
         transform = new JComboBox<Boolean>();
         
         transform.addItem(false);
         transform.addItem(true);
         transform.setSelectedItem(false);
         snakes.setValue(3);
         stairs.setValue(3);
         rows.setValue(5);
         columns.setValue(5);
         
         transform.setPreferredSize(new Dimension(60,30));
         percentModifiers.setPreferredSize(new Dimension(35, 30));
         snakes.setPreferredSize(new Dimension(35, 30));
         stairs.setPreferredSize(new Dimension(35, 30));
         rows.setPreferredSize(new Dimension(35, 30));
         columns.setPreferredSize(new Dimension(35, 30));
         percentBoxesEspecial.setPreferredSize(new Dimension(35, 30));
        
         //percentBoxesEspecial = new JTextField(5);
        GridBagConstraints gbc_general = new GridBagConstraints();
        
        
        gbc_general.insets = new Insets(5, 5, 5, 5);
        gbc_general.gridx = 1;
        gbc_general.gridy = 0;
        gameGeneral.add(percentModifiers, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(porcentajeModificadores, gbc_general);
        gbc_general.gridx = 1;
        gbc_general.gridy = 1;
        gameGeneral.add(percentBoxesEspecial, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(porcentajeCajasEs,gbc_general);
        
        gbc_general.gridx = 1;
        gbc_general.gridy = 2;
        gameGeneral.add(snakes, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(numberSnakes,gbc_general);
        
        gbc_general.gridx = 1;
        gbc_general.gridy = 3;
        gameGeneral.add(stairs, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(numberStairs,gbc_general);
        
        gbc_general.gridx = 1;
        gbc_general.gridy = 4;
        gameGeneral.add(rows, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(numeRows,gbc_general);
        
        gbc_general.gridx = 1;
        gbc_general.gridy = 5;
        gameGeneral.add(columns, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(numeColumns,gbc_general);
        
        
        gbc_general.gridx = 1;
        gbc_general.gridy = 6;
        gameGeneral.add(transform, gbc_general);
        gbc_general.gridx = 0;
        gameGeneral.add(transforms,gbc_general);
        
        PanelGeneral.add(gameGeneral);
        
        
        

        JPanel PanelPlayer2 = new JPanel(new GridBagLayout());
        PanelGeneral.add(PanelPlayer2);
        JLabel nombre2 = new JLabel("Name");
        JLabel colorSegundo = new JLabel("Pick a Color");
        name2 = new JTextField(6);
        name2.setText("Player 2");
        selectColorSeg = new JButton();
        selectColorSeg.setBackground(Color.BLACK);
        selectColorSeg.setBorderPainted(false);
        PanelPlayer2.setBackground(Color.PINK);
        GridBagConstraints gbc_players2 = new GridBagConstraints();
        gbc_players2.gridx = 1;
        gbc_players2.gridy = 0;
        gbc_players2.insets = new Insets(10, 10, 10, 10);
        PanelPlayer2.add(name2, gbc_players2);
        
        gbc_players2.gridx = 0;
        PanelPlayer2.add(nombre2,gbc_players2);
        
        gbc_players2.gridx = 1;
        gbc_players2.gridy = 1;
        PanelPlayer2.add(selectColorSeg, gbc_players2);
        gbc_players2.gridx = 0;
        PanelPlayer2.add(colorSegundo,gbc_players2);

        panel2.add(PanelGeneral, BorderLayout.CENTER);
        back = new JButton("HOME");
        playPlayers = new JButton("PLAY");
        back.setBorderPainted(false);
        playPlayers.setBorderPainted(false);
        back.setBackground(Color.GRAY);
        playPlayers.setBackground(Color.GRAY);
        JPanel menuInferior = new JPanel(new GridLayout(1,3));
        menuInferior.add(back);
        menuInferior.add(new JLabel());
        menuInferior.add(playPlayers);
      
        panel2.add(menuInferior, BorderLayout.SOUTH);
        mainPanel.add(panel2, "panel2");
        
        
        
        
        
        
        
        
        //panel4 guarda la información cuando solo hay un jugador 
        //porque esta jugando contra la maquina
        panel4 = new JPanel();
        panel4.add(new JLabel("Panel 4"));
        panel4.setLayout(new BorderLayout());
        
        
        // Crear un nuevo panel con un GridBagLayout
        JLabel nombre1 = new JLabel("Name");
        JLabel porcentajeModificadores_1 = new JLabel("Percent of Modifiers");
        JLabel porcentajeCajasEs_1 = new JLabel("Percent of Boxes Specials");
        JLabel numberSnakes_1 = new JLabel("How many snakes?");
        JLabel numberStairs_1 = new JLabel("How many stairs?");
        JLabel numeRows_1 = new JLabel("How mant rows?");
        JLabel numeColumns_1 = new JLabel("How mant columns?");
        JLabel selectTransform_1  = new JLabel("Do you want transforms?");     
        JLabel selectMachine_1 = new JLabel("What do you want machine?"); 
        
        JPanel axuDataOnlyGamer= new JPanel();
        axuDataOnlyGamer.setLayout(new GridLayout(1,2));
        panel4.add(axuDataOnlyGamer,BorderLayout.CENTER);
//        SpinnerNumberModel spinnerModel_percentModifiers = new SpinnerNumberModel(50, 0, 100, 1);
//        SpinnerNumberModel spinnerModel_percentBoxesEspecial =  new SpinnerNumberModel(50, 0, 100, 1);
         
        JPanel PanelPlayer_only = new JPanel(new GridBagLayout());

         name_1 = new JTextField(6);
         name_1.setText("Player 1");
         selectMachine = new JComboBox<String>();
         
         selectMachine.addItem("Aprentice");
         selectMachine.addItem("Begginer");
         selectMachine.setSelectedItem("Begginer");
         
         selectColorPrim_1 = new JButton();
         selectColorPrim_1.setBackground(Color.BLACK);
         selectColorPrim_1.setBorderPainted(false);
         PanelPlayer_only.setBackground(Color.PINK);
         
         
         // Agregar los botones al panel con el GridBagLayout
         GridBagConstraints gbc_players1 = new GridBagConstraints();
     
         gbc_players1.gridx = 1;
         gbc_players1.gridy = 0;
         gbc_players1.insets = new Insets(10, 10, 10, 10);
         PanelPlayer_only.add(name_1, gbc_players1);
         
         gbc_players1.gridx = 0;
         PanelPlayer_only.add(nombre1,gbc_players1);
         
         gbc_players1.gridx = 1;
         gbc_players1.gridy = 1;
         PanelPlayer_only.add(selectColorPrim_1, gbc_players1);
         gbc_players1.gridx = 0;
         PanelPlayer_only.add(colorPrimero,gbc_players1);
         
         gbc_players1.gridx = 1;
         gbc_players1.gridy = 2;
         PanelPlayer_only.add(selectMachine, gbc_players1);
         gbc_players1.gridx = 0;
         PanelPlayer_only.add(selectMachine_1,gbc_players1);
         
         axuDataOnlyGamer.add(PanelPlayer_only);
         
         percentModifiers_1 = new JSpinner(spinnerModel_percentModifiers);
         percentBoxesEspecial_1 = new JSpinner(spinnerModel_percentBoxesEspecial);
         snakes_1 = new JSpinner();
         stairs_1 = new JSpinner();
         rows_1 = new JSpinner();
         columns_1 = new JSpinner();
        
         transform_1 = new JComboBox<Boolean>();
         
         transform_1.addItem(false);
         transform_1.addItem(true);
         
         transform_1.setSelectedItem(false);
         snakes_1.setValue(3);
         stairs_1.setValue(3);
         rows_1.setValue(5);
         columns_1.setValue(5);
         
         
         percentModifiers_1.setPreferredSize(new Dimension(35, 30));
         snakes_1.setPreferredSize(new Dimension(35, 30));
         stairs_1.setPreferredSize(new Dimension(35, 30));
         rows_1.setPreferredSize(new Dimension(35, 30));
         columns_1.setPreferredSize(new Dimension(35, 30));
         percentBoxesEspecial_1.setPreferredSize(new Dimension(35, 30));
         
        JPanel PanelPlayer = new JPanel(new GridBagLayout());
        
        PanelPlayer.setBackground(Color.PINK);
        axuDataOnlyGamer.add(PanelPlayer);
        
        
        //name.setSize(70, 20);
        // Agregar los botones al panel con el GridBagLayout
        GridBagConstraints gbc_player = new GridBagConstraints();
       
        gbc_player.insets = new Insets(5, 0, 5, 5);
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 1;
        PanelPlayer.add(percentModifiers_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(porcentajeModificadores_1,gbc_player);
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 2;
        PanelPlayer.add(percentBoxesEspecial_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(porcentajeCajasEs_1,gbc_player);
        
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 3;
        PanelPlayer.add(snakes_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(numberSnakes_1,gbc_player);
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 4;
        PanelPlayer.add(stairs_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(numberStairs_1,gbc_player);
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 5;
        PanelPlayer.add(rows_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(numeRows_1,gbc_player);
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 6;
        PanelPlayer.add(columns_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(numeColumns_1,gbc_player);
        
        
        gbc_player.gridx = 1;
        gbc_player.gridy = 7;
        PanelPlayer.add(transform_1, gbc_player);
        gbc_player.gridx = 0;
        PanelPlayer.add(selectTransform_1,gbc_player);
        
        back1 = new JButton("HOME");
        playPlayer = new JButton("PLAY");
        back1.setBorderPainted(false);
        playPlayer.setBorderPainted(false);
        back1.setBackground(Color.GRAY);
        playPlayer.setBackground(Color.GRAY);
        JPanel menuInf = new JPanel(new GridLayout(1,3));
        menuInf.add(back1);
        menuInf.add(new JLabel());
        menuInf.add(playPlayer);
        
        panel4.add(menuInf, BorderLayout.SOUTH);
        mainPanel.add(panel4, "panel4");
        
       
  
        
        
        
     
        //panel3 crea dos botones en la mitad de la pantalla
        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        // Crear un nuevo panel con un GridBagLayout
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.PINK);
        panel3.add(centerPanel, BorderLayout.CENTER);
        // Crear los botones
        players = new JButton("player vs player");
        machine = new JButton("player vs machine");
        players.setBorderPainted(false);
        machine.setBorderPainted(false);
        // Agregar los botones al panel con el GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 20, 20);
        centerPanel.add(players, gbc);
        gbc.gridx = 1;
        centerPanel.add(machine, gbc);
	    players.setBackground(Color.GRAY);
	    machine.setBackground(Color.GRAY);
	    players.setPreferredSize(new Dimension(200, 30));
	    machine.setPreferredSize(new Dimension(200, 30));
	    players.setFont(new Font("Arial", Font.BOLD, 15));
	    machine.setFont(new Font("Arial", Font.BOLD, 15));
	    mainPanel.add(panel3, "panel3");
	   
	   
	   
	   
	   
	    
	    
	   // pinta todo el tablero del juego.
	    
	   boardGeneral = new JPanel();
	   boardGeneral.setLayout(new GridLayout(1, 2));
	   
	   
	   //OYENTE para jugar
	   ActionListener playDICE = new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		        //JButton clickedButton = (JButton) e.getSource(); // Obtener el botón que se ha hecho clic
		        //String buttonText = clickedButton.getText(); // Obtener el texto del botón
		        //JOptionPane.showMessageDialog(null, "Se ha hecho clic en el botón: " + buttonText); // Mostrar un mensaje de diálogo con el texto del botón
		    	if (machineActive) {
		    		positionStart_Player1 = false;

		    		playPlayerHuman1();
		    		positionStart_Player2 = false;

	    			playPlayerMachine();
		    		return;
		    	}
		    	
		    	if (flag && player) {
		    		positionStart_Player1 = false;
		    		playPlayerHuman1();
		    	} 
		    	else if (flag && otherPlayer) {
		    		positionStart_Player2 = false;
		    		playPlayerHuman2();
		    	}
     
			    }
			};
			
		
		
		//Es el panel de las fichas
		panelPrimer = new JPanel();
	       panelPrimer.setBackground(Color.PINK);
	       int nRows =  this.nRows;
	       int nColumns = this.nColumns;
	       panelPrimer.setLayout(new GridLayout(nRows, nColumns));
	       //crea todas las fichas y si se le da en cualquier ficha entonces se empieza a jugar

       
	   boardGeneral.setBackground(Color.PINK);
       
       boardGeneral.add(panelPrimer);
     
       
       
       
       
   
       // Segunda casilla con un BorderLayout donde va el dice y los players
        panelSegundo = new JPanel(new BorderLayout());
       panelSegundo.setBackground(Color.PINK);
       // Cabecera con un JPanel con GridLayout de 1 fila y 2 columnas
        header = new JPanel(new GridLayout(1, 2));
       header.setBackground(Color.PINK);
       header.add(new JLabel("Player 1"));
       header.add(new JLabel("Player 2"));
       panelSegundo.add(header, BorderLayout.NORTH);
       
       
       
       
       
       
       // Centro con dos botones verticales
        center = new JPanel(new GridBagLayout());
       center.setBackground(Color.PINK);
       
       
       // Agregar los botones al panel con el GridBagLayout
       GridBagConstraints gbc_Dice = new GridBagConstraints();
       
       imgDice = new ImageIcon(String.format("img/%s.png",dado));
       imagenOriginalDice = imgDice.getImage();
       imagenEscaladaDice = imagenOriginalDice.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
       iconoEscaladoDice = new ImageIcon(imagenEscaladaDice);
      
  
       dice = new JButton();
       dice.setIcon(iconoEscaladoDice);
       dice.setEnabled(false);
       playDice = new JButton("PLAY");
       paintAll = new JButton("Preparete");
       playDice.setEnabled(false);
       
       dice.setSize(new Dimension(300,300));
       dice.setBorderPainted(false);
       playDice.setBorderPainted(false);
       paintAll.setBorderPainted(false);
       dice.setBackground(null);
       dice.setForeground(null);
       playDice.setBackground(Color.GRAY);
       paintAll.setBackground(Color.GRAY);
       dice.addActionListener(playDICE);
       playDice.addActionListener(playDICE);
       
       gbc_Dice.gridx = 0;
       gbc_Dice.gridy = 0;
       gbc_Dice.insets = new Insets(5, 5, 5, 5);
       center.add(dice, gbc_Dice);
       
       gbc_Dice.gridy = 1;
       center.add(playDice,gbc_Dice);
      
       
       gbc_Dice.gridy = 2;
       center.add(paintAll,gbc_Dice);
       panelSegundo.add(center, BorderLayout.CENTER);
       
       mainPanel.add(boardGeneral, "boardGeneral");
       
       
       
       
       
       // Parte inferior con solo un JPanel
       
       footer = new JPanel();  
       
       
       JLabel put = new JLabel("START");
       put.setHorizontalAlignment(SwingConstants.CENTER);
       
       footer.setBackground(Color.PINK);
       footer.setLayout( new GridLayout(1,1));
       
       footer.setPreferredSize(new Dimension(footer.getWidth(), 50));
       footer.add(put);
       panelSegundo.add(footer, BorderLayout.SOUTH);
       
       
       boardGeneral.add(panelSegundo);

       
       
       
        JPanel buttonPanel2 = new JPanel();
       //buttonPanel2.add(button1);
        // agregamos los botones al panel principal
        JPanel buttonPanel = new JPanel();
        //buttonPanel.add(button2);
        //buttonPanel.add(button3);
        add(buttonPanel2, BorderLayout.LINE_START);
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
	}
	/**
	 * muestra un JOptionPane en milisegundos.
	 * se usa para que se pueda actualizar la pantalla.
	 * @param mensaje
	 * @param duracion
	 */
	public static void mostrarCuadroDialogoAutoCierre(String mensaje, int duracion) {
        JOptionPane pane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        final javax.swing.JDialog dialog = pane.createDialog(null, "Mensaje");
        
        Timer timer = new Timer(duracion, e -> {
            dialog.dispose(); // Cerrar el cuadro de diálogo después de la duración especificada
        });
        timer.setRepeats(false); // No se repite el temporizador
        
        timer.start();
        dialog.setVisible(true);
    }
	
	/**
	 * se usa cuando sacan modificador de cambiar de posición.
	 */
	public void changePosition() {
		int[] copy = positionPlayer_1;
		int copy1 = indexAfterPlayer1;
    	positionPlayer_1=positionPlayer_2;
    	positionPlayer_2 =copy;
    	
    	clearPieces(indexAfterPlayer1, true);
    	clearPieces(indexAfterPlayer2, false);
    	paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
    	paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 2);
    	preparedBoxes(poobStairs.getBoxes(), false);
    	
    	paintStadisticsPlayers();
    	indexAfterPlayer1 = indexAfterPlayer2;
    	indexAfterPlayer2 = copy1;

	}
	/**
	 * pinta el tablero despues de abrir el archivo
	 */
	public void paintLater() {
//		System.out.println( poobStairs.isCanSnakeStairsTransformer()+ "primera julian pureba");
//		   System.out.println( poobStairs.isSnakeStairsTransform()+ "primera mia pureba");
	   poobStairs.setCanSnakeStairsTransformer(transformBox);
//	   System.out.println(transformBox+ " segunda pureba");
//	   System.out.println( poobStairs.isCanSnakeStairsTransformer()+ " julian pureba");
//	   System.out.println( poobStairs.isSnakeStairsTransform()+ " mia pureba");
	   playDice.setEnabled(false);
       dice.setEnabled(false);
       paintAll.setEnabled(true);
	   nRows =  poobStairs.getnRows();
       nColumns = poobStairs.getnColumns();
       boardGeneral.setBackground(Color.PINK);
       colorPrimero = poobStairs.getColorPlayer1();
       colorSegundo = poobStairs.getColorPlayer2();
       
       flag = true;
       updateBoardLaterData(nRows, nColumns);
       cardLayout.show(mainPanel, "boardGeneral");
	   archivoAbierto= true;
	   indexAfterPlayer1 = positionPlayer_1[0]*nColumns+positionPlayer_1[1];
	   indexAfterPlayer2 =positionPlayer_2[0]*nColumns+positionPlayer_2[1];
	   putHeader(namePlayer1,namePlayer2);
            
	}
	/**
	 * Esta pendiente de quien gana y de ser así muestra un JOptionPane para salir o empezar uno nuevo.
	 */
	public void win() {
		int player = poobStairs.win();
		if (player != 0) {
//			ArrayList<String> options = new ArrayList<String>();
//			options.add("New");
//			options.add("Exit");
			String[] options = new String[2];
			options[0]= "New";
			options[1]= "Exit";
			String name  = player == 1 ? namePlayer1:namePlayer2;
			int selection = JOptionPane.showOptionDialog(this, "Win!!!\n" + name, "Winner", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, paintAnyImageJOptionPane("win.jpg"), options, options[0]);
			if(selection == 0) {
				nuevo();
			}else {
				dispose();
			}
		}
	}
	/**
	 * Me muestra el dado y el modificador en un JOptionPane
	 * @param indexDice
	 */
	public int modificadorAndDice(ArrayList<int []>indexDice) {
		
        if(indexDice.size()==1) {
	    	JOptionPane.showMessageDialog(panelSegundo, "Take out = " + dado+ " but CAN YOU MOVE", "NOT MOVE", JOptionPane.INFORMATION_MESSAGE, paintDiceEachChange(dado));
	    	preparedBoxes(poobStairs.getBoxes(), false);
	    	return -507;
        }
		int size = indexDice.size();
		String[] opciones = new String[size-1];
		int seleccion = -1;
		for (int i = 0; i < size; i++) {
			if(i> 0 ) {
				if(indexDice.get(i)[0]==-5) {
					opciones[i-1] = "Cha. Pos.";
				}else {
				opciones[i-1] = ""+ indexDice.get(i)[0];
				}
//		        System.out.println(indexDice.get(i)[0] + " numero de posibilidades");
//		        System.out.println(size);
			}
			
		}
		if (indexDice.get(0)[1]== 0) {
			
	         seleccion = JOptionPane.showOptionDialog(panelSegundo, "Where do you want go?", "Boxes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,  paintDiceEachChange(dado), opciones, opciones[0]);
	         
	         //System.out.println(seleccion + " sin modificador");
	    }else if(indexDice.get(0)[1]== 1){
	    	
	         seleccion = JOptionPane.showOptionDialog(panelSegundo, "Where do you want go? You have MODIFIER ADVANCE", "Boxes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,  paintDiceEachChange(dado), opciones, opciones[0]);
	         //System.out.println(seleccion + " modificador avanzar");
	    	//JOptionPane.showMessageDialog(null, "Sacaste " + dado+ " con modificador de avanzar", "Dado + Modificador", JOptionPane.INFORMATION_MESSAGE, paintDiceEachChange(1));
	    }else if(indexDice.get(0)[1]== 2){
	    	
	         seleccion = JOptionPane.showOptionDialog(panelSegundo, "Where do you want go? You have MODIFIER BACK", "Boxes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,  paintDiceEachChange(dado), opciones, opciones[0]);
	         //System.out.println(seleccion + "  modificador retroceder");
	    }else if(indexDice.get(0)[1]==3) {
	         seleccion = JOptionPane.showOptionDialog(panelSegundo, "Where do you want go? You have MODIFIER MULTIPLY", "Boxes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,  paintDiceEachChange(dado), opciones, opciones[0]);

	    }else if(indexDice.get(0)[1]==4) {
	         seleccion = JOptionPane.showOptionDialog(panelSegundo, "Where do you want go? You have MODIFIER CHANGE POSITION", "Boxes", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,  paintDiceEachChange(dado), opciones, opciones[0]);
	    }
		
		//System.out.println(seleccion );
		
		
		if (seleccion >= 0) {
			if(opciones[seleccion] == "Cha. Pos.") return -5;
		    return indexDice.get(seleccion+1)[0];
		} else {
		    return -1;
		}
	}
	/**
	 * Método para que la máquina juegue.
	 */
	public void playPlayerMachine() {
		play.setEnabled(false);
		playDice.setEnabled(false);
		mostrarCuadroDialogoAutoCierre("Play Machine",0);
		ArrayList<int[]> posibilities = poobStairs.playWithOptionPlayer2();
		dado = posibilities.get(0)[0];
		
		int selection = (poobStairs.getPlayer(2) instanceof MachinePlayerAprentice)?((MachinePlayerAprentice)(poobStairs.getPlayer(2))).playSelection(posibilities):((MachinePlayerBegginer)(poobStairs.getPlayer(2))).playSelection(posibilities);
		 
	    if (selection == -5) {
	    	poobStairs.modifierChangePiece(poobStairs.getPlayer(2), poobStairs.getPlayer(1), 2);
	    	changePosition();
	    	paintStadisticsPlayers();
	    	otherPlayer = false;
		    player = true; 
		    play.setEnabled(true);
			playDice.setEnabled(true);
	    }else {
	    	try {
    		ArrayList<int[]> index_2_Dice = poobStairs.movePlayer(2, selection);
    	    if (transformBox && (!positionStart_Player1))paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
    	    if(selection != -2) {
    	    for (int i = 0; i < index_2_Dice.size(); i++) {
    	        paintDiceEachChange();// Actualización de la imagen
    	        paintPiceToPice(index_2_Dice, i, player);
    	        
    	    }
   		 	

    	    }else {
    	    	
    	    	
    	    }
    	    if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
    	   	if (transformBox) auxiliar(selection);
    	    paintStadisticsPlayers();
    	    win();
    	    paintStadisticsPlayers();
    	    }
	    	catch (PoobStairsException e) {
				if(e.getMessage().equals(e.BOXPREGUNTONA)) {
					ArrayList<int[]> index_2_Dice = ((PoobStairsExceptionWithArray) e).getArray();
		    	    if (transformBox)paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
		    	    if(selection != -2) {
		    	    System.out.println("jugador 2 " + dado);
		    	    for (int i = 0; i < index_2_Dice.size(); i++) {
		    	        
		    	        paintDiceEachChange();// Actualización de la imagen
		    	        paintPiceToPice(index_2_Dice, i, player);
		    	    }
		    	    }else {
		    	    	paintDiceEachChange();
		    	    }
		    	   	if (transformBox) auxiliar(selection);
		    	   	if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
		    	    paintStadisticsPlayers();
		    	    win();
					movePreguntona(2,player,true);
					paintStadisticsPlayers();
		    	    win();
		    	    paintStadisticsPlayers();
					otherPlayer = false;
				    player = true; 
			}
				if(e.getMessage().equals(e.INVALIDVALUE)) {
					paintDiceEachChange();
					paintStadisticsPlayers();
					otherPlayer = false;
				    player = true;
				}
			}
	}  
	    otherPlayer = false;
	    player = true; 
	    
	    play.setEnabled(true);
		playDice.setEnabled(true);

	}
	/**
	 * Hace que se pueda jugar el jugador 1
	 */
	public void playPlayerHuman1() {
		
		ArrayList<int[]> posibilities = poobStairs.playWithOptionPlayer1();
		
		dado = posibilities.get(0)[0];
		int selection = modificadorAndDice(posibilities);
	
		if (selection == -5) {
	    	poobStairs.modifierChangePiece(poobStairs.getPlayer(1), poobStairs.getPlayer(2), 1);
	    	changePosition();
	    	otherPlayer = true;
		    player = false;
		}else {
			
		//System.out.println(selection+" seleccion");
	    ArrayList<int[]> indexDice;
		try {
			indexDice = poobStairs.movePlayer(1, selection);
		
		// seleccion manda desde cero y la función acepta desde uno.
	    if (transformBox && (!positionStart_Player2))paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 2);

	    System.out.println("jugador 1 " + dado);
	    
	    if(selection != -2) {
	    	
	    for (int i = 0; i < indexDice.size(); i++) {
            paintDiceEachChange();// Actualización de la imagen
            paintPiceToPice(indexDice, i, player);
	    }
	    }else {
	    	paintDiceEachChange();
	    }
	    if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
	    if (transformBox) auxiliar(selection);
	    paintStadisticsPlayers();
	    win();
	    
		} catch (PoobStairsException e) {
			if(e.getMessage().equals(e.BOXPREGUNTONA)) {
				ArrayList<int[]> index_2_Dice = ((PoobStairsExceptionWithArray) e).getArray();
	    	    if (transformBox)paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
	    	    if(selection != -2) {
	    	    System.out.println("jugador 2 " + dado);
	    	    for (int i = 0; i < index_2_Dice.size(); i++) {
	    	        
	    	        paintDiceEachChange();// Actualización de la imagen
	    	        paintPiceToPice(index_2_Dice, i, player);
	    	    }
	    	    }else {
	    	    	paintDiceEachChange();
	    	    }
	    	    if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
	    	   	if (transformBox) auxiliar(selection);
	    	    paintStadisticsPlayers();
	    	    win();
				movePreguntona(1,player,false);
				otherPlayer = true;
			    player = false;
		}
			if(e.getMessage().equals(e.INVALIDVALUE)) {
				JOptionPane.showMessageDialog(panelSegundo, e.getMessage());
				paintDiceEachChange();
				paintStadisticsPlayers();
				otherPlayer = true;
			    player = false;
			}
		}
		otherPlayer = true;
	    player = false;
	}
	}
	/**
	 * Se usa para hacer juagar ala segundo juagador humano
	 */
	public void playPlayerHuman2() {
		ArrayList<int[]> posibilities = poobStairs.playWithOptionPlayer2();
		dado = posibilities.get(0)[0];
		int selection = modificadorAndDice(posibilities);
		
		
	    if (selection == -5) {
	    	poobStairs.modifierChangePiece(poobStairs.getPlayer(2), poobStairs.getPlayer(1), 2);
	    	changePosition();
	    	otherPlayer = false;
		    player = true; 
	    }else {
	    	try {
    		ArrayList<int[]> index_2_Dice = poobStairs.movePlayer(2, selection);
    	    if (transformBox && (!positionStart_Player1))paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
    	    if(selection != -2) {
    	    System.out.println("jugador 2 " + dado);
    	    for (int i = 0; i < index_2_Dice.size(); i++) {
    	        
    	        paintDiceEachChange();// Actualización de la imagen
    	        paintPiceToPice(index_2_Dice, i, player);
    	    }
    	    }else {
    	    	paintDiceEachChange();
    	    }
    	    if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
    	   	if (transformBox) auxiliar(selection);
    	    paintStadisticsPlayers();
    	    win();
	    	} catch (PoobStairsException e) {
				if(e.getMessage().equals(e.BOXPREGUNTONA)) {
					ArrayList<int[]> index_2_Dice = ((PoobStairsExceptionWithArray) e).getArray();
		    	    if (transformBox)paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
		    	    if(selection != -2) {
		    	    System.out.println("jugador 2 " + dado);
		    	    for (int i = 0; i < index_2_Dice.size(); i++) {
		    	        
		    	        paintDiceEachChange();// Actualización de la imagen
		    	        paintPiceToPice(index_2_Dice, i, player);
		    	    }
		    	    }else {
		    	    	paintDiceEachChange();
		    	    }
		    	    if (transformBox) preparedBoxes(poobStairs.getBoxes(), false);
		    	   	if (transformBox) auxiliar(selection);
		    	    paintStadisticsPlayers();
		    	    win();
					movePreguntona(2,player,false);
					otherPlayer = false;
				    player = true; 
			}
				if(e.getMessage().equals(e.INVALIDVALUE)) {
					JOptionPane.showMessageDialog(panelSegundo, e.getMessage());
					paintDiceEachChange();
					paintStadisticsPlayers();
					otherPlayer = false;
				    player = true;
				}
				
			}
    	    
	}
	    otherPlayer = false;
	    player = true; 
	}
	
	public void gameGeneral(int playerPrincilal, int playerAux, boolean Machine) {
		play.setEnabled(false);
		playDice.setEnabled(false);
		ArrayList<int[]> posibilities = poobStairs.playWithOptionPlayer2();
		dado = posibilities.get(0)[0];
		if (Machine)mostrarCuadroDialogoAutoCierre("Play Machine",0);
		int selection;
		if(Machine) {
			selection = (poobStairs.getPlayer(2) instanceof MachinePlayerAprentice)?((MachinePlayerAprentice)(poobStairs.getPlayer(2))).playSelection(posibilities):((MachinePlayerBegginer)(poobStairs.getPlayer(2))).playSelection(posibilities);

		}else {
			selection = modificadorAndDice(posibilities);
		}

		
	    if (selection == -5) {
	    	poobStairs.modifierChangePiece(poobStairs.getPlayer(playerPrincilal), poobStairs.getPlayer(playerAux), playerPrincilal);
	    	changePosition();
	    	otherPlayer = !otherPlayer;
		    player = !player; 
	    }else {
	    	try {
    		ArrayList<int[]> index_2_Dice = poobStairs.movePlayer(playerPrincilal, selection);
    		
    	    if (transformBox && playerAux == 1)paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
    	    if (transformBox && playerAux == 2)paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 1);

    	    if(selection != -2) {
    	    System.out.println("jugador 2 " + dado);
    	    for (int i = 0; i < index_2_Dice.size(); i++) {
    	        
    	        paintDiceEachChange();// Actualización de la imagen
    	        paintPiceToPice(index_2_Dice, i, player);
    	    }
    	    }else {
    	    	paintDiceEachChange();
    	    }
    	   	if (transformBox) auxiliar(selection);
    	    paintStadisticsPlayers();
    	    win();
	    	} catch (PoobStairsException e) {
				if(e.getMessage().equals(e.BOXPREGUNTONA)) {
					ArrayList<int[]> index_2_Dice = ((PoobStairsExceptionWithArray) e).getArray();
					if (transformBox && playerAux == 1)paintPieces(positionPlayer_1, PoobStairsGUI.this.colorPrimero, 1);
		    	    if (transformBox && playerAux == 2)paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 1);
		    	    
		    	    if(selection != -2) {
		    	    System.out.println("jugador 2 " + dado);
		    	    for (int i = 0; i < index_2_Dice.size(); i++) {
		    	        
		    	        paintDiceEachChange();// Actualización de la imagen
		    	        paintPiceToPice(index_2_Dice, i, player);
		    	    }
		    	    }else {
		    	    	paintDiceEachChange();
		    	    }
		    	   	if (transformBox) auxiliar(selection);
		    	    paintStadisticsPlayers();
		    	    win();
					movePreguntona(playerPrincilal,player,false);
					otherPlayer = !otherPlayer;
				    player = !player; 
			}
			}
    	    
	}
	    otherPlayer = !otherPlayer;
	    player = !player; 
	    play.setEnabled(true);
	 	playDice.setEnabled(true);
	}
	
	/**
	 * muestras las preguntas
	 * @param player
	 * @param player1
	 */
	public void movePreguntona(int player, boolean player1, boolean machine) {
		HashMap<String, String> questions =poobStairs.getPreguntas();
		Random random = new Random();
        int randomIndex = random.nextInt(questions.size());
        String ask = (String) questions.keySet().toArray()[randomIndex];
        Object[] options = {"Sí", "No"};
        int answer;
		if(!machine) {
		
		answer = JOptionPane.showOptionDialog(
		        null,
		        ask,
		        "Preguntona",
		        JOptionPane.YES_NO_OPTION,
		        JOptionPane.QUESTION_MESSAGE,
		        iconoEscaladoDice,
		        options,
		        options[0]
		);}else {
			answer= ((MachinePlayer)poobStairs.getPlayer(2)).answerQuestions();
		}
		
		if (answer == JOptionPane.YES_OPTION) {
			if(questions.get(ask).equals("Si")){
				try {
					ArrayList<int[]> indexDice = poobStairs.movePlayer(player, 1);
					if (transformBox)paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 2);

				    System.out.println("jugador 1 " + dado);
				    
				    
				    for (int i = 0; i < indexDice.size(); i++) {
			            paintDiceEachChange();// Actualización de la imagen
			            paintPiceToPice(indexDice, i, player1);
				    }
				   
				    
				    if (transformBox) auxiliar(1);
				    paintStadisticsPlayers();
				    win();
				} catch (PoobStairsException e1) {
					otherPlayer = !otherPlayer;
				    this.player = !this.player; 
				}
			}
			}else {
				if(questions.get(ask).equals("No")){
					try {
						ArrayList<int[]> indexDice = poobStairs.movePlayer(player, 1);
						if (transformBox)paintPieces(positionPlayer_2, PoobStairsGUI.this.colorSegundo, 2);

					    System.out.println("jugador 1 " + dado);
					    
					    
					    for (int i = 0; i < indexDice.size(); i++) {
				            paintDiceEachChange();// Actualización de la imagen
				            paintPiceToPice(indexDice, i, player1);
					    }
					   
					    
					    if (transformBox) auxiliar(1);
					    paintStadisticsPlayers();
					    win();
					} 
					catch (PoobStairsException e1) {
						otherPlayer = !otherPlayer;
					    this.player = !this.player; 
					}
			}
		
	}
	}
	/**
	 * Mueve la pieza con 0.5 segundos de retrazo, de ahí que se ve un movimiento de la pieza
	 * @param indexDice
	 * @param i
	 * @param player_1
	 */
	public void paintPiceToPice(ArrayList<int[]> indexDice, int i, boolean player_1) {
		
		if (player_1) {
			positionPlayer_1[0] =  indexDice.get(i)[0];
			positionPlayer_1[1] =  indexDice.get(i)[1];
		}if (!player_1) {
			positionPlayer_2[0] =  indexDice.get(i)[0];
			positionPlayer_2[1] =  indexDice.get(i)[1];
		}
		
		if(!transformBox)preparedBoxes(poobStairs.getBoxes(), false);
		paintPieces(panelPrimer, indexDice.get(i)[0], indexDice.get(i)[1]); // Pintar óvalos
		if(!transformBox)preparedBoxes(poobStairs.getBoxes(), false);
        try {
            Thread.sleep(500); // Retraso de 1 segundo
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
	}
	/**
	 * pone las imagenes en el tablero
	 * @param boxes
	 */
	public void putBoxesSpecials(Box[][] boxes) {
		for (int i = 0; i < nRows; i ++) {
			for(int j = 0; j < nColumns; j ++) {
				int index = i* nColumns +j;
				if(boxes[i][j] instanceof MortalBox) {
					putImageInBoxes("mortal.jpg", index, null);
				}
				if(boxes[i][j] instanceof SaltarinaNBox) {
					putImageInBoxes("saltarina.jpg", index, null);
				}
				if(boxes[i][j] instanceof SaltarinaInversaNBox) {
					putImageInBoxes("saltarina_inversa.png", index, null);
				}
				if(boxes[i][j] instanceof RetrocesoBox) {
					putImageInBoxes("snake_inicio.png", index, null);
				}
				if(boxes[i][j] instanceof AvanceBox) {
					
					putImageInBoxes("avance.jpg", index, null);
				}
				if(boxes[i][j] instanceof PreguntonaBox) {
					putImageInBoxes("preguntona.png", index, null);
				}
			
			}
		}
	}
	/**
	 * Pinta las escaleras y serpientes
	 * @param boxes
	 */
	public void putSnakesAndStairs(Box[][] boxes){
		for (int i = 0; i < nRows; i ++) {
			for(int j = 0; j < nColumns; j ++) {
				
				if( boxes[i][j] instanceof SnakeStair && boxes[i][j].isActive()) {
				
					int[] auxiliarCoor = new int[2];
					int [] coordenada = boxes[i][j] instanceof DualSnakeStair? boxes[i][j].getCoordenate(): auxiliarCoor;
					if (boxes[i][j] instanceof DualSnakeStair) {
						System.out.println(coordenada[0] +" "+coordenada[1]+ " soy Dual" );
					}
					//if (transformBox)putBackground(boxes[i][j]);
					int[] start = ((SnakeStair)boxes[i][j]).getStart();
					int[] end = ((SnakeStair)boxes[i][j]).getEnd();
					paintSnakesOrStairs(start[0], start[1], end[0], end[1], ((SnakeStair)boxes[i][j]).isSnake());
					
					//boardGeneral.revalidate();
				}
			}
		}
	}

	/**
	 * paint to stairs and snakes as lines and mortal boxes
	 * @param boxes
	 * @param panelPrimer
	 */
	public void preparedBoxes(Box[][] boxes, Boolean conImagenes) {
		if(conImagenes) {
		putBoxesSpecials(boxes);
		}
		putSnakesAndStairs(boxes);
		paintStadisticsPlayers();
	}
/**
 * Da la imagen con las dimensiones de una label.
 * @param variable
 * @return
 */
public void putImageInBoxes(String variable, int index, Border borde) {
	Border borde1 = borde == null ? BorderFactory.createLineBorder(Color.BLACK, 2, true): borde;
	
	if(flag) {
	
	Component componente = panelPrimer.getComponent(index);
	panelPrimer.remove(componente);
	
	JLabel button = new JLabel();
	 
	
	//System.out.println("dame hop3" + boardGeneral.getHeight());
	ImageIcon imgDice = new ImageIcon(String.format("img/%s", variable));
	int labelWidth = (boardGeneral.getWidth() / 2)/nColumns;
	int labelHeight = boardGeneral.getHeight() / nRows;
	Image scaledImage = imgDice.getImage().getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);
	ImageIcon scaledIcon = new ImageIcon(scaledImage);
	button.setBorder(borde1);
	button.setIcon(scaledIcon);
	panelPrimer.add(button, index);
	
   }
	}
/**
 * Muestra un mensaje de selección solo si se esta jugando con modificadores
 * if (transformBox) auxiliar(selection)
 * @param i
 */
public void auxiliar(int i) {
	putBackground();
	mostrarCuadroDialogoAutoCierre("Cambio", 1);
	preparedBoxes(poobStairs.getBoxes(), false);
	if (!positionStart_Player1)paintPieces(positionPlayer_1, colorPrimero, 1);
	if (!positionStart_Player2)paintPieces(positionPlayer_2, colorSegundo, 2);
}

/**
 * refresca una sola caja 
 * @param box
 */
public void putBackground(Box box) {
    int[] coordinate = box.getCoordenate();
    int index = coordinate[0] * nColumns + coordinate[1];
    
    if (flag) {
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true);
        
        Component component = panelPrimer.getComponent(index);
        JLabel button = (JLabel) component;
        
        Color color = button.getBackground();
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorder(border);
        //panelPrimer.revalidate(); // Actualizar el panel
    }
}

/**
 * Borra el tablero y deja las casillas con el color de fondo original
 */
public void putBackground() {
	putSnakesAndStairs(poobStairs.getBoxes());
	putBoxesSpecials(poobStairs.getBoxes());
	 Border borde = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true);
     // Primera casilla con otra grilla
	   
    
     //crea todas las fichas y si se le da en cualquier ficha entonces se empieza a jugar

     
     for (int i = 0; i < nRows; i++) {
  	    for (int j = 0; j < nColumns; j++) {
  	        int[] coordenate = new int[2];
  	        int index = i* nColumns + j;
  	        Component component = panelPrimer.getComponent(index);
  	        JLabel button = (JLabel) component;
  	        Color color = button.getBackground();
  	        coordenate[0] = i;
  	        coordenate[1] = j;
  	        //int positionReal = poobStairs.getPosition(coordenate);
  	        //JLabel button = new JLabel(" Box " + (positionReal));
  	        button.setForeground(Color.WHITE);
  	        button.setBackground(color);
  	        button.setOpaque(true);
  	        button.setBorder(borde);
  	        //System.out.println(button.getWidth()+"ancho alto"+button.getHeight());
  	        
  	    }
  	    
     }
     putBoxesSpecials(poobStairs.getBoxes());
     putSnakesAndStairs(poobStairs.getBoxes());
     return;
}



/**
 * pinta la imagen del dado en el panelSegundoo
 */
public void paintDiceEachChange() {
	imgDice = new ImageIcon(String.format("img/%s.png",dado));
    imagenOriginalDice = imgDice.getImage();
    imagenEscaladaDice = imagenOriginalDice.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    iconoEscaladoDice = new ImageIcon(imagenEscaladaDice);
    dice.setIcon(iconoEscaladoDice);
    
}

/**
 * se usa para pintar la imagen del dado en el JOptionPane
 * @param i
 * @return ImageIcon
 */
public ImageIcon paintDiceEachChange(int dado) {
	imgDice = new ImageIcon(String.format("img/%s.png",dado));
    imagenOriginalDice = imgDice.getImage();
    imagenEscaladaDice = imagenOriginalDice.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    iconoEscaladoDice = new ImageIcon(imagenEscaladaDice);
    return iconoEscaladoDice;
}
/**
 * se usa para pintar cualquier imagne en un JOptionPane
 * @param dado
 * @return
 */
public ImageIcon paintAnyImageJOptionPane(String imagen) {
	imgDice = new ImageIcon(String.format("img/%s",imagen));
    imagenOriginalDice = imgDice.getImage();
    imagenEscaladaDice = imagenOriginalDice.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    iconoEscaladoDice = new ImageIcon(imagenEscaladaDice);
    return iconoEscaladoDice;
}
	

/*
 * pone el header del tablero de juego
 */
public void putHeader(String name_1, String name_2) {
	String text_1 = name_1 == null  ? name.getText(): name_1; 
	String text_2 = name_2 == null  ? name2.getText(): name_2;
	header.setBackground(Color.PINK);
    JLabel nombreInHeader = new JLabel(text_1);
    JLabel nombre2InHeader = new JLabel(text_2);
    nombreInHeader.setHorizontalAlignment(SwingConstants.CENTER);
    nombre2InHeader.setHorizontalAlignment(SwingConstants.CENTER);
    Component removerFirtItem = header.getComponent(0);
    header.remove(removerFirtItem);
    
    if (player) {
 	   nombre2InHeader.setBackground(colorSegundo);
 	   nombre2InHeader.setOpaque(true);
    }
    if (primeraVez && !player) {
 	   nombreInHeader.setBackground(colorPrimero);
 	   nombreInHeader.setOpaque(true);
 	   player = true;
 	   primeraVez = false;
    }
    header.add(nombreInHeader, 0);
    
    Component removerSecondItem = header.getComponent(1);
    header.remove(removerSecondItem);
   
    if(otherPlayer) {
 	   nombreInHeader.setBackground(colorPrimero);
 	   nombreInHeader.setOpaque(true);
    }
    
    header.add(nombre2InHeader,1);
    header.revalidate();
    
    
}
/**
 * pone el footer del panelSegundo, es decir el footer
 * donde está el dado y el botón de play
 */
public void putFooter() {
	
	JPanel staditics = new JPanel();
    staditics.setLayout(new GridLayout(3,2));
//    System.out.println("one "+ poobStairs.getInformationPlayer1()[0]);
//    System.out.println("one "+ poobStairs.getInformationPlayer1()[1]);
//    System.out.println("one "+ poobStairs.getInformationPlayer1()[2]);
//    System.out.println("one "+ poobStairs.getInformationPlayer1()[3]);
//    System.out.println("###################################" );
//    System.out.println("two "+poobStairs.getInformationPlayer2()[0]);
//    System.out.println("two "+poobStairs.getInformationPlayer2()[1]);
//    System.out.println("two "+poobStairs.getInformationPlayer2()[2]);
//    System.out.println("two "+poobStairs.getInformationPlayer2()[3]);
//    //System.out.println("Real "+ poobStairs.getPlayer1().print());

    String text_1 = player ? String.valueOf(poobStairs.getInformationPlayer1()[0]):String.valueOf(poobStairs.getInformationPlayer2()[0]);
    String text_2 = player ? String.valueOf(poobStairs.getInformationPlayer1()[1]):String.valueOf(poobStairs.getInformationPlayer2()[1]);
    String text_3 = player ? String.valueOf(poobStairs.getInformationPlayer1()[2]):String.valueOf(poobStairs.getInformationPlayer2()[2]);
    String text_4 = player ? String.valueOf(poobStairs.getInformationPlayer1()[3]):String.valueOf(poobStairs.getInformationPlayer2()[3]);
    String text_5 = player ? String.valueOf(poobStairs.getInformationPlayer1()[4]):String.valueOf(poobStairs.getInformationPlayer2()[4]);
    JLabel positionPiece = new JLabel("The piece is:  "+ text_1);
    JLabel nSnakesTakes = new JLabel("Snakes Taken:  "+text_2);
    JLabel nStairsTakes = new JLabel("Stairs Taken:  "+text_3);
    JLabel nBoxesTakes = new JLabel("Special Box Taken:  "+text_4);
    JLabel maxBox = new JLabel("Max Box:  "+ text_5);
    
    positionPiece.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    nSnakesTakes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    nStairsTakes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    nBoxesTakes.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    maxBox.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    
    Component removerFirtItem_ = footer.getComponent(0);
    footer.remove(removerFirtItem_);
    staditics.add(positionPiece);
    staditics.add(maxBox);
    staditics.add(nSnakesTakes);
    staditics.add(nBoxesTakes);
    staditics.add(nStairsTakes);
    
    
    
    
    
    Color colorOfFooter = player ? colorPrimero:colorSegundo;
    Border borde = BorderFactory.createLineBorder(colorOfFooter, 4, true);
    //staditics.setBackground(colorOfFooter);  
    staditics.setBorder(borde);
    //Border borde = BorderFactory.createLineBorder(Color.GRAY, 3, true);
    //staditics.setBorder(borde);
    footer.add(staditics);
}


	/**
	 * pinta el header y el footer de la segunda parte del tablero
	 */
	public void paintStadisticsPlayers() {	
	       putHeader(namePlayer1, namePlayer2);
	       putFooter(); 
	}
	

	
	/**
	 * pone las primeras dos fichas en la primera posición
	 */
	public void putFirtsPices() {
		//index = row * numColumns + col
		

		JPanel circlePanel = new JPanel() {
		    @Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        g.setColor(colorPrimero);
		        g.fillOval(0, 0, 20, 20);
		        g.setColor(colorSegundo);
		        g.fillOval(40, 0, 20, 20);
		    }
		};
		JLabel one = new JLabel("START");
		circlePanel.add(one);
		// Remover el primer componente del sur del panelSegundo
		BorderLayout borderLayout = (BorderLayout) panelSegundo.getLayout();
		Container southPanel = (Container) borderLayout.getLayoutComponent(BorderLayout.SOUTH);
		Component components = southPanel.getComponent(0);
		southPanel.remove(components);

		// Agregar el panel de círculos al sur del panelSegundo
		southPanel.add(circlePanel, BorderLayout.CENTER);
		panelSegundo.revalidate();		   
	}
	
	
	/**
	 * pinta las casillas.
	 * @param Rows
	 * @param Columns
	 */
	public void updateBoardLaterData(int Rows, int Columns) {
		   Border borde = BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true);
	       // Primera casilla con otra grilla
		   
	       panelPrimer = new JPanel();
	       panelPrimer.setBackground(Color.PINK);
	       int nRows =  Rows;
	       int nColumns = Columns;
	       panelPrimer.setLayout(new GridLayout(nRows, nColumns));
	       //crea todas las fichas y si se le da en cualquier ficha entonces se empieza a jugar
	  
	       
	       for (int i = 0; i < nRows; i++) {
	    	    for (int j = 0; j < nColumns; j++) {
	    	        int[] coordenate = new int[2];
	    	        coordenate[0] = i;
	    	        coordenate[1] = j;
	    	        int positionReal = poobStairs.getPosition(coordenate);
	    	        JLabel button = new JLabel(" Box " + (positionReal));
	    	        button.setForeground(Color.WHITE);
	    	        button.setBackground(Color.GRAY);
	    	        button.setOpaque(true);
	    	        button.setBorder(borde);
	    	        //System.out.println(button.getWidth()+"ancho alto"+button.getHeight());
	    	        
	    	        panelPrimer.add(button);
	    	    }
	    	    
	       }
	       
	       putWin();
	       
	       
	       Container  container = boardGeneral;
	       GridLayout layout = (GridLayout) boardGeneral.getLayout();
	       // Obtiene la primera box del gridLayout que es donde está la cuadricula.
	       Component oldComponent = container.getComponent(0 * layout.getColumns() + 0);
	       // Remover componente viejo y agregar componente nuevo
	       container.remove(oldComponent);
	       container.add(panelPrimer, 0 * layout.getColumns() + 0);
	       // Refrescar el contenedor para que se muestre el cambio
	       container.repaint();
//	       panelPrimer.repaint();
	       
	       return;
	}
	/**
	 * pone la imagen de ganador
	 */
	public void putWin() {
		Border borde = BorderFactory.createLineBorder(new Color(97,31,36), 3, true);
		
		int index = (nRows%2 == 0) ? 0 : nColumns-1 ;
		putImageInBoxes("win.jpg", index, borde);
		
		}
	
	/**
	 * pinta las lineas de las serpientes o escaleras
	 * @param panelPrimer
	 * @param cRowStart
	 * @param cColumnStart
	 * @param cRowEnd
	 * @param cColumnEnd
	 * @param snake
	 */
	public void paintSnakesOrStairs( int cRowStart, int cColumnStart, int cRowEnd, int cColumnEnd, boolean snake) {
		   GridLayout layout = (GridLayout) panelPrimer.getLayout();
		   int numColumns = layout.getColumns();
		   
		   int row = cRowStart;
		   int column = cColumnStart ;
		   int index = row*numColumns+column; // índice del botón
		   
		   int row2 = cRowEnd;
		   int column2 = cColumnEnd ;
		   int index2 = row2*numColumns+column2; // índice del botón
		   

		   
		   Component componente = panelPrimer.getComponent(index);
		   JLabel startLabel = (JLabel)componente;
		   
		   Component componente2 = panelPrimer.getComponent(index2);
		   JLabel endLabel = (JLabel)componente2;
		   
		   int startX = startLabel.getBounds().x + startLabel.getBounds().width / 2;
	       int startY = startLabel.getBounds().y + startLabel.getBounds().height / 2;
	       int endX = endLabel.getBounds().x + endLabel.getBounds().width / 2;
	       int endY = endLabel.getBounds().y + endLabel.getBounds().height / 2;
	       
	       JPanel boton = panelPrimer;
	       //Graphics g = boton.getGraphics();
	       g = (Graphics2D) boton.getGraphics();
	       if (snake) {
		   g.setColor(Color.GREEN);
	        // Dibuja la línea entre los puntos de inicio y fin calculados
		   g.setStroke(new BasicStroke(4));
		   g.drawLine(startX, startY, endX, endY);
		   g.setColor(Color.GREEN);
	       }else {
	    	   g.setStroke(new BasicStroke(4));
			   g.setColor(Color.CYAN);
		        // Dibuja la línea entre los puntos de inicio y fin calculados
			   g.drawLine(startX, startY, endX, endY);
			   g.setColor(Color.CYAN);
		   }
	       
	       
	     
	}
	
	
	/**
	 * Elimina la ficha del jugador después de haber jugado
	 * @param index
	 * @param player1
	 */
	public void clearPieces ( int index, boolean player1 ) {
		Component componente = panelPrimer.getComponent(index);
		   JLabel boton = (JLabel)componente;
		   Color fondo = boton.getBackground();
		   Graphics g = boton.getGraphics();
		   if (player1) {
			    g.setColor(fondo); // Color transparente
			    g.fillOval(boton.getWidth()/2, boton.getHeight()-boton.getWidth() / 3, boton.getWidth() / 4, boton.getHeight() / 4);
			} else {
			    g.setColor(fondo); // Color transparente
			    g.fillOval(boton.getWidth()/10, boton.getHeight()/20, boton.getWidth() / 4, boton.getHeight() / 4);
			}
	}
	
	/**
	 * pinta el jugador que juega en el indice que recibe
	 * @param panelPrimer
	 * @param index
	 */
	public void paintPieces(JPanel panelPrimer, int index ) {
		//index = row * numColumns + col
		   Component componente = panelPrimer.getComponent(index);
		   JLabel boton = (JLabel)componente;

		   Graphics g = boton.getGraphics();
		   if (g != null) {
			   if (flag && player) {
				   clearPieces(indexAfterPlayer1, true);
				   g = boton.getGraphics();
				   g.setColor(colorPrimero);
				   g.fillOval(boton.getWidth()/2, boton.getHeight()-boton.getWidth() / 3, boton.getWidth() / 4, boton.getHeight() / 4);
				   indexAfterPlayer1 = index;
			   }
			   if(flag && otherPlayer) {
				   clearPieces(indexAfterPlayer2, false);
				   //System.out.println("paso");
				   g = boton.getGraphics();
				   g.setColor(colorSegundo);
				   g.fillOval(boton.getWidth()/10, boton.getHeight()/20, boton.getWidth() / 4, boton.getHeight() / 4);
				   indexAfterPlayer2 = index;
			   }
			    
			} else {
			    System.out.println("El objeto Graphics es nulo");
			}
	       
	       
	}
	
	
	/**
	 * pinta cada jugador solo que recibe la posición donde juega
	 * @param panelPrimer
	 * @param cRow
	 * @param cColumn
	 */
	public void paintPieces(JPanel panelPrimer, int cRow, int cColumn ) {
		
		if (cRow== 0 && cColumn == 0) {
			if(positionStart_Player1 == true || positionStart_Player2 == true) {
				return;
			}
		}
		//index = row * numColumns + col
		   GridLayout layout = (GridLayout) panelPrimer.getLayout();
		   int numColumns = layout.getColumns();
		   
		   int row = cRow;
		   int column = cColumn ;
		   int index = row*numColumns+column; // índice del botón
		   
		
		   
		   Component componente = panelPrimer.getComponent(index);
		   JLabel boton = (JLabel)componente;

		   Graphics g = boton.getGraphics();
		   if (g != null) {
			   if (flag && player) {
				   
				   clearPieces(indexAfterPlayer1, true);
				   player1 = boton.getGraphics();
				   player1.setColor(colorPrimero);
				   player1.fillOval(boton.getWidth()/2, boton.getHeight()-boton.getWidth() / 3, boton.getWidth() / 4, boton.getHeight() / 4);
				   indexAfterPlayer1 = index;
				   }
			   if(flag && otherPlayer) {

				   clearPieces(indexAfterPlayer2, false);
				   //System.out.println("paso");
				   player2 = boton.getGraphics();
				   player2.setColor(colorSegundo);
				   player2.fillOval(boton.getWidth()/10, boton.getHeight()/20, boton.getWidth() / 4, boton.getHeight() / 4);
				   indexAfterPlayer2 = index;}
			    
			} else {
			    System.out.println("El objeto Graphics es nulo");
			}
	       
	       
	}
	
	/**

	 * pinta una pieza en una pocisión especifica
	 * @param positions
	 * @param color
	 * @param player
	 */
	public void paintPieces(int[] positions, Color color, int player) {
	    GridLayout layout = (GridLayout) panelPrimer.getLayout();
	    int numColumns = layout.getColumns();
	    int row = positions[0];
	    int column = positions[1];
	    int index = row * numColumns + column; // índice del botón
	    
	    Component component = panelPrimer.getComponent(index);
	    
	    if (component instanceof JLabel) {
	        JLabel label = (JLabel) component;
	        label.setOpaque(true);
	        
	        Graphics g = label.getGraphics();
	        if(player == 1) {
	        	g.setColor(color);
			    g.fillOval(label.getWidth()/2, label.getHeight()-label.getWidth() / 3, label.getWidth() / 4, label.getHeight() / 4);

	        }
	        if(player == 2) {
	        	g.setColor(color);
			    g.fillOval(label.getWidth()/10, label.getHeight()/20, label.getWidth() / 4, label.getHeight() / 4);

	        }
		   }
	}
	       
	

/**
 * hace toda la parte lógica para pintar la casillas impares o pares según se indeque
 * @param start
 * @param color
 */
	public void operationAnalytics( int start, Color color) {
		int number = 0;
		for (int i = 0; i < nRows; i++) {
			for (int j = 0; j < nColumns; j++) {
			
			int index = (i *nColumns) + j;
			
			number = i+j;
			
			if(number % 2 != 0 && start == 0) {
				//System.out.println("imparr "+number);
				Component picture = panelPrimer.getComponent(index);
				JLabel reallyPicture = (JLabel)picture;
				reallyPicture.setBackground(color);
				}
			
			
			
			if( number % 2 == 0 && start == 1) {
				//System.out.println("parr" + number);
				Component picture = panelPrimer.getComponent(index);
				JLabel reallyPicture = (JLabel)picture;
				reallyPicture.setBackground(color);
					}
			//System.out.println("prueba de abajo" + number);
			
		}
	  }
	}
	/**
	 * pinta las casillas pares o impares según se indique
	 * @param type
	 */
	public void paintBoxesODD_EVEN(int type) {
		if (type== 0) {
			operationAnalytics(0, colorPrimerBox);
			archivoAbierto = true;
			play.setEnabled(false);
			dice.setEnabled(false);
			paintAll.setEnabled(true);
//			preparedBoxes(poobStairs.getBoxes(), false);
//			paintPieces(positionPlayer_1, colorPrimero,1 );
//			paintPieces(positionPlayer_2, colorSegundo,2 );
		}
		if (type == 1) {
			operationAnalytics(1, colorSegundoBox);
			archivoAbierto = true;
			play.setEnabled(false);
			dice.setEnabled(false);
			paintAll.setEnabled(true);
//			preparedBoxes(poobStairs.getBoxes(), false);
//			paintPieces(positionPlayer_1, colorPrimero,1 );
//			paintPieces(positionPlayer_2, colorSegundo,2 );
				
		}
		
	}
	/**
	 * Crea un nuevo PoobStairsGUI
	 */
	public void nuevo() {
		dispose();
		String [] nuevo = {"este si ejjeej"};
		this.main(nuevo);
	}
	
	
	
	/**
	 * Crea todos los oyentes de botones, cajas, etc..
	 */
	public void prepareActions() {
		colorBoxesOdd.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                //nuevo();
            	colorPrimerBox = JColorChooser.showDialog(null, "Select color for seconds Boxes", Color.WHITE);
            	
            	paintBoxesODD_EVEN(0);
            	preparedBoxes(poobStairs.getBoxes(), false);
            	paintPieces(positionPlayer_1, colorPrimero, 1);
            	paintPieces(positionPlayer_2, colorSegundo, 2);
            	
            	
            }
        });
		colorBoxesEven.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
            	colorSegundoBox = JColorChooser.showDialog(null, "Select color for firts Boxes", Color.WHITE);
            	
            	paintBoxesODD_EVEN(1);
            	preparedBoxes(poobStairs.getBoxes(), false);
            	paintPieces(positionPlayer_1, colorPrimero, 1);
            	paintPieces(positionPlayer_2, colorSegundo, 2);
            	
            }
        });
		
		
		
		  // Agregar oyentes de eventos a las opciones del menú
        nuevoItem.addActionListener(new ActionListener() {
        
            public void actionPerformed(ActionEvent e) {
                nuevo();
            	
            }
        });
        
        abrirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(fileChooser);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Archivo seleccionado: " + filePath);
                    try {
                    	
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(selectedFile));
                        String s = (String) in.readObject();
                        transformBox= (Boolean)in.readObject();
                        System.out.println(transformBox+ " primera pureba");
                        poobStairs = (PoobStairs) in.readObject();
                        positionPlayer_1 = (int[])in.readObject();
                        positionPlayer_2 = (int[])in.readObject();
                        namePlayer1 = (String)in.readObject();
                        namePlayer2 = (String)in.readObject();
                        machineActive = (Boolean)in.readObject();
                        
                        System.out.println(transformBox+ "  pureba");
                        in.close();
                        cardLayout.show(mainPanel, "boardGeneral");
                        
                        paintLater();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } 
                
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("Operacion cancelada");
                }
            }
        });
        
        
        salvarASItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showSaveDialog(null);
                //if (seleccion == JFileChooser.APPROVE_OPTION) {
                    try {
                    	
                        File archivo = fileChooser.getSelectedFile();
                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo+".jffmv"));
                        out.writeObject("escaleras y serpientes");
                        out.writeObject(transformBox);
                        //System.out.println(transformBox+ " primera pureba");
                        out.writeObject(poobStairs);
                        out.writeObject(positionPlayer_1);
                        out.writeObject(positionPlayer_2);
                        out.writeObject(namePlayer1);
                        out.writeObject(namePlayer2);
                        out.writeObject(machineActive);
                        out.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                //}
            }
        });
        
        salirItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
 
		
		selectColorPrim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	colorPrimero = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	while(colorPrimero == null) {
            		colorPrimero = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	}
            	selectColorPrim.setBackground(colorPrimero);
            	
            }
        });
		
		selectColorPrim_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	colorPrimero = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	
            	while(colorPrimero == null) {
            		colorPrimero = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	}
            	selectColorPrim_1.setBackground(colorPrimero);
            	
            }
        });
		
		selectColorSeg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	colorSegundo = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	while(colorSegundo == null) {
            		colorSegundo = JColorChooser.showDialog(null, "Seleccionar color", Color.WHITE);
            	}
            	selectColorSeg.setBackground(colorSegundo);
            }
        });

		machine.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                cardLayout.show(mainPanel, "panel4");
	            }
	        });
		 
		
		 players.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                cardLayout.show(mainPanel, "panel2");
	            }
	        });
		 
		 
		 
		 
		 Color originalColor = play.getBackground(); //
		 play.addMouseListener(new MouseAdapter() {
			
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	play.setBackground(originalColor); // Establecemos el color original al soltar el botón
			    	
			    }
			});
		
		 play.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                cardLayout.show(mainPanel, "panel3");
	                
	            }
	        });
		 
		 
		 back.addMouseListener(new MouseAdapter() {
			

			    @Override
			    public void mouseReleased(MouseEvent e) {
			        play.setBackground(originalColor); // Restablecer el color original después de soltar el botón
			    }
			});

			back.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			        cardLayout.show(mainPanel, "panel1");
			    }
			});
		 
		 
		 back1.addMouseListener(new MouseAdapter() {
			  
			    
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	play.setBackground(originalColor); // Establecemos el color original al soltar el botón
			    }
			});
		
		 back1.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	                cardLayout.show(mainPanel, "panel1");
	            }
	        });
			
		
		 
		
		 playPlayer.addMouseListener(new MouseAdapter() {
	  
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	play.setBackground(originalColor); // Establecemos el color original al soltar el botón
			    }
			});
		
		 playPlayer.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	machineActive = true;
	            	transformBox = (Boolean)transform_1.getSelectedItem();
	            	typeMachine = ((String)selectMachine.getSelectedItem()).equals("Aprentice") ? 1:2;
	            	
	            	nRows = ((Number) rows_1.getValue()).intValue();
	            	nColumns= ((Number) columns_1.getValue()).intValue();
	            	nSnakes = ((Number) snakes_1.getValue()).intValue();
		 			nStairs = ((Number) stairs_1.getValue()).intValue();
		 			persentageSpecialBox = ((Number) percentBoxesEspecial_1.getValue()).intValue();
		 			persentageModifiers = ((Number) percentModifiers_1.getValue()).intValue();
		 			namePlayer1=name_1.getText();	   
		 			colorPlayer1 = colorPrimero.toString();
		 			
		 			
		 			try {
						poobStairs = new PoobStairs(nRows, nColumns, nSnakes, nStairs, 
								persentageSpecialBox, namePlayer1, colorPrimero,
								persentageModifiers,transformBox, typeMachine);
						namePlayer2 = poobStairs.getNameMachine();
						colorSegundo = poobStairs.getColorPlayer2();
						
		 			} catch (PoobStairsException e1) {
						JOptionPane.showMessageDialog(rootPane, e1.getMessage());
					}
		 			updateBoardLaterData(nRows,nColumns);
		 			
		 			salvarASItem.setEnabled(true);
	                cardLayout.show(mainPanel, "boardGeneral");
	                flag = true;
	                
	            	
//	            	JOptionPane.showMessageDialog(null, "This functionality is being build");
	                //cardLayout.show(mainPanel, "boardGeneral");
	            }
	        });
		 
		 playPlayers.addMouseListener(new MouseAdapter() {
			    
			    @Override
			    public void mouseReleased(MouseEvent e) {
			    	
			    	play.setBackground(originalColor); // Establecemos el color original al soltar el botón
			    }
			});
		
		 playPlayers.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	transformBox = (Boolean)transform.getSelectedItem();
	            	
	            	nRows = ((Number) rows.getValue()).intValue();
	            	nColumns= ((Number) columns.getValue()).intValue();
	            	nSnakes = ((Number) snakes.getValue()).intValue();
		 			nStairs = ((Number) stairs.getValue()).intValue();
		 			persentageSpecialBox = ((Number) percentBoxesEspecial.getValue()).intValue();
		 			persentageModifiers = ((Number) percentModifiers.getValue()).intValue();
		 			namePlayer1=name.getText();	     
		 			namePlayer2= name2.getText();
		 			colorPlayer1 = colorPrimero.toString();
		 			colorPlayer2 = colorSegundo.toString();
		 			
		 			try {
						poobStairs = new PoobStairs(nRows, nColumns, nSnakes, nStairs, persentageSpecialBox, namePlayer1, namePlayer2, colorPrimero, colorSegundo,persentageModifiers,transformBox);
					} catch (PoobStairsException e1) {
						JOptionPane.showMessageDialog(rootPane, e1.getMessage());
					}
		 			updateBoardLaterData(nRows,nColumns);
		 			
		 			salvarASItem.setEnabled(true);
	                cardLayout.show(mainPanel, "boardGeneral");
	                flag = true;
	                
	                
	            }
	        });
		 
		 paintAll.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	otherPlayer = false;
	            	primeraVez = true;
	            	player = false;
	            	if (archivoAbierto) {
	            		preparedBoxes(poobStairs.getBoxes(), true);
	            		paintPieces(positionPlayer_1, colorPrimero, 1);
	            	    paintPieces(positionPlayer_2, colorSegundo, 2);
	            		//putHeader(null, null);
	            	    playDice.setEnabled(true);
		            	dice.setEnabled(true);
		            	colorBoxesEven.setEnabled(true);
		                colorBoxesOdd.setEnabled(true);
		                paintAll.setEnabled(false);
		                flag = true;
		               
	            	}
	            	
	            	if (flag && !archivoAbierto) {
	            	
	            	preparedBoxes(poobStairs.getBoxes(), true);
	            	putWin();
	            	flag = true;
		 			putFirtsPices();
	            	playDice.setEnabled(true);
	            	dice.setEnabled(true);
	                
	                
	                colorBoxesEven.setEnabled(true);
	                colorBoxesOdd.setEnabled(true);
	                
	                
	                paintAll.setEnabled(false);
	                
	               
	            	}
	                
	            }
	        });
		 
		 this.addComponentListener(new ComponentAdapter() {
	            public void componentResized(ComponentEvent evt) {
	            	putWin();
	            	preparedBoxes(poobStairs.getBoxes(), true);
	            	
	            	
	            }
	        });
		 
		 JFrame myFrame = this;

		 myFrame.addComponentListener(new ComponentAdapter() {
		     public void componentResized(ComponentEvent e) {
		    	 putWin();
		    	 preparedBoxes(poobStairs.getBoxes(), true);
		    	 
		    	 
		     }
		 });
		
			
	}
	/**
	 * Es el main, el que ejecuta toda la GUI.
	 * @param args
	 */
	public static void main(String[] args) {
		PoobStairsGUI playStairs = new PoobStairsGUI();
		playStairs.setVisible(true);
	}

}