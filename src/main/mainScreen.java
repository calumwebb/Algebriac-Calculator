package main;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.event.ItemEvent;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import backend.Pi;
import backend.QuadraticEquation;
import backend.Sort;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JScrollPane;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.JTextComponent;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")

public class mainScreen extends JFrame {
	
	private JPanel cards;
	private JTextField PiEnterField;
	private JTextField quad3TextField;
	private JTextField quad2TextField;
	private JTextField quad1TextField;
	
	// pi variables
	Pi pi = new Pi();
	int PiNth;
	BigDecimal Pi;
	BigDecimal PiTemp;

	
	// quadratic variables
	QuadraticEquation quad = new QuadraticEquation();
	Double qa, qb, qc;
	String quadRoot2, quadRoot1;
	
	// Sorting variables
	Sort sort = new Sort();
	String sortString;
	int[] numbers;


	
	public static void main(String[] args) throws FileNotFoundException {
		long startTime = System.currentTimeMillis();
		long elapsedTime;
		setOut();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainScreen frame = new mainScreen();
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println(elapsedTime + " millis to load the application");
	}
	
	
	public mainScreen() {
		setTitle("Algebriac Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 600);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("application closed");
				System.exit(0);
			}
		});
		mnFile.add(mntmClose);
		
		JMenu mnFunctions = new JMenu("Navigation");
		menuBar.add(mnFunctions);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "SPLASHSCREEN");
	            System.out.println("splashscreen selected");
			}
		});
		mnFunctions.add(mntmHome);
		
		JSeparator separator = new JSeparator();
		mnFunctions.add(separator);
		
		JMenuItem mntmPiNthDigit = new JMenuItem("Pi");
		mntmPiNthDigit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "PiPanel");
	            System.out.println("PiPanel selected");
			}
		});
		mnFunctions.add(mntmPiNthDigit);
		
		JMenuItem mntmQuadratic = new JMenuItem("Quadratic");
		mntmQuadratic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "QuadraticPanel");
	            System.out.println("QuadraticEquationPanel selected");
			}
		});
		mnFunctions.add(mntmQuadratic);
		
		JMenuItem mntmSorting = new JMenuItem("Sorting");
		mntmSorting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "SortingPanel");
	            System.out.println("SortingPanel selected");
			}
		});
		mnFunctions.add(mntmSorting);
		
		JSeparator separator_1 = new JSeparator();
		mnFunctions.add(separator_1);
		
		JMenuItem mntmConsole = new JMenuItem("Console");
		mntmConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "ConsolePanel");
	            System.out.println("ConsolePanel selected");
			}
		});
		mnFunctions.add(mntmConsole);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
	            cl.show(cards, "Info");
	            System.out.println("Info selected");
			}
		});
		mnHelp.add(mntmInfo);
		
		cards = new JPanel();
		cards.setBorder(null);
		setContentPane(cards);
		cards.setLayout(new CardLayout(0, 0));
		
		JPanel SPLASHSCREEN = new JPanel();
		cards.add(SPLASHSCREEN, "SPLASHSCREEN");
		SPLASHSCREEN.setLayout(null);
		
		JPanel Info = new JPanel();
		cards.add(Info, "Info");
		Info.setLayout(null);
				
		JLabel Information = new JLabel("");
		Information.setEnabled(false);
		Information.setToolTipText("");
		Information.setIcon(new ImageIcon(mainScreen.class.getResource("/resources/info.png")));
		Information.setBounds(6, 6, 888, 516);
		Info.add(Information);
		
		JLabel Logo = new JLabel("");
		Logo.setIcon(new ImageIcon(mainScreen.class.getResource("/resources/splashscreen.png")));
		Logo.setBounds(6, 38, 889, 462);
		SPLASHSCREEN.add(Logo);
		
		JPanel PiPanel = new JPanel();
		cards.add(PiPanel, "PiPanel");
		PiPanel.setLayout(null);
		final JScrollPane PiScrollPane = new JScrollPane();
		PiScrollPane.setBounds(6, 137, 888, 365);
		PiPanel.add(PiScrollPane);
		
		JButton btnGeneratePi = new JButton("GENERATE");
		btnGeneratePi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				long start;
				long finish;
				start = System.currentTimeMillis();
				
				PiNth = Integer.parseInt(PiEnterField.getText());
				final JTextArea PiTextArea = new JTextArea();
				PiTextArea.setFont(new Font("Myriad Pro", Font.PLAIN, 13));
				PiTextArea.setText(pi.piAlg(PiNth));
				PiTextArea.setLineWrap(true);
				PiScrollPane.setViewportView(PiTextArea);
				finish = (System.currentTimeMillis() - start);
				System.out.println(PiNth + " digits of Pi were calculated in " + finish + " milliseconds");
			}
		});
		

		btnGeneratePi.setBounds(735, 82, 117, 43);
		PiPanel.add(btnGeneratePi);
		
		PiEnterField = new JTextField();
		PiEnterField.setHorizontalAlignment(SwingConstants.CENTER);
		PiEnterField.setFont(new Font("Myriad Pro", Font.PLAIN, 31));
		PiEnterField.setBounds(408, 84, 300, 41);
		PiPanel.add(PiEnterField);
		PiEnterField.setColumns(10);
		
		
		JLabel PiTitle = new JLabel("");
		PiTitle.setIcon(new ImageIcon(mainScreen.class.getResource("/resources/piTitleBar.png")));
		PiTitle.setBounds(6, 6, 888, 287);
		PiPanel.add(PiTitle);
		
		JPanel QuadraticPanel = new JPanel();
		cards.add(QuadraticPanel, "QuadraticPanel");
		QuadraticPanel.setLayout(null);
		
		quad3TextField = new JTextField();
		quad3TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		quad3TextField.setFont(new Font("Myriad Pro", Font.PLAIN, 50));
		quad3TextField.setBounds(641, 163, 143, 69);
		QuadraticPanel.add(quad3TextField);
		quad3TextField.setColumns(10);
		
		quad2TextField = new JTextField();
		quad2TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		quad2TextField.setFont(new Font("Myriad Pro", Font.PLAIN, 50));
		quad2TextField.setColumns(10);
		quad2TextField.setBounds(386, 163, 143, 69);
		QuadraticPanel.add(quad2TextField);
		
		quad1TextField = new JTextField();
		quad1TextField.setHorizontalAlignment(SwingConstants.RIGHT);
		quad1TextField.setFont(new Font("Myriad Pro", Font.PLAIN, 50));
		quad1TextField.setColumns(10);
		quad1TextField.setBounds(118, 163, 143, 69);
		QuadraticPanel.add(quad1TextField);
		
	
		
		final JTextPane SortingscrollPane = new JTextPane();
		SortingscrollPane.setBounds(153, 294, 597, 219);
		QuadraticPanel.add(SortingscrollPane);
		SortingscrollPane.setText("\n");
		SortingscrollPane.setEditable(false);
		SortingscrollPane.setFont(new Font("Myriad Pro", Font.PLAIN, 19));
		JButton btnNewButton = new JButton("Solve");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					qc = Double.parseDouble(quad3TextField.getText());
					qb = Double.parseDouble(quad2TextField.getText());
					qa = Double.parseDouble(quad1TextField.getText());
					
					String equation = qa.intValue() + "x^2+" + qb.intValue() + "x+" + qc.intValue();
					quadRoot1 = QuadraticEquation.QuadPlus(qa, qb, qc);
					quadRoot2 = QuadraticEquation.QuadNeg(qa, qb, qc);
					
					if (quadRoot1 == "There are no real roots") {
						SortingscrollPane.setText("There are no real roots for the equation: " + equation );
						System.out.println(equation + " can not be solved, as there are no real roots in this equation");
					} else { 
						double discr = qb*qb - 4*qa*qc; 
						double sumRoot = -qb/qa;
						double proRoot = qc/qa;
					SortingscrollPane.setText("Roots: " + quadRoot1 + quadRoot2 + "\nDiscriminant: " + discr + "\nSum of Roots: " + sumRoot + "\nProduct of Roots: " + proRoot);
					System.out.println(equation + " was solved with the roots " + quadRoot1 + quadRoot2);
				}
			}
		});
		btnNewButton.setBounds(128, 253, 645, 29);
		QuadraticPanel.add(btnNewButton);
		
		JLabel QuadEquationTitle = new JLabel("");
		QuadEquationTitle.setFont(new Font("Myriad Pro", Font.PLAIN, 16));
		QuadEquationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		QuadEquationTitle.setIcon(new ImageIcon(mainScreen.class.getResource("/resources/QuadEquation1.png")));
		QuadEquationTitle.setBounds(0, 0, 900, 550);
		QuadraticPanel.add(QuadEquationTitle);
		
		JPanel SortingPanel = new JPanel();
		cards.add(SortingPanel, "SortingPanel");
		SortingPanel.setLayout(null);
		
		JComboBox SortComboBox = new JComboBox();
		SortComboBox.setModel(new DefaultComboBoxModel(new String[] {"Bubble Sort"}));
		SortComboBox.setBounds(98, 165, 216, 27);
		SortingPanel.add(SortComboBox);
		
		final JComboBox OrderComboBox = new JComboBox();
		OrderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Ascending", "Descending"}));
		OrderComboBox.setBounds(605, 165, 216, 27);
		SortingPanel.add(OrderComboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 247, 888, 303);
		SortingPanel.add(scrollPane);
		
		final JTextArea SortInputTextArea = new JTextArea();
		SortInputTextArea.setLineWrap(true);
		SortInputTextArea.setWrapStyleWord(true);
		scrollPane.setViewportView(SortInputTextArea);
		cards.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{SPLASHSCREEN, Info, Information, Logo, PiPanel}));
		
		JButton SortButton = new JButton("Sort List");
		SortButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		SortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String order = OrderComboBox.getSelectedItem().toString();
				sortString = SortInputTextArea.getText();
				if (order == "Ascending") {
					numbers = Sort.BubbleSortA(sortString);
						String temp = SortInputTextArea.getText();
						SortInputTextArea.setText("");
					for (int i = 0; i < numbers.length; i++ ) {
						SortInputTextArea.append(Integer.toString(numbers[i]) + " ");
					}
					System.out.println("Sorted " + numbers.length + " numbers using bubble sort in ascending order");
				} else {
					numbers = Sort.BubbleSortD(sortString);
					String temp = SortInputTextArea.getText();
					SortInputTextArea.setText("");
				for (int i = 0; i < numbers.length; i++ ) {
					SortInputTextArea.append(Integer.toString(numbers[i]) + " ");
				}
				System.out.println("Sorted " + numbers.length + " numbers using bubble sort in descending order");
				}
			}
		});
		SortButton.setBounds(673, 6, 189, 40);
		SortingPanel.add(SortButton);
		
		JButton btnRandomNumbers = new JButton("Random Numbers");
		btnRandomNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				SortInputTextArea.setText("");
				int randomNumbers = (int) (Math.random() * 1000);
				for (int i = 0; i < randomNumbers; i ++) {
					int random = (int) (Math.random() * 100000);
					SortInputTextArea.append(random + " ");	
				}
				System.out.println(randomNumbers + " random numbers where generated to sort");
			}
		});
		btnRandomNumbers.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnRandomNumbers.setBounds(673, 52, 189, 21);
		SortingPanel.add(btnRandomNumbers);
		
		JLabel SortingTitle = new JLabel("");
		SortingTitle.setIcon(new ImageIcon(mainScreen.class.getResource("/resources/SortingTitleHeader.png")));
		SortingTitle.setBounds(6, 6, 888, 243);
		SortingPanel.add(SortingTitle);
		
		JPanel ConsolePanel = new JPanel();
		cards.add(ConsolePanel, "ConsolePanel");
		ConsolePanel.setLayout(null);

		JScrollPane ConsoleScrollPane = new JScrollPane();
		ConsoleScrollPane.setBounds(6, 6, 888, 544);
		ConsolePanel.add(ConsoleScrollPane);
		
		JTextArea ConsoleTextArea = new JTextArea();
		ConsoleTextArea.setLineWrap(true);
		ConsoleTextArea.setFont(new Font("Myriad Pro", Font.PLAIN, 26));
		ConsoleScrollPane.setViewportView(ConsoleTextArea);
	}
	
	public static void setOut() throws FileNotFoundException {
		PrintStream console = System.out;

		File file = new File("src/main/console.txt");
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		System.setOut(ps);
	}
	
	public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) evt.getItem());
    }
}
