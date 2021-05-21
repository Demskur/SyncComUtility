/**
 * Nov 1, 2015 - Turbo7
 */
package util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Implements a pattern that enqueue records and allow retrieval in order.<br />
 * It has two direct effects:
 * <ol>
 * <li>Do not block the threads that report new records, and</li>
 * <li>The records are processed one by one.</li>
 * </ol>
 *
 * @author ayachan
 */
public class EventQueue<T> implements Runnable
{

  /**
   * The process of the records.
   *
   * @author ayachan
   */
  static public interface EventProcess<U>
  {
    void process(U event);
  }

  static final long DEFAULT_QUEUE_ms = 200;

  protected final EventProcess<T> process;
  protected final BlockingQueue<T> queue;
  protected final long timeout_ms;

  protected volatile Thread thisthrd;

  /**
   * Instance an event queue with the given process.<br />
   * The <i>process</i> callback will be called for each event in the queue.
   *
   * @param process The process to call for each event in the queue.
   */
	public EventQueue(EventProcess<T> process)
  {
    this(process, DEFAULT_QUEUE_ms);
  }

  /**
   * Instance an event queue with the given process.<br />
   * The <i>process</i> callback will be called for each event in the queue. The
   * queue will be inquired using the given timeout.
   *
   * @param process
   *          The process to call for each event in the queue.
   * @param timeout_ms
   *          The time out to use when polling the queue, not really matters.
   */
  public EventQueue(EventProcess<T> process, long timeout_ms)
  {
    this(null, process, timeout_ms);
  }

  /*
   * Added Sep 8, 2017 by AYA to provide some way to identify the thread
   * where this event queue is running.
   */

  public EventQueue(String thrdname, EventProcess<T> process)
  {
    this(thrdname, process, DEFAULT_QUEUE_ms);
  }
  public EventQueue(String thrdname, EventProcess<T> process, long timeout_ms)
  {
    this.process = process;
    this.timeout_ms = timeout_ms;
    this.queue = new LinkedBlockingQueue<T>();

    thisthrd = new Thread(this, thrdname != null ? thrdname : getClass().getName());
    thisthrd.setDaemon(true);
    thisthrd.start();
  }

  /**
   * Terminate this event queue.
   */
  public void terminate()
  {
    thisthrd = null;
  }

  /**
   * Add an event to the queue.<br />
   * The event will be reported to the declared process some time in the future.
   *
   * @param event The event to add.
   */
  public void add(T event)
  {
    queue.add(event);
  }

  /**
   * Clear the queue.<br />
   * <b>Can be called only from inside the queue process!</b>
   */
  public void clear()
  {
    queue.clear();
  }

  @Override
  public void run()
  {
    Thread thrdinst = Thread.currentThread();
    while ( (thrdinst == thisthrd) && (thrdinst != null))
    {
      try
      {
        T evt = queue.poll(timeout_ms, TimeUnit.MILLISECONDS);
        if (evt == null)
          continue;

        process.process(evt);
      }
      catch (InterruptedException e)
      {
        thrdinst = null;
      }
    }
  }
}
