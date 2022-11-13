package container;

import javax.swing.JFrame;

import modelos.Fase;

public class Container extends JFrame  {

	private static final long serialVersionUID = 6434092271450406865L;

	public Container() {

		add(new Fase());
		setTitle("WB-Space");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Container();

	}
}
