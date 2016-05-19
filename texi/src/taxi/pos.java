package taxi;

/**
 * Created by DESTR on 2016/5/19.
 */
public class pos
{
	private int x;
	private int y;

	/**
	 * modifies: this
	 * effects: 初始化
	 *
	 * @param x
	 * @param y
	 */
	public pos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * effects: 返回this.x
	 * @return
	 */
	public int get_x()
	{
		return x;
	}

	/**
	 * effects: 返回this.y
	 * @return
	 */
	public int get_y()
	{
		return y;
	}

	/**
	 * 返回this是否为合法的pos类的对象
	 * @return
	 */
	public boolean rep_ok()
	{
		return true;
	}
}
