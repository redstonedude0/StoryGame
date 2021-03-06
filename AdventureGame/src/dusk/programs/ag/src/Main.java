package dusk.programs.ag.src;

import javax.swing.SwingUtilities;

public class Main implements Runnable {
	
	public static Main m;
	public static Thread thread;
	
	public static void main(String[] args) {
		m = new Main();
	}
	
	public Main() {
		//Initialise other classes
		start();
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		while (true) {
			update();
		}
	}
	
	public static long nextTime = System.currentTimeMillis();
	public static int frames = 0;
	public static long lastTime = System.currentTimeMillis();
	
	public static void update() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					lastTime = System.currentTimeMillis();
					//GraphicsHandler.graphicsUpdate();
					frames++;
					if (System.currentTimeMillis() >= nextTime) {
						nextTime += 1000;
						//System.out.println(frames + " fps");
						frames = 0;
					}
					long currTime = System.currentTimeMillis();
					long elapsedTime = currTime - lastTime;
					if (elapsedTime >= 20) {
						elapsedTime = 20;// don't allow negative sleep times but aim for 50 FPS
					}
					Thread.sleep(20 - elapsedTime);
					update();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} // 50fps
			}
		});
	}

}
