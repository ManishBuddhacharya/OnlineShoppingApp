package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e.onlineshoppingapp.ItemDetailsActivity;
import com.e.onlineshoppingapp.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import modal.Items;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    Context mContext;
    List<Items> itemLists;

    public ItemsAdapter(Context mContext, List<Items> itemLists) {
        this.mContext = mContext;
        this.itemLists = itemLists;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_items, viewGroup, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {
        final Items item = itemLists.get(i);
        itemsViewHolder.imgProfile.setImageResource(item.getImageId());
        itemsViewHolder.tvName.setText(item.getName());
        itemsViewHolder.tvContact.setText(item.getPrice());

        itemsViewHolder.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                intent.putExtra("image", item.getImageId());
                intent.putExtra("price", item.getPrice());
                intent.putExtra("name", item.getName());
                intent.putExtra("description", item.getDescription());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemLists.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgProfile;
        TextView tvName, tvContact;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            tvName = itemView.findViewById(R.id.tvName);
            tvContact = itemView.findViewById(R.id.tvContact);
        }
    }
}


