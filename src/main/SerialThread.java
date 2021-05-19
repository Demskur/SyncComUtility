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
import util.FastcomUtil;
import util.EventQueue;

/**
 * @author fpinilla
 *
 */
public class SerialThread implements EventQueue.EventProcess<ControllerFx.QueueEvent> {
	Logger log = Logger.getLogger(this.getClass().getName());

	public SerialThread(String thrdname, long milliseconds) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				byte[] data = SYNCCOM_Loader.read();
				System.out.println(new String(data));
				if (data != null)
					process(new QueueEvent(QueueEventType.READ, data, thrdname));					
			}
		}, milliseconds);
	}

	@Override
	public void process(QueueEvent event) {
	}

}
