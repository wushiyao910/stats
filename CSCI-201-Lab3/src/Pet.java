
public abstract class Pet implements Nameable {
	private String name;
	
	public void setName(String newName)
	{
		this.name=newName;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public abstract String speak();
}
