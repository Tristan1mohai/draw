package Draw;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.BorderUIResource;

public class draw extends JPanel{
	
	int startX,startY,endX,endY;//起点、终点坐标
    int  MovStartX,MovStartY;//移动图形时对鼠标的第一次点击的坐标进行记录
    String DrawStyle="直线";//LINE TRIAGNEL RECTANGLE 
    Color  color= Color.BLACK;//声明一个颜色对象 
    Shape current;
    Stroke stroke=new BasicStroke((float) 2.0);
    String str="请输入文字";
    
	ArrayList<Shape> shapes=new ArrayList<Shape>();
	ArrayList<grahpic> graphics=new ArrayList<grahpic>();
	HashMap<grahpic,Shape> map=new HashMap<grahpic,Shape>();

	public draw() {
		JFrame jf=new JFrame();
		jf.setVisible(true);

		jf.setSize(1000, 700);
		jf.setTitle("简单画图工具");
		jf.setDefaultCloseOperation(3);
		jf.setLocationRelativeTo(null);
		jf.setLayout(new BorderLayout());
		
		DrawListener dl=new DrawListener(this);
		

		
		
		jf.add(this,BorderLayout.CENTER);
		this.setBackground(Color.white);
		
		/*形状控制版面*/
		
		JPanel ShapePanel=new JPanel();
		ShapePanel.setBackground(Color.black);
		ShapePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		ShapePanel.setBackground(Color.GRAY);
		String[] shape= {"直线","矩形","圆","线条+","线条-","保存"};
		for(int i=0 ; i<shape.length;i++) {
			JButton button=new JButton(shape[i]);
			button.setBackground(Color.white);
			button.addActionListener(dl);
			ShapePanel.add(button);			
		}
		jf.add(ShapePanel, BorderLayout.NORTH);
		
		
		/*颜色控制版面*/
		
		JPanel ColorPanel=new JPanel();
		ColorPanel.setBackground(Color.BLACK);
		ColorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		ColorPanel.setBackground(Color.GRAY);
		Color[] color= {Color.black,Color.blue,Color.gray,Color.red,Color.CYAN,Color.magenta,Color.green,Color.darkGray,Color.pink };
		for(int i=0 ;i<color.length;i++) {
			JButton button=new JButton();
			button.addActionListener(dl);
			button.setPreferredSize(new Dimension(30, 30));
			button.setBackground(color[i]);
			ColorPanel.add(button);
		}
		
		jf.add(ColorPanel, BorderLayout.SOUTH);
		
		jf.setVisible(true);
		this.addMouseListener(new MyMouseListener(this));
		this.addMouseMotionListener(new MyMouseMotionListener(this));
		jf.addKeyListener(new MykeyLisener(this));
		this.addKeyListener(new Mypanl());
		
	}
	

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		
			Collection<grahpic> key=map.keySet();
			for(grahpic gc:key) {
				g.setColor(gc.c);
				Graphics2D g2d=(Graphics2D)g;
				g2d.setStroke(gc.sk);
				if(gc.shape.equals("直线")) {
					g.drawLine(gc.x1, gc.y1, gc.x2, gc.y2);
				}
				if(gc.shape.equals("矩形")) {
					g.drawRect(gc.x1, gc.y1, Math.abs(gc.x1-gc.x2), Math.abs(gc.y1-gc.y2));
				}
				if(gc.shape.equals("圆")) {
					g.drawOval(gc.x1, gc.y1, Math.abs(gc.x1-gc.x2), Math.abs(gc.y1-gc.y2));		
				}
			}

			g.setColor(color);
			if(DrawStyle.equals("直线")) {
				g.drawLine(startX, startY, endX, endY);
			}
			if(DrawStyle.equals("矩形")) {
				g.drawRect(startX, startY, Math.abs(startX-endX), Math.abs(startY-endY));
			}
			if(DrawStyle.equals("圆")) {
				g.drawOval(startX, startY, Math.abs(startX-endX), Math.abs(startY-endY));		
			}

		System.out.println(current);
	}
	
	public void add() {
		current=newshape();
		grahpic gc=new grahpic();
		map.put(gc, current);
		repaint();
	}
	
	public Shape find(Point2D p) {
		Iterator iter=map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry=(Map.Entry)iter.next();
			grahpic key=(grahpic)entry.getKey();
			Shape value=(Shape)entry.getValue();
			if(key.shape.equals("直线")) {
				if(p.getX()>Math.min(key.x1, key.x2)&&p.getX()<Math.max(key.x1, key.x2)) {
					if(p.getY()>Math.min(key.y1, key.y2)&&p.getY()<Math.max(key.y1, key.y2)) {
						return value;
					}
				}
			}
		}
		Collection<Shape> value=map.values();
		for(Shape r:value) {
			if(r.contains(p)) {
				return r;
			}
		}
		return null;
	}
	public void move() {
		if(current!=null) {
			Iterator iter=map.entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				grahpic key=(grahpic)entry.getKey();
				Shape value=(Shape)entry.getValue();
				if((current.getBounds().x==value.getBounds().x)&&(current.getBounds().y==value.getBounds().y)) {
					map.remove(key);
					break;
				}
			}
			grahpic gc=new grahpic();
	//		graphics.set(i, gc);
			DrawStyle=gc.shape;
			current=newshape();
			map.put(gc, current);
	//		shapes.set(i, current);
			repaint();
		}
		

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new draw();
	}
	
	public Shape newshape() {
		Shape temp =null;
		if(DrawStyle.equals("直线")) {
			temp=new Line2D.Double(startX, startY, endX, endY);
		}
		if(DrawStyle.equals("矩形")) {
			temp=new Rectangle2D.Double(startX, startY, Math.abs(startX-endX), Math.abs(startY-endY));
		}
		if(DrawStyle.equals("圆")) {
			Ellipse2D circle=new Ellipse2D.Double();
			circle.setFrame(startX, startY, Math.abs(startX-endX), Math.abs(startY-endY));		
			temp=circle;
		}
		return temp;
	}
	class grahpic {
		
		private Color c;
		private String shape;
		private int x1,x2,y1,y2;
		private Stroke sk;
		private String st;
		float i=(float) 2.0;
		
		
		public grahpic() {
			this.sk=stroke;
			this.c=color;
			this.shape=DrawStyle;
			this.x1=startX;
			this.x2=endX;
			this.y1=startY;
			this.y2=endY;
			this.st=str;
		}
	}
	public void addStroke() {
		// TODO Auto-generated method stub
		if(current!=null) {
			Iterator iter=map.entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				grahpic key=(grahpic)entry.getKey();
				Shape value=(Shape)entry.getValue();
				if((current.getBounds().x==value.getBounds().x)&&(current.getBounds().y==value.getBounds().y)) {
					key.sk=new BasicStroke(++(key.i));
					break;
				}
			}
		}
		repaint();
	}
	public void cutStroke() {
		if(current!=null) {
			Iterator iter=map.entrySet().iterator();
			while(iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				grahpic key=(grahpic)entry.getKey();
				Shape value=(Shape)entry.getValue();
				if((current.getBounds().x==value.getBounds().x)&&(current.getBounds().y==value.getBounds().y)) {
					key.sk=new BasicStroke(--(key.i));
					break;
				}
			}
		}
		repaint();
	}
		
}
