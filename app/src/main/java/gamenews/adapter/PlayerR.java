package gamenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import gamenews.R;
import gamenews.vistas.DetallesJugador;
import gamenews.room.model.Player;


public class PlayerR extends RecyclerView.Adapter<PlayerR.ViewHolder> {
    private static final String PLAYER = "player";
    private final Context context;
    private List<Player> list;
    private int lastPosition = -1;


    public PlayerR(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_topplayers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (list != null) {
            holder.mItem = list.get(position);
            holder.mContentView.setText(list.get(position).getName());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, DetallesJugador.class);
                    Bundle b = new Bundle();
                    b.putSerializable(PLAYER, list.get(position));
                    i.putExtras(b);
                    context.startActivity(i);
                }
            });
            Glide.with(context).load(list.get(position).getAvatar()).apply(RequestOptions.centerCropTransform()).into(holder.avatar);
            setAnimation(holder.itemView, position);
        }
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else return 0;
    }

    public void setList(List<Player> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Player mItem;
        public ImageView avatar;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
            avatar = view.findViewById(R.id.avatar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
