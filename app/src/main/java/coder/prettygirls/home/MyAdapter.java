package coder.prettygirls.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import coder.mylibrary.base.SimpleBaseAdapter;
import coder.prettygirls.R;
import coder.prettygirls.data.bean.PicCategory;

/**
 * Created by dengpan616@qq.com on 2016/7/23.
 */
public class MyAdapter extends SimpleBaseAdapter<PicCategory> {
    public MyAdapter(Context c, List<PicCategory> datas) {
        super(c, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            view = layoutInflater.inflate(R.layout.item_category_view, viewGroup, false);
            holder=new ViewHolder();
            holder.tv_name= (TextView) view.findViewById(R.id.category_name);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_name.setText(datas.get(i).getName());
        return view;
    }
    class ViewHolder{
        TextView tv_name;
    }
}
