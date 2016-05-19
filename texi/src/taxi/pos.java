package taxi;

/**
 * Created by DESTR on 2016/5/19.
 */
public class pos
{
	private int x;
	private int y;
	private int z;

	public pos(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public int get_x()
	{
		return x;
	}
	public int get_z()
	{
		return z;
	}
	public int get_y()
	{
		return y;
	}
}
