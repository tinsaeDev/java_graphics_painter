
			import java.awt.*;
			import java.awt.event.*;
			import java.awt.image.*;
			import javax.imageio.*;
			import javax.swing.*;
			import java.io.*;


			public class Paint extends Component implements ActionListener, MouseListener{
				
				JFrame window;
				JPanel superPanel;
				JPanel southPanel;
				
				JButton saveImageButton;
				
				BufferedImage img;
				File imageToSave;
				
				boolean userStillPressingMouse;
				PointerInfo mouseInfo;
				
				int preX;
				int preY;
				
				
				public Paint(){
					
					this.addMouseListener( this );
					
					img = new BufferedImage( 600,600,BufferedImage.TYPE_INT_RGB );
					
					window = new JFrame("Tinsae'e Painting Window");
					superPanel = new JPanel( new BorderLayout() );
					southPanel = new JPanel( new FlowLayout() );
					
					saveImageButton = new JButton("Save Image");
						saveImageButton.addActionListener( new ActionListener(){
							
							
							@Override
							public void actionPerformed(ActionEvent event){
								
								
								try{
									
									imageToSave = new File( JOptionPane.showInputDialog( window,"File Name") );
									
									ImageIO.write(img,"PNG",imageToSave);
									
								}
								
								catch(Exception e){
									
									JOptionPane.showMessageDialog( window,"File did not saved");
									
								}
								
							}
							
							
							
							
						} );
							
					
					southPanel.add(saveImageButton);
					superPanel.add( this );
					superPanel.add(southPanel,BorderLayout.SOUTH);
					
					window.add( superPanel );
					window.setSize( 600,600 );
					window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
					window.setVisible(true);
					
				}
				
				
				public static void main(String[] args){
					
							Paint p = new Paint();
							Timer t = new Timer(1,p);
								  t.start();
				}
				
				
				@Override				
				public void actionPerformed(ActionEvent event){
					
				
					Graphics2D gImg = (Graphics2D) img.getGraphics();
					
						if(userStillPressingMouse){	
								
								mouseInfo = MouseInfo.getPointerInfo();				
								int x = (int) window.getLocation().getX() + (int) mouseInfo.getLocation().getX();
								int y = (int) window.getLocation().getY() + (int) mouseInfo.getLocation().getY();
								
								gImg.setColor( Color.RED );
								
								gImg.setStroke( new BasicStroke(8) );
								 gImg.drawLine( preX,preY,x,y);
								window.setIconImage(img);
								 
								 preX=x;
								 preY=y;
								 
								//gImg.fillOval( x,y,x+3,y+3 );
								
								System.out.println( window.getLocation().getX() );
					
										}
										
					Graphics2D g = (Graphics2D) getGraphics();
					g.drawImage(img,0,0,null);
				
				}
			
				@Override
				public void mousePressed(MouseEvent event){
					
					userStillPressingMouse = true;
					
					
				}				
				
				@Override
				public void mouseReleased(MouseEvent event){
					
					
					userStillPressingMouse = false;
					
				}				
				
				
		// Empty methods overhead		
				
				@Override
				public void mouseClicked(MouseEvent event){}				
				
				@Override
				public void mouseEntered(MouseEvent event){}				
				
				@Override
				public void mouseExited(MouseEvent event){}

			
			
			
			}