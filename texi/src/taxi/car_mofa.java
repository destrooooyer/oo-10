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

	/**
	 * requires: x小于x.size()
	 * effects: 返回第x次完成请求经过的路线
	 *
	 * @param x
	 * @return
	 */
	public Iterator get_path(int x)
	{
		synchronized (this)
		{
			if (x < this.x.size())
			{
				return new path(x);
			}
			else
				return null;
		}
	}

	/**
	 * effects: 返回完成请求的次数
	 *
	 * @return
	 */
	public int get_ser_count()
	{
		synchronized (this)
		{
			return x.size();
		}
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
		synchronized (this)
		{
			x = new Vector<Vector<Integer>>();
			x.add(new Vector<Integer>());
			y = new Vector<Vector<Integer>>();
			y.add(new Vector<Integer>());
		}
		this.flag = 0;
	}

	/**
	 * requires: light是0或1
	 * modifies: this
	 * effects: 调用move，flag为1，表示能走关闭的路
	 *
	 * @param light
	 * @return
	 */
	@Override
	public boolean new_move(int light)
	{
		synchronized (this)
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
		}
		return move(light, 1);
	}

	/**
	 * effects: 返回this是否是合法的car_mofa的对象
	 *
	 * @return
	 */
	@Override
	public boolean rep_ok()
	{
		if (x == null || y == null)
			return false;
		if (flag != 0 && flag != 1)
			return false;
		return super.rep_ok();
	}

	class path implements Iterator
	{
		private int n;
		private int iter;

		private Vector<Integer> pos_x;
		private Vector<Integer> pos_y;

		/**
		 * modifies: this
		 * effects: 初始化
		 *
		 * @param p
		 */
		public path(int p)
		{
			synchronized (this)
			{
				this.pos_x = x.get(p);
				this.pos_y = y.get(p);
			}
			this.n = pos_x.size();
		}

		/**
		 * effects: 返回iter<n
		 *
		 * @return
		 */
		@Override
		public boolean hasNext()
		{
			return iter < n;
		}

		/**
		 * effects: 返回iter>=0
		 *
		 * @return
		 */
		public boolean has_prev()
		{
			return iter >= 0;
		}

		/**
		 * modifies: this
		 * effects: 若iter<n则返回new pos(pos_x.get(iter), pos_y.get(iter))
		 * 否则返回null
		 *
		 * @return
		 */
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

		/**
		 * modifies: this
		 * effects: 若iter<n则返回new pos(pos_x.get(iter), pos_y.get(iter))
		 * 否则返回null
		 *
		 * @return
		 */
		public Object prev()
		{
			if (iter >= 0)
			{
				pos temp = new pos(pos_x.get(iter), pos_y.get(iter));
				return temp;
			}
			else
				return null;
		}

		/**
		 * effects: 无
		 */
		@Override
		public void remove()
		{
			return;
		}

		/**
		 * 返回this是否是合法的car_mofa.path的对象
		 *
		 * @return
		 */
		boolean rep_ok()
		{
			return (pos_x != null && pos_y != null && iter < n);
		}

	}
}
