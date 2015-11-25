package shiyaowu_CSCI201_Assignment4;

public class Tile {
	private String type;
	private String rotation;
	
	public Tile(String t, String r)
	{
		this.type=t;
		this.rotation=r;
	}
	
	public String getType()
	{
		return this.type;
	}
	
	public String getRotation()
	{
		return this.rotation;
	}
}
