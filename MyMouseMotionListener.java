package Draw;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MyMouseMotionListener implements MouseMotionListener{
	draw db;
	public MyMouseMotionListener(draw d) {
		// TODO Auto-generated constructor stub
		db=d;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

		if(db.current!=null) {
			//当前处于移动图形的状态
//			db.current.getBounds().x=e.getX();
//			db.current.getBounds().y=e.getY();
//			
			db.startX=db.current.getBounds().x+(e.getX()-db.MovStartX);//移动的新的坐标
			db.startY=db.current.getBounds().y+(e.getY()-db.MovStartY);
			db.endX=db.startX+db.current.getBounds().width;
			db.endY=db.startY+db.current.getBounds().height;
			db.MovStartX=e.getX();
			db.MovStartY=e.getY();
			db.move();
		}else {
			db.endX=e.getX();
		    db.endY=e.getY();
		    db.repaint();
		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
