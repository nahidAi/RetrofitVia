package test.retrofit.com.retrofitvia.classes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.retrofit.com.retrofitvia.DetailActivity;
import test.retrofit.com.retrofitvia.R;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Model> list;

    public Adapter(Context context, List<Model> list) {
        this.context = context;
        this.list = list;
        //افزایش تعداد آیتم بطور موقت
        this.list.addAll(list);
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Model model = list.get(position);
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        Picasso.get().load(model.getImage_url()).into(holder.imageView);
        holder. itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView name;
        private TextView email;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);


        }
    }
}
