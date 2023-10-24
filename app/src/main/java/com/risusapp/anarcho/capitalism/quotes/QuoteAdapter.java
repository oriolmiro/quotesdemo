package com.risusapp.anarcho.capitalism.quotes;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {
    private Context context;
    private List<Quote> quotes;
    private int numColumns;
    public QuoteAdapter(Context context, List<Quote> quotes , int numColumns) {
        this.context = context;
        this.quotes = quotes;
    }
    @NonNull
    @Override
    public QuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(numColumns == 1) {
             view = LayoutInflater.from(context).inflate(R.layout.item_quote, parent, false);
        }else{
             view = LayoutInflater.from(context).inflate(R.layout.item_quote_2, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.ViewHolder holder, int position) {
        Quote quote = quotes.get(position);
        //Name
        holder.tvAuthor.setText(quote.getName());
        holder.tvDescription.setText(quote.getDescription());
        //Image
        int
                 idImagen = this.context.getResources().getIdentifier(quote.img, "drawable", context.getPackageName());
        if (idImagen != 0) {
            holder.imgAuthor.setImageResource(idImagen);
        } else {
            // Si la imagen no se encuentra, puedes mostrar la imagen por defecto
            holder.imgAuthor.setImageResource(R.drawable.avatar_placeholder);
        }
        //audio
        int resourceId = context.getResources().getIdentifier(quote.getSound_resource(), "raw", context.getPackageName());
        if (resourceId != 0) {
            holder.mediaPlayer = MediaPlayer.create(context, resourceId);
        }
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.mediaPlayer.start();
            }
        });
    }

    @Override
    public int getItemCount() {
        return quotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAuthor;
        TextView tvDescription;
        ImageView imgAuthor;
        ImageButton btnPlay;
        MediaPlayer mediaPlayer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvDescription = itemView.findViewById(R.id.tv_description);
            imgAuthor = itemView.findViewById(R.id.iv_quote);
            btnPlay = itemView.findViewById(R.id.ib_play);
        }
    }
}
