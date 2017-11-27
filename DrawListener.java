package Draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawListener implements ActionListener{
	

	draw dw;
	int x1,y1,x2,y2;
	Shape current;


	public DrawListener(draw d) {
		dw=d;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("")) {
			JButton button=(JButton)e.getSource();
			dw.color=button.getBackground();
			System.out.println("color="+dw.color);
		}else if(e.getActionCommand().equals("线条+")){
			if(dw.current!=null) {
				dw.addStroke();
			}
		}else if(e.getActionCommand().equals("线条-")){
			if(dw.current!=null) {
				dw.cutStroke();
				
			}	
		}else if(e.getActionCommand().equals("保存")){
			
		}
		else
		{
			JButton button=(JButton)e.getSource();
			dw.DrawStyle=button.getActionCommand();
			System.out.println("shape="+dw.DrawStyle);
		}
	}
	

	
	
	
}
