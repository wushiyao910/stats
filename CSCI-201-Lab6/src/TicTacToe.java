import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
			
public class TicTacToe extends JFrame{

	private JLabel playerLabel;
	private JButton restartBtn;
	int playerCounter;
	String result;
	
	
	public TicTacToe()
	{
		super("Lab6");
		setSize(500,500);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		playerCounter=0;
		int grid[]=new int [9];
		result="Current Player: 1";
		for (int i=0; i<9; i++)
		{
			grid[i]=0;
		}
		
		
		JButton button [] = new JButton [9];
		playerLabel=new JLabel (result);
		restartBtn=new JButton ("Restart Game");
		JPanel mainPage = new JPanel(new BorderLayout());
		JPanel gridPage = new JPanel();
		JPanel topPage=new JPanel(new BorderLayout());

		gridPage.setLayout(new GridLayout(3,3));
		
		class restart implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				new TicTacToe();
			}
			
		}
		
		class onClick implements ActionListener
		{
			int i;
			public onClick(int n)
			{
				i=n;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				playerCounter++;
				if (playerCounter%2==1)
				{
					button[i].setEnabled(false);
					grid[i]=1;
					button[i].setText("x");
				}
				else
				{
					button[i].setEnabled(false);
					grid[i]=2;
					button[i].setText("o");
				}
				
				if (grid[0]==1 && grid[1]==1 && grid[2]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[3]==1 && grid[4]==1 && grid[5]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}

				}
				else if (grid[6]==1 && grid[7]==1 && grid[8]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}

				}
				else if (grid[0]==1 && grid[3]==1 && grid[6]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[1]==1 && grid[4]==1 && grid[7]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[2]==1 && grid[5]==1 && grid[8]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[0]==1 && grid[4]==1 && grid[8]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[2]==1 && grid[4]==1 && grid[6]==1)
				{
					result="Player One Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[0]==2 && grid[1]==2 && grid[2]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[3]==2 && grid[4]==2 && grid[5]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[6]==2 && grid[7]==2 && grid[8]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[0]==2 && grid[3]==2 && grid[6]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[1]==2 && grid[4]==2 && grid[7]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[2]==2 && grid[5]==2 && grid[8]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[0]==2 && grid[4]==2 && grid[8]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}
				else if (grid[2]==2 && grid[4]==2 && grid[6]==2)
				{
					result="Player Two Won";
					playerLabel.setText(result);
					for (int j=0; j<9; j++)
					{
						button[j].setEnabled(false);
					}
				}				
				else
				{
					if (button[0].isEnabled()==false && button[1].isEnabled()==false
							&& button[2].isEnabled()==false && button[3].isEnabled()==false
							&& button[4].isEnabled()==false && button[5].isEnabled()==false
							&& button[6].isEnabled()==false && button[7].isEnabled()==false
							&& button[8].isEnabled()==false)
					{
						result="Tie";
						playerLabel.setText(result);
					}
					else
					{
						if (playerCounter%2==1)
						{
							result="Current Player: 2";
							playerLabel.setText(result);
						}
						else
						{
							result="Current Player: 1";
							playerLabel.setText(result);
						}
					}
				}
			}
			
		}
		
		for (int i=0; i<9; i++)
		{
			button[i]=new JButton();
			button[i].addActionListener(new onClick(i));
			gridPage.add(button[i]);
		}
		
		
		restartBtn.addActionListener(new restart());
		mainPage.add(restartBtn,BorderLayout.SOUTH);
		topPage.add(playerLabel, BorderLayout.CENTER);
		mainPage.add(gridPage, BorderLayout.CENTER);
		mainPage.add(topPage, BorderLayout.NORTH);
		add(mainPage);
		
		setVisible(true);
	}
	
	
	
	public static void main(String[]args)
	{
		TicTacToe TTT=new TicTacToe();
	}
}
