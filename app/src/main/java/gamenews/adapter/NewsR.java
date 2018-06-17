package gamenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import gamenews.R;
import gamenews.room.model.News;
import gamenews.vistas.Detalles;


public abstract class NewsR extends RecyclerView.Adapter<NewsR.ViewHolder> {

    private List<News> list;
    private Context context;


    public NewsR(Context context) {

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(list!=null) {
            holder.title.setText(list.get(position).getTitle());
            holder.content.setText(list.get(position).getDescription());
            Glide.with(context).load(list.get(position).getCoverImage()).apply(RequestOptions.centerCropTransform()).into(holder.image);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Detalles.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("new", list.get(position));
                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
            if (position == 0) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                layoutParams.setFullSpan(true);
            }
            if (list.get(position).isFavorite())
                holder.star.setImageResource(android.R.drawable.star_big_on);
            else holder.star.setImageResource(android.R.drawable.star_big_off);
            holder.star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    setAction(list.get(position).isFavorite(), list.get(position).getId());


                }
            });
        }
    }


    public abstract void setAction(boolean isFavorite, String id_new);
    @Override
    public int getItemCount() {
        if(list!=null) {
            return list.size();
        }
        return 0;
    }

    public void setList(List<News> list) {
        this.list = list;
    }

    public List<News> getList() {
        return list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView image, star;
        public TextView title,content;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            image = view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.title);
            content = (TextView) view.findViewById(R.id.content);
            star = view.findViewById(R.id.star);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) return 1;
        else return 0;
    }
}
