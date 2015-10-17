package com.jd.nbapp.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jd.nbapp.R;
import com.jd.nbapp.bean.CartInfo;
import com.jd.nbapp.bean.CartProduct;
import com.jd.nbapp.ui.CartActivity;
import com.jd.nbapp.ui.DetailActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

public class CartItemAdapter extends BaseAdapter {
	private Context context;
	private List<CartInfo> data = new ArrayList<CartInfo>();
	ImageLoader imageLoader = ImageLoader.getInstance();
	

	public CartItemAdapter(Context context, List<CartInfo> data) {
		this.context = context;
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.view_cartlist_item, null);
			holder = new ViewHolder();

			// 提示(商品名称)
			holder.tips = (TextView) convertView.findViewById(R.id.tv_cart_remind);
			// 单价
			holder.cartPrice = (TextView) convertView.findViewById(R.id.tv_cart_price);
			// 每件商品的checkBox
			holder.cartItemCheck = (CheckBox) convertView.findViewById(R.id.cb_cart_item_check);
			// 商品的图片
			holder.cartPic = (ImageView) convertView.findViewById(R.id.iv_cart_pic);
			// 每件商品购买的金额（总价）
			holder.cart_itemSum = (TextView) convertView.findViewById(R.id.tv_cart_item_sum);

			holder.et = (EditText) convertView.findViewById(R.id.et_product_num);
			holder.iv_product_num_add = (ImageView) convertView.findViewById(R.id.iv_product_num_add);
			holder.iv_product_num_des = (ImageView) convertView.findViewById(R.id.iv_product_num_des);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		CartInfo cartInfo = new CartInfo();
		holder.tips.setText(data.get(position).getGoods_name());
		holder.cartPrice.setText(data.get(position).getGoods_price() + "");
		holder.cartItemCheck.setChecked(data.get(position).isCheck());
		holder.cart_itemSum.setText(data.get(position).getGoods_number() * data.get(position).getGoods_price() + "");
		holder.et.setText(data.get(position).getGoods_number() + "");
		imageLoader.displayImage(data.get(position).getImageUrl(), holder.cartPic);

		holder.iv_product_num_add
				.setOnClickListener(new NumChangeListener(true, holder.et, holder.cartPrice, holder.cart_itemSum,position));
		holder.iv_product_num_des
				.setOnClickListener(new NumChangeListener(false, holder.et, holder.cartPrice, holder.cart_itemSum,position));
		//holder.cartItemCheck.setOnCheckedChangeListener(new CartCheckChangeListener(position));
		return convertView;
	}

	private class ViewHolder {
		
		TextView tips;
		TextView cartPrice;
		CheckBox cartItemCheck;
		ImageView cartPic;
		TextView cart_itemSum;
		EditText et;
		ImageView iv_product_num_add;
		ImageView iv_product_num_des;
	}

	class NumChangeListener implements OnClickListener {
		private boolean isAdd;
		private EditText et;
		private TextView cartPrice;
		private TextView cart_itemSum;
		private int position;

		public NumChangeListener(boolean isAdd, EditText et, TextView cartPrice, TextView cart_itemSum,int position) {
			this.isAdd = isAdd;
			this.et = et;
			this.cartPrice = cartPrice;
			this.cart_itemSum = cart_itemSum;
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			String string = et.getText().toString();
			String onePrice = cartPrice.getText().toString();
			int num = Integer.parseInt(string);
			if (isAdd) {
				et.setText(++num + "");
				cart_itemSum.setText(num * Double.parseDouble(onePrice) + "");
			} else {
				if (num <= 1)
					return;
				et.setText(--num + "");
				cart_itemSum.setText(num * Double.parseDouble(onePrice) + "");
			}
			data.get(position).setGoods_number(num);
			CartActivity.gHandler.sendEmptyMessage(0);
		}
	}
	
	/*class CartCheckChangeListener implements OnCheckedChangeListener{
		private int position;
		
		public CartCheckChangeListener(int position) {
			this.position = position;
		}
		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			data.get(position).setCheck(isChecked);
		}
		
	}*/

}