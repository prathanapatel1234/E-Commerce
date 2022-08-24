package uk.actees.aad.w9521088.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import uk.actees.aad.w9521088.ApiCall.Model.ProductListResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishListPresenter;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.ApiConstants;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    ArrayList<ProductListResBean.DataItem> list;
    Context context;
    ItemClickListner listner;

    public interface ItemClickListner{
        public void OnItemClicked(int position,String type);
    }

    public CategoryAdapter(ArrayList<ProductListResBean.DataItem> list, Context context,ItemClickListner listner) {
        this.list = list;
        this.context = context;
        this.listner = listner;
    }

    public void filterList(ArrayList<ProductListResBean.DataItem> filterlist){
        list = filterlist;

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_child_home_category,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.get().load(ApiConstants.BASE_IMAGE_URL+ "/"+ list.get(position).getImage()).into(holder.img_Uniform);
        holder.txtPrice.setText(list.get(position).getProductMrpPrice());
        holder.txt_Uniform_Type.setText(list.get(position).getShortDesc());


        if(list.get(position).getIsWishlist() == 1){
            holder.imgWishlist.setImageResource(R.drawable.img_wishlist_clicked);

        }else if(list.get(position).getIsWishlist() == 0){
            holder.imgWishlist.setImageResource(R.drawable.img_wishlist);
        }

        holder.cons_Root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listner.OnItemClicked(position,"Detail");
            }
        });

        holder.imgWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list.get(position).getIsWishlist() == 1){
                    list.get(position).setIsWishlist(0);
                }
                else if(list.get(position).getIsWishlist() == 0){
                    list.get(position).setIsWishlist(1);
                }

                listner.OnItemClicked(position,"wish");
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cons_Root,cons_Uniform;
        CardView cv_Wishlist;
        ImageView img_Uniform,imgWishlist;
        TextView txt_Uniform_Type,txtPrice;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cons_Root = itemView.findViewById(R.id.cons_Root);
            cons_Uniform = itemView.findViewById(R.id.consUniform);
            img_Uniform = itemView.findViewById(R.id.imgUniform);
            imgWishlist = itemView.findViewById(R.id.imgWishList);
            txt_Uniform_Type = itemView.findViewById(R.id.txtUniformType);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            cv_Wishlist = itemView.findViewById(R.id.cv_Wishlist);
        }


    }

}
