package Draw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener{
	draw dw;
	public MyMouseListener(draw d) {
		dw=d;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		dw.current=dw.find(e.getPoint());
		System.out.println(dw.current);
		if(dw.current==null) {
			dw.startX=e.getX();
			dw.startY=e.getY();
			System.out.println("press");
		}else {
			dw.MovStartX=e.getX();
			dw.MovStartY=e.getY();
			System.out.println("...");

		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(dw.current==null&&dw.startX!=e.getX()&&dw.startY!=e.getY()) {
			dw.endX=e.getX();
			dw.endY=e.getY();
			System.out.println("release");
			dw.add();
		}else {
		}
		
	}

}
