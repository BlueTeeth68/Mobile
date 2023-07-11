package com.example.pesample.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pesample.R;
import com.example.pesample.UpdateActivity;
import com.example.pesample.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<UserDTO> userDTOS;

    public UserAdapter(Context context, List<UserDTO> userDTOS) {
        this.context = context;
        this.userDTOS = userDTOS;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserDTO tmp = userDTOS.get(position);
        holder.userIdText.setText(String.valueOf(tmp.getId()));
        holder.usernameText.setText(String.valueOf(tmp.getUsername()));
        holder.fullNameText.setText(String.valueOf(tmp.getFullName()));
        holder.roleText.setText(String.valueOf(tmp.getRole()));
    }

    @Override
    public int getItemCount() {
        return userDTOS != null ? userDTOS.size() : 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView userIdText, usernameText, fullNameText, roleText;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userIdText = itemView.findViewById(R.id.user_id_text);
            usernameText = itemView.findViewById(R.id.user_name_text);
            fullNameText = itemView.findViewById(R.id.full_name_text);
            roleText = itemView.findViewById(R.id.role_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                    intent.putExtra("id", userIdText.getText());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
