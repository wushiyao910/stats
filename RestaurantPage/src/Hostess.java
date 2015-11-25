
public class Hostess {
	private Tables tables;
	
	public Hostess(int numTables) {
		this.tables = new Tables(numTables);
	}
	
	public Table seatCustomer(CustomerThread customerThread) {
		Table table = null;
		try {
			Restaurant.addMessage("Hostess is trying to seat customer " + customerThread.getCustomerNumber());
			/* Here, customer is about to wait for a table... add him to waiting label*/
			Restaurant.addCustomerToWaitingLabel(customerThread.getCustomerNumber());
			table = tables.getTable();
			
			WaiterThread waiter = Restaurant.getWaiterFactory().getWaiter();
			waiter.setTable(table);
			table.seatTable(customerThread, waiter);
			
			/* Here, customer is seated */
			Restaurant.addCustomerToSeatedLabel(customerThread.getCustomerNumber());
			Restaurant.addWaiterMessage("Customer " + customerThread.getCustomerNumber() + " is seated at table " + table.getTableNumber(), waiter.getWaiterNumber());
			Restaurant.addMessage("Hostess seated customer " + customerThread.getCustomerNumber() + " at table " + table.getTableNumber() + " with waiter " + waiter.getWaiterNumber());
		
			
		} catch (InterruptedException ie) {
			System.out.println("HostessThread.seatCustomer():InterruptedException: " + ie.getMessage());
		}
		return table;
	}
	
	public void customerLeaving(CustomerThread customerThread) {
		Restaurant.addCustomerToLeavingLabel(customerThread.getCustomerNumber());
		Restaurant.addWaiterMessage("Customer " + customerThread.getCustomerNumber() + " is done eating and is leaving.", customerThread.getTable().getWaiterThread().getWaiterNumber());
		Restaurant.addMessage("Customer " + customerThread.getCustomerNumber() + " is done eating and is leaving.");
		customerThread.getTable().getWaiterThread().returnTable(customerThread.getTable());
		
		
		Table table=customerThread.getTable();
		table.setSemaphore(Restaurant.getNumBusBoyNeeded());
		String busboyNumbers=null;
		Table tableWithMinPermits=table;
		int minNumPermits = 0;
		if (table.getSemaphore()==null)
		{
			System.out.println("!!!");
		}
		if (table.getSemaphore()!=null)
		{
			System.out.println("###");
			minNumPermits=table.getSemaphore().availablePermits();
			System.out.print(minNumPermits);

		}
		Table[] allTables=tables.getTables();
		while(true)
		{
			for (int i=0; i<tables.getTables().length; i++)
			{
				if (allTables[i].getSemaphore().availablePermits()<minNumPermits)
				{
					tableWithMinPermits=allTables[i];
				}
			}
			
			if (tableWithMinPermits.getTableNumber() == table.getTableNumber())
			{
				try {
					BusboyThread busboy=Restaurant.getBusboyFactory().getBusboy();
					busboy.cleanTable(table);
					busboyNumbers=busboyNumbers+busboy.getBusboyNumber();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (table.getSemaphore().availablePermits()==0)
				{
					Restaurant.addMessage("Busboys "+busboyNumbers+" are starting to clean");
					Restaurant.addMessage("Busboys cleaned the table"+table.getTableNumber());
					table.getSemaphore().release();
					//loop to release all semaphores
					//loop all the busboy to signal
					break;
				}

			}
		}
		
		tables.returnTable(customerThread.getTable());
	}

}