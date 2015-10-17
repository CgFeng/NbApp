package com.jd.nbapp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.jd.nbapp.R;
import com.jd.nbapp.bean.LinkMan;
import com.loopj.android.http.AsyncHttpClient;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 地址适配器
 * @author Cool
 *
 */
public class AdItemAdapter extends BaseAdapter {
	
	private Context context;
	private List<LinkMan> linkmans = new ArrayList<LinkMan>();
	private int selected = 0;
	public AdItemAdapter(Context context,List<LinkMan> linkmans,int selected){
		this.context = context;
		this.linkmans = linkmans;
		this.selected = selected;
	}
	@Override
	public int getCount() {
		return linkmans.size();
	}
	@Override
	public Object getItem(int position) {
		return linkmans.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = View.inflate(context, R.layout.view_addresslist_item, null);
			holder = new ViewHolder();
			holder.selected =  (ImageView) convertView.findViewById(R.id.iv_ad_selected );
			holder.name =  (TextView) convertView.findViewById(R.id.tv_ad_linkman );
			holder.phone =  (TextView) convertView.findViewById(R.id.tv_ad_phone );
			holder.address =  (TextView) convertView.findViewById(R.id.tv_address );
		    convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (selected == position) {
			holder.selected.setVisibility(View.VISIBLE);
		}else {
			holder.selected.setVisibility(View.GONE);
		}
		holder.name.setText(linkmans.get(position).getName());
		holder.phone.setText(linkmans.get(position).getPhone());
		holder.address.setText(linkmans.get(position).getAddress());
		return convertView;
	}
	
	static class ViewHolder{
		private ImageView selected;
		private TextView name;
		private TextView phone;
		private TextView address;
	}
}
