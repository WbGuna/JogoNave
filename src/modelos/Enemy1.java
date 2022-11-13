package modelos;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy1 {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

//	private static final int LARGURA = 938;
	private static int VELOCIDADE = 2;
	
	public Enemy1(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	
	public void load() {
		ImageIcon referencia = new ImageIcon("res/enemy1.png");
		Imagem = referencia.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);

	}
	
	public void update() {
		this.x -= VELOCIDADE;
//		if (this.x > LARGURA) {
//			isVisivel = false;
//		}

	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura,altura);
	}

	public Image getImagem() {
		return Imagem;
	}

	public void setImagem(Image imagem) {
		Imagem = imagem;
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

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}


}
