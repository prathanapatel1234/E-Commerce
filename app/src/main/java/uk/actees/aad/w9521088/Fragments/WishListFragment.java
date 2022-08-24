package uk.actees.aad.w9521088.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uk.actees.aad.w9521088.Adapter.WishlistAdapter;
import uk.actees.aad.w9521088.ApiCall.Model.ProductDetailResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishListResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishlistAddedResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishListPresenter;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishlistAddedPresenter;
import uk.actees.aad.w9521088.ApiCall.View.IWishListView;
import uk.actees.aad.w9521088.ApiCall.View.IWishlistAddedView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.ApiConstants;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;
import uk.actees.aad.w9521088.databinding.FragmentWishListBinding;

public class WishListFragment extends Fragment implements WishlistAdapter.ItemClickListner, IWishListView,IWishlistAddedView {

    FragmentWishListBinding binding;
    ArrayList<WishListResBean.DataItem> list = new ArrayList<>();
    WishlistAdapter wishlistAdapter;
    WishListPresenter presenter;
    WishlistAddedPresenter wishlistAddedPresenter;
    SharedPreferenceData ProfileData;
    Integer Pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil .inflate(inflater,R.layout.fragment_wish_list, container, false);

        presenter = new WishListPresenter();
        presenter.setView(this);
        wishlistAddedPresenter = new WishlistAddedPresenter();
        wishlistAddedPresenter.setView(this);
        ProfileData = new SharedPreferenceData(getContext());

        wishlistAdapter = new WishlistAdapter(list,getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        binding.rvWishlist.setLayoutManager(linearLayoutManager);
        binding.rvWishlist.setAdapter(wishlistAdapter);

        if(NetworkCheck.isConnected(getContext())){
            presenter.onWishlistCall(getActivity(),ProfileData.getACCESS_TOKEN());
        }
        else {
            NetworkCheck.showNetworkFailureAlert(getActivity());
        }

        return binding.getRoot();
    }

    @Override
    public void OnItemClicked(int position, String type,String product_id) {
        Pos = position;
        if(type.equalsIgnoreCase("wish")){
            wishlistAddedPresenter.onWishlistAdded(getActivity(),ProfileData.getACCESS_TOKEN(),product_id,"");
        }

    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }

    @Override
    public void onWishlistSuccess(WishListResBean item) {
        if(item.isStatus()){
            if(item.getData() !=null){
                list.addAll(item.getData());
            }
            wishlistAdapter.notifyDataSetChanged();

            if (item != null &&item.getData().size() >=1){
                binding.txtNoWishListItem.setVisibility(View.GONE);
            }else{
                binding.txtNoWishListItem.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onWishListAdded(WishlistAddedResBean item) {
        if(item.isStatus()){
            Toast.makeText(getActivity(), item.getMessage(), Toast.LENGTH_SHORT).show();
            list.remove(list.get(Pos));
            wishlistAdapter.notifyDataSetChanged();
        }

    }
}