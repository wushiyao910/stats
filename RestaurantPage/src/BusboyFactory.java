import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class BusboyFactory {
	private Vector<BusboyThread> busboyThreadVector = new Vector<BusboyThread>();
	private int numBusboys;
	private int numBusboyNeeded;
	private Hostess hostess;
	private Lock lock=new ReentrantLock();
	private Condition busboyAvailable = lock.newCondition();
	
	public BusboyFactory(Hostess hostess, int numBusboys, int numBusboyNeeded)
	{
		this.hostess=hostess;
		this.numBusboys=numBusboys;
		this.numBusboyNeeded=numBusboyNeeded;
		for (int i=0; i<this.numBusboys; i++)
		{
			busboyThreadVector.add(new BusboyThread(hostess,i,numBusboyNeeded,this));
		}
	}
	
	public int getNumBusBoyNeeded()
	{
		return this.numBusboyNeeded;
	}
	
	public void returnBusboy(BusboyThread bbt)
	{
		lock.lock();
		bbt.finishTable(bbt.getTable());
		busboyAvailable.signal();
		lock.unlock();
	}
	
	public BusboyThread getBusboy()
	{
		BusboyThread bbt=null;
		try
		{
			lock.lock();
			while(bbt ==null)
			{
				int i;
				for (i=0; i<busboyThreadVector.size(); i++)
				{
					bbt=busboyThreadVector.get(i);
					if (bbt != null)
					{
						if (bbt.getTable() == null)
						{
							break;
						}
					}
				}
				if (i==busboyThreadVector.size())
				{
					bbt=null;
					busboyAvailable.await();
				}
			}
		}catch(InterruptedException ie)
		{
			
		}finally
		{
			lock.unlock();
		}
		return bbt;
	}

}


class BusboyThread extends Thread
{
	private Hostess hostess;
	private Table table;
	private int busboyNumber;
	private BusboyFactory busboyFactory;
	private Lock busboyLock = new ReentrantLock();
	private Condition tableAssignedCondition = busboyLock.newCondition();
	private int numBusboyNeeded;
	
	public BusboyThread(Hostess hostess, int busboyNumber,int numBusboyNeeded, BusboyFactory busboyFactory)
	{
		this.hostess=hostess;
		this.busboyNumber=busboyNumber;
		this.busboyFactory=busboyFactory;
		//this.table.setSemaphore(numBusboyNeeded);
		this.start();
	}
	
	
	public Hostess getHostess()
	{
		return this.hostess;
	}
	
	public void finishTable(Table table)
	{
		this.table=null;
		
	}
	
	public void cleanTable(Table table) throws InterruptedException
	{
		this.table=table;
		this.table.getSemaphore().acquire();
		this.busboyLock.lock();
		this.tableAssignedCondition.signalAll();
		this.busboyLock.unlock();
	}
	
	public Table getTable()
	{
		return this.table;
	}
	
	public int getBusboyNumber()
	{
		return this.busboyNumber;
	}
	
	public void run()
	{
		try
		{
			while(true)
			{
				this.busboyLock.lock();
				this.tableAssignedCondition.await();
				this.busboyLock.unlock();
				Thread.sleep(1000 * (int)(Math.random() * 10));
				
				if (getTable()!=null)
				{
					getTable().getLock().lock();
					getTable().getReadyCondition().signal();
					getTable().getLock().unlock();
				}
				
				busboyFactory.returnBusboy(this);
			}
		} catch(InterruptedException ie)
		{
			
		}
	}
	
}





