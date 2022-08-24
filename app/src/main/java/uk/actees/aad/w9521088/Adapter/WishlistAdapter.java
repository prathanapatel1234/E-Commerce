package uk.actees.aad.w9521088.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.actees.aad.w9521088.ApiCall.Model.WishListResBean;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.ApiConstants;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.MyViewHolder>{

    ArrayList<WishListResBean.DataItem>list;
    Context context;
    ItemClickListner listner;
    public interface ItemClickListner{
        public void OnItemClicked(int position,String type,String product_id);
    }

    public WishlistAdapter(ArrayList<WishListResBean.DataItem> list, Context context, ItemClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_child_wishlist,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        WishListResBean.DataItem dataItem = list.get(position);
        Picasso.get().load(ApiConstants.BASE_IMAGE_URL +"/"+dataItem.getProduct().getImage()).into(holder.img);
        holder.txtProductType.setText(list.get(position).getProduct().getProductName());
        holder.txtPrice.setText(list.get(position).getProduct().getProductMrpPrice());

        holder.imgWishListed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnItemClicked(position,"wish",""+dataItem.getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img,imgWishListed;
        TextView txtPrice,txtProductType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgBoy);
            imgWishListed = itemView.findViewById(R.id.imgWishListClicked);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtProductType = itemView.findViewById(R.id.txtProductType);

        }
    }
}
