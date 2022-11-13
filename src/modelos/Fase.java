package modelos;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {

	private static final long serialVersionUID = -6269969359375221542L;
	private Image fundo;
	private Player player;
	private Timer timer;
	private List<Enemy1> enemy1;
	private boolean emJogo;
	private List<Stars> stars;
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);
		ImageIcon referencia = new ImageIcon("res/Blackground.png");
		fundo = referencia.getImage();
		player = new Player();
		player.load();
		
		addKeyListener(new TecladoAdapter());
		
		timer = new Timer(5, this);
		timer.start();
		
		inicializaInimigos();
		inicializaStars();
		emJogo = true;
	}
	
	public void inicializaInimigos() {
		int cordenadas [] = new int [100];
		enemy1 =  new ArrayList<Enemy1>();
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int)(Math.random() * 8000+800);
			int y = (int)(Math.random() * 600 + 30);
			enemy1.add(new Enemy1(x, y));
		}
	}
	
	public void inicializaStars() {
		int cordenadas [] = new int [200];
		stars =  new ArrayList<Stars>();
		for (int i = 0; i < cordenadas.length; i++) {
			int x = (int)(Math.random() * 1024 + 0);
			int y = (int)(Math.random() * 768 + 0);
			stars.add(new Stars(x, y));
		}
	}
	
	public void paint(Graphics g) {
		
		Graphics2D graficos = (Graphics2D) g;
		if(emJogo == true) {
			
			graficos.drawImage(fundo, 0, 0, null);
			
			for(int z = 0; z < stars.size(); z++) {
				Stars st = stars.get(z);
				st.load();
				graficos.drawImage(st.getImagem(), st.getX(), st.getY(), this);
			}
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			
			List<Tiro> tiros = player.getTiros();
			for(int i = 0; i < tiros.size(); i++) {
				Tiro m = tiros.get(i);
				m.load();
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
			
			for (int y = 0; y < enemy1.size(); y++) {
				Enemy1 in = enemy1.get(y);
				in.load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
		} else {
			ImageIcon fimJogo = new ImageIcon("res/fimdejogo.png");
			graficos.drawImage(fimJogo.getImage(), 0, 0, null);
		}		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		player.update();
		
		if(player.isTurbo()) {
			timer.setDelay(2);
		}
		
		if(player.isTurbo() == false) {
			timer.setDelay(5);
		}
		
		for (int w = 0; w < stars.size(); w++) {
			Stars st = stars.get(w);
			if(st.isVisivel()) {
				st.update();
			} else {
				stars.remove(w);
			}
		}
		List<Tiro> tiros = player.getTiros();
		for(int i = 0; i < tiros.size(); i++) {
			Tiro m = tiros.get(i);
			if(m.isVisivel()) {
				m.update();
				if(player.isTurbo()) {
					tiros.get(i).setVELOCIDADE(-1);
				}
				if(player.isTurbo() == false) {
					tiros.get(i).setVELOCIDADE(2);
				}
			} else {
				tiros.remove(i);
			}
		}
		
		for (int y = 0; y < enemy1.size(); y++) {
			Enemy1 in = enemy1.get(y);
			if(in.isVisivel()) {
				in.update();
			} else {
				enemy1.remove(y);
			}
		}
		checarColisoes();
		repaint();		
	}
	
	public void checarColisoes() {
		Rectangle fornave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaTiro;
		
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if(fornave.intersects(formaEnemy1)) {
				if(player.isTurbo() == true) {
					tempEnemy1.setVisivel(false);
				} else {
					
					player.setVisivel(false);
					tempEnemy1.setVisivel(false);
					emJogo = false;
				}
			}
		}
		
		List<Tiro> tiros = player.getTiros();
		for (int o = 0; o < tiros.size(); o++) {
			Tiro tempTiro = tiros.get(o);
			formaTiro = tempTiro.getBounds();
			for (int j = 0; j < enemy1.size(); j++) {
				Enemy1 tempEnemy1 = enemy1.get(j);
				formaEnemy1 = tempEnemy1.getBounds();
				if(formaTiro.intersects(formaEnemy1)) {
					tempEnemy1.setVisivel(false);
					tempTiro.setVisivel(false);
				}
			}
		}
	}
	
	private class TecladoAdapter extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			player.keyRelease(e);
		}
	}
	
	
	
}
