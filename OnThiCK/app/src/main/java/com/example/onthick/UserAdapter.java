package com.example.onthick;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHoler> {

    private List<User> mlist_user;
    private Context context;
    private UserDatabase database;

    public UserAdapter(List<User> mlist_user, Context context) {
        this.mlist_user = mlist_user;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_layout,parent,false);
        return new UserViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHoler holder, int position) {
        User user = mlist_user.get(position);
        database = UserDatabase.getInstance(context);

        holder.tv_username.setText(user.getName_user());
        holder.tv_useradress.setText(user.getAdress_user());

        holder.btn_edit.setOnClickListener(v->{

            User user1 =mlist_user.get(holder.getAdapterPosition());

            final int id = user1.getId_user();
            String name = user1.getName_user();
            String adress = user1.getAdress_user();
            System.out.println(name);
            Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_update);

            int width = WindowManager.LayoutParams.MATCH_PARENT;
            int height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width,height);
            dialog.show();

            EditText update_name =dialog.findViewById(R.id.editTextNameUpdate);
            EditText update_adress = dialog.findViewById(R.id.editTextAgeUpdate);

            Button btnUpdate=dialog.findViewById(R.id.btnUpdate);

            update_name.setText(name);
            update_adress.setText(adress);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    String uName=update_name.getText().toString().trim();
                    String uAdress = update_adress.getText().toString().trim();


                    database.userDao().update(id,uName,uAdress);
                    Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                    mlist_user.clear();
                    mlist_user.addAll(database.userDao().getAllUser());
                    notifyDataSetChanged();
                }
            });
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = mlist_user.get(holder.getAdapterPosition());

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.mipmap.ic_launcher);
                builder.setMessage("Bạn có muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        database.userDao().Delete_1_user(user1);
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        mlist_user.clear();
                        mlist_user.addAll(database.userDao().getAllUser());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không",null);
                builder.show();
            }
        });
        holder.tv_username.setOnClickListener(new View.OnClickListener() {
            User user = mlist_user.get(holder.getAdapterPosition());
            @Override
            public void onClick(View v) {
                Toast.makeText(context, user.getName_user(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, test.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user",user);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist_user.size();
    }

    public class UserViewHoler extends RecyclerView.ViewHolder {
        private TextView tv_username,tv_useradress;
        private ImageButton btn_delete,btn_edit;
        public UserViewHoler(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_useradress = itemView.findViewById(R.id.tv_adress);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_edit = itemView.findViewById(R.id.btn_edit);
        }
    }

}
