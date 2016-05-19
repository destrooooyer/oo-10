package taxi;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by DESTR on 2016/5/19.
 */
public class car_mofa extends car
{
	private Vector<Vector<Integer>> x;
	private Vector<Vector<Integer>> y;
	private int flag;

	public Iterator get_path(int x)
	{
		return new path(x);
	}

	public int get_ser_count()
	{
		return x.size();
	}

	/**
	 * requires: _map是联通的地图
	 * modifies: 无
	 * effects: 初始化 status, reputation, stop_count等变量
	 *
	 * @param _map
	 */
	public car_mofa(map _map)
	{
		super(_map);
		//this.paths=new Vector<path>();
		x = new Vector<Vector<Integer>>();
		x.add(new Vector<Integer>());
		y = new Vector<Vector<Integer>>();
		y.add(new Vector<Integer>());
		this.flag = 0;
	}

	/**
	 * requires: light是0或1
	 * effects: 调用move，flag为1，表示能走关闭的路
	 *
	 * @param light
	 * @return
	 */
	@Override
	public boolean new_move(int light)
	{
		if (this.getStatus() == status_kinds.serving)
		{
			//System.out.println(x.size());
			x.get(x.size() - 1).add(this.get_x());
			y.get(y.size() - 1).add(this.get_y());
			this.flag = 1;
		}
		else
		{
			if (this.flag == 1)
			{
				this.flag = 0;
				x.add(new Vector<Integer>());
				y.add(new Vector<Integer>());
			}
		}
		return move(light, 1);
	}

	class path implements Iterator
	{
		private int n;
		private int iter;

		Vector<Integer> pos_x;
		Vector<Integer> pos_y;

		public path(int p)
		{
			this.pos_x = x.get(p);
			this.pos_y = y.get(p);
			this.n = pos_x.size();
		}

		@Override
		public boolean hasNext()
		{
			return iter < n;
		}

		@Override
		public Object next()
		{
			if (iter < n)
			{
				pos temp = new pos(pos_x.get(iter), pos_y.get(iter));
				iter++;
				return temp;
			}
			else
				return null;
		}

		@Override
		public void remove()
		{

		}
	}
}
