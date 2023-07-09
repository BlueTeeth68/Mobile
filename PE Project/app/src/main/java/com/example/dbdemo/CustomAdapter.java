package com.example.dbdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    private Context context;
    private ArrayList<String> book_id, book_title, book_author, book_pages;

    public CustomAdapter(Context context,
                         ArrayList<String> book_id,
                         ArrayList<String> book_title,
                         ArrayList<String> book_author,
                         ArrayList<String> book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bookIdText.setText(String.valueOf(book_id.get(position)));
        holder.bookTitleText.setText(String.valueOf(book_author.get(position)));
        holder.bookAuthorText.setText(String.valueOf(book_author.get(position)));
        holder.bookPagesText.setText(String.valueOf(book_pages.get(position)));
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookIdText, bookTitleText, bookAuthorText, bookPagesText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookIdText = itemView.findViewById(R.id.book_id_text);
            bookTitleText = itemView.findViewById(R.id.book_title_text);
            bookAuthorText = itemView.findViewById(R.id.book_author_text);
            bookPagesText = itemView.findViewById(R.id.book_pages_text);
        }
    }
}
