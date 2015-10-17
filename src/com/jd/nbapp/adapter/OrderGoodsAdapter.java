package com.jd.nbapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jd.nbapp.R;
import com.jd.nbapp.bean.CartProduct;
import com.jd.nbapp.bean.OrderGoods;

public class OrderGoodsAdapter extends BaseAdapter {
	List<OrderGoods> products;
	Context context;
	public OrderGoodsAdapter(Context context,List<OrderGoods> products) {
		this.context = context;
		this.products = products;
	}

	@Override
	public int getCount() {
		return products.size();
	}

	@Override
	public OrderGoods getItem(int position) {
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = View.inflate(context,R.layout.view_order_good_item, null);
			holder = new ViewHolder(); 
			
			holder.tv_good_name = (TextView) convertView.findViewById(R.id.tv_good_name);
			holder.tv_good_price = (TextView) convertView.findViewById(R.id.tv_good_price);
			holder.tv_good_num = (TextView) convertView.findViewById(R.id.tv_good_num);
			holder.tv_good_sum = (TextView) convertView.findViewById(R.id.tv_good_sum);
			holder.tv_good_type = (TextView) convertView.findViewById(R.id.tv_good_type);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		OrderGoods item = getItem(position);
		holder.tv_good_name.setText(item.getGoods_name());
		double goods_price = item.getGoods_price();
		int goods_number = item.getGoods_number();
		holder.tv_good_price.setText("￥"+goods_price+"");
		holder.tv_good_num.setText(goods_number+"");
		holder.tv_good_sum.setText("￥"+goods_price*goods_number+"");
		holder.tv_good_type.setText("类别");
		return convertView;
	}
	
	private class ViewHolder{
		TextView tv_good_name;
		TextView tv_good_price;
		TextView tv_good_num;
		TextView tv_good_sum;
		TextView tv_good_type;
	}

}
