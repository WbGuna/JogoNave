package modelos;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener {

	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private List<Tiro> tiros;
	private boolean isVisivel, isTurbo;
	private Timer timer;
	
	public Player() {
		this.x = 100;
		this.y = 100;
		isVisivel = true;
		isTurbo = false;
		tiros = new ArrayList<Tiro>();
		
		timer = new Timer(5000, this);
		timer.start();
	}
	
	public boolean isTurbo() {
		return isTurbo;
	}

	public void setTurbo(boolean isTurbo) {
		this.isTurbo = isTurbo;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void load() {
		ImageIcon referencia = new ImageIcon("res/spaceship2.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(isTurbo == true) {			
			turbo();
			isTurbo = false;
		}
		
		if(isTurbo == false) {
			load();
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura,altura);
	}
	
	public void update() {

		x += dx;
		y += dy;

		if (this.x < 6) {
			x = 6;
		}

		if (this.x > 938) {
			x = 938;
		}

		if (this.y < 65) {
			y = 65;
		}
		if (this.y > 694) {
			y = 694;
		}

	}
	
	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_Z) {
			turbo();
		}
		
		if (codigo == KeyEvent.VK_A) {
			if(isTurbo == false) {
				tirosSimples();				
			}
		}

		if (codigo == KeyEvent.VK_UP) {
			dy = -3;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 3;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = -3;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 3;
		}

	}
	
	public void keyRelease(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();

		if (codigo == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_DOWN) {
			dy = 0;
		}

		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}
	
	public void tirosSimples() {
		this.tiros.add(new Tiro(x + largura, y + altura / 2));

	}
	
	public void turbo() {
		isTurbo = true;
		ImageIcon referencia = new ImageIcon("res/naveturbo.png");
		imagem = referencia.getImage();
	}


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public List<Tiro> getTiros() {
		return tiros;
	}

	public void setTiros(List<Tiro> tiros) {
		this.tiros = tiros;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	
	
	
}
