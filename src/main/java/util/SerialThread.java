/**
 * 
 */
package main.java.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import main.java.controlador.ControllerFx;
import main.java.controlador.ControllerFx.QueueEvent;
import main.java.controlador.ControllerFx.QueueEvent.QueueEventType;
import main.java.synccom.SYNCCOM_Loader;

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
