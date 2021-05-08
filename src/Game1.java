import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class Game1 {
	
	private String selectedLanguage;

	private ImageIcon icon = new ImageIcon("src\\settingsIcon.png");

	private Container container;
	private JLabel lbnLbl, pcktLbl;
	private int leben = 100;
	private JLabel introLabel;
	private JLabel lebenLabel, iconLabel;
	private JButton introButton, continueButton, choice1, choice2, choice3, choice4;
	private JTextArea textArea, pocketText;

	private Font font = new Font("Cambria", Font.PLAIN, 28);
	private Font fontC = new Font("Cambria", Font.PLAIN, 20);
	private String position;
	private ActionHandler buttonAction = new ActionHandler();

	private boolean strangeKeyFirstTime = true;
	private boolean basementDoorFirstTime = false;
	private boolean basementDoorFirstUnlocked = false;
	private boolean brokenWindowSeenStones = false;
	private boolean windowIsBroken = false;
	private boolean windowIsOpen = false;
	private boolean lookingForLight = false;

	public static void main(String[] args) {
		new Game1();
	}

	// Introduction page 1#
	public Game1() {
		JFrame window = new JFrame();
		window.setSize(800, 600);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		window.getContentPane().setBackground(Color.black);
		window.getContentPane().setLayout(null);
		window.setResizable(false);
		container = window.getContentPane();

		introLabel = new JLabel("Horror House");
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);
		introLabel.setFont(new Font("Cambria", Font.PLAIN, 90));
		introLabel.setForeground(Color.red);
		introLabel.setBounds(93, 150, 600, 100);

		introButton = new JButton("Start");
		introButton.setFont(font);
		introButton.setForeground(Color.white);
		introButton.setBackground(Color.black);
		introButton.setBounds(343, 350, 100, 50);
		introButton.setFocusPainted(false);
		introButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				secondPage();
			}
		});
		container.add(introLabel);
		container.add(introButton);

		iconLabel = new JLabel("");
		iconLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setLanguage();
			}
		});
		iconLabel.setBackground(Color.WHITE);
		iconLabel.setBounds(23, 23, 63, 72);
		iconLabel.setIcon(icon);
		container.add(iconLabel);
		iconLabel.repaint();

	}

	// Language settings
	public void setLanguage() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 1));
		JRadioButton rB_En = new JRadioButton("English");
		rB_En.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedLanguage = "English";
				System.out.println(selectedLanguage);
			}
		});
		JRadioButton rB_De = new JRadioButton("Deutsch");
		rB_De.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedLanguage = "Deutsch";
				System.out.println(selectedLanguage);
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(rB_En);
		group.add(rB_De);

		JLabel msg = new JLabel("Select language\n");
		JLabel leer = new JLabel(" ");
		msg.setFont(fontC);
		panel.add(msg);
		panel.add(leer);
		panel.add(rB_En);
		panel.add(rB_De);
		JOptionPane.showMessageDialog(null, panel, "Language settings", JOptionPane.PLAIN_MESSAGE);

	}

	// Introduction page 2#
	public void secondPage() {
		introLabel.setVisible(false);
		introButton.setVisible(false);
		iconLabel.setVisible(false);

		textArea = new JTextArea("Once, there was a house, a hunted house. No one could even get close to it. "
				+ "But one day our brave hero, was dumb enough to get in. But he will need your help...");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setVisible(true);
		textArea.setFont(font);
		textArea.setBounds(100, 150, 600, 200);
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);

		continueButton = new JButton("Continue");
		continueButton.setFont(font);
		continueButton.setBackground(Color.black);
		continueButton.setForeground(Color.white);
		continueButton.setBounds(300, 350, 200, 50);
		continueButton.setVisible(true);
		continueButton.setFocusPainted(false);
		continueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setOptions();
				fenceDoor();

			}
		});
		container.add(continueButton);
		container.add(textArea);

	}

	// Player info
	public void spielerInfo() {

		textArea.setVisible(false);

		lbnLbl = new JLabel("HP:  ");
		lbnLbl.setBounds(100, 10, 60, 50);
		lbnLbl.setVisible(true);
		lbnLbl.setBackground(Color.black);
		lbnLbl.setForeground(Color.white);
		lbnLbl.setFont(font);
		container.add(lbnLbl);

		lebenLabel = new JLabel(leben + "");
		lebenLabel.setFont(font);
		lebenLabel.setBounds(180, 10, 100, 50);
		lebenLabel.setForeground(Color.white);
		lebenLabel.setVisible(true);
		container.add(lebenLabel);

		pcktLbl = new JLabel("Pocket:  ");
		pcktLbl.setBounds(350, 10, 130, 50);
		pcktLbl.setVisible(true);
		pcktLbl.setForeground(Color.white);
		pcktLbl.setFont(font);
		container.add(pcktLbl);

		pocketText = new JTextArea("empty");
		pocketText.setBounds(460, 17, 280, 80);
		pocketText.setEditable(false);
		pocketText.setFont(font);
		pocketText.setLineWrap(true);
		pocketText.setWrapStyleWord(true);
		pocketText.setBackground(Color.black);
		pocketText.setForeground(Color.white);
		container.add(pocketText);

		container.repaint();

	}

	// Multiple choice
	public void setOptions() {
		spielerInfo();
		textArea.setBounds(50, 95, 700, 235);
		textArea.setVisible(true);
		continueButton.setVisible(false);

		JPanel outputPanel = new JPanel();
		outputPanel.setBounds(225, 350, 300, 200);
		outputPanel.setBackground(Color.black);
		choice1 = new JButton("ch1");
		choice1.setFont(fontC);
		choice1.setBackground(Color.black);
		choice1.setForeground(Color.red);
		choice1.setFocusPainted(false);
		choice2 = new JButton("ch2");
		choice2.setFont(fontC);
		choice2.setBackground(Color.black);
		choice2.setForeground(Color.red);
		choice2.setFocusPainted(false);
		choice3 = new JButton("ch3");
		choice3.setFont(fontC);
		choice3.setBackground(Color.black);
		choice3.setForeground(Color.red);
		choice3.setFocusPainted(false);
		choice4 = new JButton("ch4");
		choice4.setFont(fontC);
		choice4.setBackground(Color.black);
		choice4.setForeground(Color.red);
		choice4.setFocusPainted(false);
		outputPanel.add(choice1);
		outputPanel.add(choice2);
		outputPanel.add(choice3);
		outputPanel.add(choice4);
		outputPanel.setLayout(new GridLayout(4, 1));
		container.add(outputPanel);

		choice1.addActionListener(buttonAction);
		choice1.setActionCommand("c1");
		choice2.addActionListener(buttonAction);
		choice2.setActionCommand("c2");
		choice3.addActionListener(buttonAction);
		choice3.setActionCommand("c3");
		choice4.addActionListener(buttonAction);
		choice4.setActionCommand("c4");

	}

	// Places
	public void fenceDoor() {

		position = "Fence door";
		textArea.setText("Our hero is in the in front of the fence door. There are a couple ways to get in the house: "
				+ "main entrance, garden door and the basement door. Which one should he choose?");
		choice1.setText("MAIN ENTRANCE");
		choice2.setText("GARDEN DOOR");
		choice3.setText("BASEMENT DOOR");
		choice4.setText("...");

	}

	public void mainEntrance() {
		position = "Main entrance";
		textArea.setText(
				"He goes through the wicked way to the main door ignoring all the \"STAY AWAY!!\" sings in both sides. As he climbs the steps on to the veranda, he sees the main door with a big rusted lock on it. Our hero is determined to get inside but he can't get the lock opened.");
		choice1.setText("TRY TO LOOSEN THE LOCK");
		choice2.setText("LOOK UNDER THE DOORMAT");
		if (pocketText.getText().contains("Strange key")) {
			choice3.setText("TRY THE STRANGE KEY");
		} else {
			choice3.setText("...");
		}
		choice4.setText("GO BACK ");

	}

	public void gardenDoor() {
		position = "Garden door";
		textArea.setText(
				"Our hero walkes around the house and finally finds the garden door. However, the door is blocked. It seems impossible to get inside through this door. But next to the door he sees a window.");
		choice1.setText("TO THE WINDOW");
		if (!brokenWindowSeenStones) {
			choice2.setText("...");
		} else {
			choice2.setText("PICK A STONE");
		}
		choice3.setText("...");
		choice4.setText("GO BACK");
	}

	public void basementDoor() {
		position = "Basement door";
		choice1.setText("...");
		choice2.setText("...");
		choice3.setText("...");
		choice4.setText("GO BACK");
		textArea.setText(
				"After a while, our hero finds a door that could be the basement door. He notices that this door has also a big lock on it, but it doesn't look that old and rusty.");
		if(basementDoorFirstTime) {
			textArea.setText(textArea.getText() + " Our hero thinks that the strange key he found might pass this lock. ");
		}
		if (pocketText.getText().contains("Strange key")) {
			textArea.setText(
					textArea.getText() + " He gets such a feeling that the key would pass just right. Should he try it?");
			choice1.setText("TRY THE STRANGE KEY");
		}
		if(basementDoorFirstUnlocked) {
			textArea.setText("The key passes smoothly through the hole and with a click unlocks our hero the door easily. As the door wide opens, our hero tries to recognize the things inside, but the darkness doesn't let him to do that. Only thing he can identify is a really disgusting and almost unbearable smell.  ");
			choice2.setText("GO INSIDE");
		}
		
		basementDoorFirstTime = true;

	}

	public void brokenWindow() {
		position = "Broken window";
		textArea.setText(
				"As our hero nears himself to the window, he sees that it is broken. But to reach the window handle he has to break the window a little bit more. He'll need some hard, solid object for this. Our genius hero remembers seeing many stones in front of the garden door.");
		brokenWindowSeenStones = true;
		choice1.setText("...");
		if (pocketText.getText().contains("stone") || windowIsOpen) {
			choice1.setText("BREAK THE WINDOW");
		}
		choice2.setText("...");
		if (windowIsBroken) {
			choice2.setText("OPEN THE WINDOW");
			textArea.setText(
					"Our hero throws a stone and suddenly he sees something moving really fast inside. But of course he ignores this. Now that the window is broken, he can reach to the window handle to open the window from inside. It still seems dangerous, though. He can cut his hand.");

		}

		choice3.setText("...");
		if (windowIsOpen) {
			textArea.setText("It looks like there is nothing anymore that could stop our hero getting inside.");
			choice3.setText("GO INSIDE");
			choice1.setText("...");
			choice2.setText("...");
		}
		choice4.setText("GO BACK");
	}

	public void kitchen() {
		position = "Kitchen";
		if (lookingForLight) {
			textArea.setText(
					"He is now back in the kitchen looking for a lamp or something that'll help him see where he is stepping.");
			choice4.setText("LOOK AROUND");
		} else {
			textArea.setText(
					"He is finally inside. After a couple moments, our hero starts recognizing objects around him and thinks that no one has set foot here for a long time, at least no human. As he gets away from the window, he notices a horrible smell.");
			choice4.setText("...");
		}
		choice1.setText("UPSTAIRS");
		choice2.setText("BASEMENT");
		choice3.setText("LIVING ROOM");
	}

	public void basementStairs() {
		position = "Basement stairs";
		textArea.setText(
				"As our hero nears himself to the basement door, that disgusting smell gets stronger and stronger with every step. For some reason, he starts thinking about that thing he saw while breaking the window and what it might be. Without minding his bleeding hand, he gathers all his courage and opens the door and looks in the darkness. He thinks he will need some light in that pitch dark basement.");
		lookingForLight = true;
		choice1.setText("GO TO BASEMENT ANYWAY");
		choice2.setText("LIVING ROOM");
		choice3.setText("UPSTAIRS");
		choice4.setText("KITCHEN");
	}
	
	public void basemen() {
		position = "Basement";
		if(pocketText.getText().contains("Matches")) {
			System.out.println("hola");
		}
		textArea.setText(
				"You are now in the basement");
		lookingForLight = true;
		choice1.setText("...");
		choice2.setText("...");
		choice3.setText("...");
		choice4.setText("...");
	}
	
	

	// Multiple choice functions
	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(position);
			System.out.println(e.getActionCommand());

			switch (position) {

			// Fence door scene
			case "Fence door":
				switch (e.getActionCommand()) {
				case "c1":
					mainEntrance();
					break;
				case "c2":
					gardenDoor();
					break;
				case "c3":
					basementDoor();
					break;
				}
				break;

			// Main entrance scene.
			case "Main entrance":
				switch (e.getActionCommand()) {
				case "c1":
					textArea.setText("The lock is extremely rusted, nothing happens.");
					break;
				case "c2":
					if (strangeKeyFirstTime) {

						textArea.setText(
								"He finds a shiny but strange formed key. It can be useful, but probably not on this old lock, he thinks.");
						addElementPocket("Strange key");
						choice3.setText("TRY THE STRANGE KEY");
						strangeKeyFirstTime = false;
					} else {
						textArea.setText("Here is nothing anymore.");
					}
					break;
				case "c3":
					if (choice3.getText().equals("TRY THE STRANGE KEY")) {
						textArea.setText(
								"Our hero tries the strange key but the key is now stuck and he can neither turn the key nor get it out again. He has to leave it there.");
						removeElementPocket("Strange key");
						choice3.setText("...");

					}
					break;
				case "c4":
					fenceDoor();
					break;
				}
				break;

			// Garden door scene
			case "Garden door":
				switch (e.getActionCommand()) {
				case "c1":
					brokenWindow();
					break;
				case "c2":
					if (brokenWindowSeenStones) {
						addElementPocket("stone");
					}
					break;
				case "c4":
					fenceDoor();
					break;
				}
				break;

			// Basement door scene

			case "Basement door":
				switch (e.getActionCommand()) {
				case "c1":
					if(choice1.getText().equals("TRY THE STRANGE KEY")) {
						
						textArea.setText("The key passes smoothly through the hole and with a click unlocks our hero the door easily. As the door wide opens, our hero tries to recognize the things inside, but the darkness doesn't let him to do that. Only thing he can identify is a really disgusting and almost unbearable smell.  ");
						removeElementPocket("Strange key");
						basementDoorFirstUnlocked = true;
						choice2.setText("GET INSIDE");
						
					}
					break;
				
				case "c2": 
					if(choice2.getText().equals("GET INSIDE")) {
						 basemen();
					}
					break;
					
				case "c4":
					fenceDoor();
					break;
				}
				break;

			// Broken window scene
			case "Broken window":
				switch (e.getActionCommand()) {
				case "c1":
					if (choice1.getText().equals("BREAK THE WINDOW")) {
						if (!windowIsBroken)
							removeElementPocket("stone");
						windowIsBroken = true;
						textArea.setText(
								"Our hero throws a stone and suddenly he sees something moving really fast inside. But of course he ignores this. Now that the window is broken, he can reach to the window handle to open the window from inside. It still seems dangerous, though. He can cut his hand.");
						choice2.setText("OPEN THE WINDOW");
						choice1.setText("...");
					}
					break;
				case "c2":
					if (choice2.getText().equals("OPEN THE WINDOW")) {
						if (!windowIsOpen) {
							manageLifePoints(-10);
						}
						textArea.setText(
								"To reach the handle, he puts his hand through the broken part of the window. Surprisingly, he cuts his hand. However, our brave hero doesn't even think of changing his mind.");
						windowIsOpen = true;
						choice3.setText("GO INSIDE");
						choice2.setText("...");
					}
					break;
				case "c3":
					if (choice3.getText().equals("GO INSIDE")) {
						kitchen();
					}
					break;

				case "c4":
					gardenDoor();
					break;
				}
				break;

			// Kitchen
			case "Kitchen":
				switch (e.getActionCommand()) {
				case "c2":
					basementStairs();
					break;
				case "c4":
					if (choice4.getText().equals("LOOK AROUND")) {
						textArea.setText("?Hola, qué tal?");
					}
					break;
				}
				break;

			// Basement stairs
			case "Basement stairs":
				switch (e.getActionCommand()) {
				case "c1": 
					basemen();
					break;
				case "c4":
					kitchen();
					break;

				}
				break;
			}
		}

	}

	// Managing the pocket logic
	public void addElementPocket(String elm) {
		if (!pocketText.getText().contains(elm)) {
			if (pocketText.getText().equals("empty")) {
				pocketText.setText(elm);
			} else {
				pocketText.setText(pocketText.getText() + ", " + elm);
			}
		}
	}

	public void removeElementPocket(String elm) {
		if (!pocketText.getText().contains(",")) {
			if (pocketText.getText().equals(elm)) {
				pocketText.setText("empty");
			}
		} else {
			if (pocketText.getText().contains(", " + elm)) {
				pocketText.setText(pocketText.getText().replace(", " + elm, ""));
			} else if (pocketText.getText().contains(elm + ", ")) {
				pocketText.setText(pocketText.getText().replace(elm + ", ", ""));
			}
		}
	}

	// Life points logic
	public void manageLifePoints(int life) {
		if (leben + life <= 100 && leben + life >= 0) {
			leben += life;
			lebenLabel.setText(leben + "");
		}
		if (leben <= 0) {
			System.out.println("You re dead, now. upps");
		}
	}

}
