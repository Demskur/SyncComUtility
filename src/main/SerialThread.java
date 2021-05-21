/**
 * 
 */
package main;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import controlador.ControllerFx;
import controlador.ControllerFx.QueueEvent;
import controlador.ControllerFx.QueueEvent.QueueEventType;
import dll.SYNCCOM_Loader;
import util.EventQueue;

/**
 * @author fpinilla
 *
 */
public class SerialThread {
	Logger log = Logger.getLogger(this.getClass().getName());
	Timer timer;

	public SerialThread(String thrdname, long milliseconds, EventQueue<ControllerFx.QueueEvent> equeue) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				try {
					byte[] data = SYNCCOM_Loader.read();
					if (data != null) {
						System.out.println(new String(data));
						equeue.add(new QueueEvent(QueueEventType.READ, data, thrdname));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, 0, milliseconds);

	}

	public void stop() {
		if (timer != null)
			timer.cancel();
	}

}
