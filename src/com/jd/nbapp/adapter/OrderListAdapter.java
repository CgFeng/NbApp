package com.jd.nbapp.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jd.nbapp.R;
import com.jd.nbapp.bean.Order;
import com.jd.nbapp.ui.RouteActivity;
import com.jd.nbapp.utils.ListViewUtils;

public class OrderListAdapter extends BaseAdapter {
	
	private List<Order> orders;
	private Context context;
	public OrderListAdapter(Context context,List<Order> orders) {
		this.context = context;
		this.orders = orders;
	}
	@Override
	public int getCount() {
		return orders.size();
	}

	@Override
	public Order getItem(int position) {
		return orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView==null){
			convertView = View.inflate(context, R.layout.view_order_item, null);
			holder = new ViewHolder();
			holder.tv_order_num = (TextView) convertView.findViewById(R.id.tv_order_num);
			holder.tv_order_price = (TextView) convertView.findViewById(R.id.tv_order_price);
			//holder.btn_track_order = (Button) convertView.findViewById(R.id.btn_track_order);
			holder.tv_order_time = (TextView) convertView.findViewById(R.id.tv_order_time);
			holder.tv_order_address = (TextView) convertView.findViewById(R.id.tv_order_address);
			holder.tv_order_client = (TextView) convertView.findViewById(R.id.tv_order_client);
			holder.tv_order_phone = (TextView) convertView.findViewById(R.id.tv_order_phone);
			holder.tv_order_state = (TextView) convertView.findViewById(R.id.tv_order_state);
			holder.lv_order_good = (ListView) convertView.findViewById(R.id.lv_order_good);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		Order order = getItem(position);
		holder.tv_order_num.setText(order.getOrder_sn());
		holder.tv_order_price.setText(order.getOrder_amount() + "");
		holder.tv_order_time.setText(order.getAdd_time() + "");
		holder.tv_order_address.setText(order.getAddress());
		holder.tv_order_client.setText(order.getConsignee());
		holder.tv_order_phone.setText(order.getTel_mobile());
		holder.tv_order_state.setText(order.getOrder_status());
		
		holder.lv_order_good.setAdapter(new OrderGoodsAdapter(context,orders.get(position).getOrder_goods()));
		ListViewUtils.setListViewHeightBasedOnChildren(holder.lv_order_good);
		//holder.btn_track_order.setOnClickListener(new OrderTrackListener(getItem(position).getOrderId()));
		return convertView;
	}
	
	private static class ViewHolder{
		TextView tv_order_num;
		TextView tv_order_price;
		//Button btn_track_order;
		TextView tv_order_time;
		TextView tv_order_address;
		TextView tv_order_client;
		TextView tv_order_phone;
		ListView lv_order_good;
		TextView tv_order_state;
	}
	
	private class OrderTrackListener implements OnClickListener{
		private String orderId;
		public OrderTrackListener(String orderId) {
			this.orderId = orderId;
		}
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context,RouteActivity.class);
			intent.putExtra("orderId", orderId);
			context.startActivity(intent);
		}
		
	}

}
