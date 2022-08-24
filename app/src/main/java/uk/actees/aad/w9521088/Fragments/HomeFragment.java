package uk.actees.aad.w9521088.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import uk.actees.aad.w9521088.Activity.ProductDetailActivity;
import uk.actees.aad.w9521088.Adapter.CategoryAdapter;
import uk.actees.aad.w9521088.ApiCall.Model.ProductListResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishListResBean;
import uk.actees.aad.w9521088.ApiCall.Model.WishlistAddedResBean;
import uk.actees.aad.w9521088.ApiCall.Presenter.ProductListPresenter;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishListPresenter;
import uk.actees.aad.w9521088.ApiCall.Presenter.WishlistAddedPresenter;
import uk.actees.aad.w9521088.ApiCall.View.IProductListView;
import uk.actees.aad.w9521088.ApiCall.View.IWishListView;
import uk.actees.aad.w9521088.ApiCall.View.IWishlistAddedView;
import uk.actees.aad.w9521088.R;
import uk.actees.aad.w9521088.Utils.NetworkCheck;
import uk.actees.aad.w9521088.Utils.SharedPreferenceData;
import uk.actees.aad.w9521088.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements CategoryAdapter.ItemClickListner, IProductListView,IWishlistAddedView {

    FragmentHomeBinding binding;
    ArrayList<ProductListResBean.DataItem> list = new ArrayList<>();
    CategoryAdapter categoryAdapter;
    ProductListPresenter presenter;
    WishlistAddedPresenter wishlistAddedPresenter;
    SharedPreferenceData ProfileData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        presenter = new ProductListPresenter();
        presenter.setView(this);
        ProfileData = new SharedPreferenceData(getContext());
        wishlistAddedPresenter = new WishlistAddedPresenter();
        wishlistAddedPresenter.setView(this);

        categoryAdapter = new CategoryAdapter(list, getContext(), this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.rvCategoryItem.setLayoutManager(gridLayoutManager);
        binding.rvCategoryItem.setAdapter(categoryAdapter);

        if(NetworkCheck.isConnected(getActivity())){
            presenter.ProductListCall(getActivity(), ProfileData.getACCESS_TOKEN());
        }
        else{
            NetworkCheck.showNetworkFailureAlert(getActivity());
        }

        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterList(editable.toString());
            }
        });

        return binding.getRoot();
    }

    private void filterList(String text){
        ArrayList<ProductListResBean.DataItem> filteredList = new ArrayList<>();
        for(ProductListResBean.DataItem item :list){
            if(item.getShortDesc().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No data found", Toast.LENGTH_SHORT).show();
        }else{
            categoryAdapter.filterList(filteredList);
        }

    }

    @Override
    public void OnItemClicked(int position,String type) {

        if(type.equalsIgnoreCase("wish")){
            if(NetworkCheck.isConnected(getActivity())){
                wishlistAddedPresenter.onWishlistAdded(getActivity(),ProfileData.getACCESS_TOKEN(),""+list.get(position).getId(),"");
            }
            else{
                NetworkCheck.showNetworkFailureAlert(getActivity());
            }
            categoryAdapter.notifyDataSetChanged();
        }else{
            Intent intent = new Intent(getActivity(),ProductDetailActivity.class);
            intent.putExtra("product_id",String.valueOf(list.get(position).getId()));
            startActivity(intent);
        }

        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onProductListSuccess(ProductListResBean item) {
        list.clear();
        if(item.isSuccess()){
            list.addAll(item.getData());
        }
        categoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void enableLoadingBar(Context context, boolean enable) {

    }

    @Override
    public void onError(String reason) {

    }

    @Override
    public void onWishListAdded(WishlistAddedResBean item) {
        if(item.isStatus()){
            Toast.makeText(getActivity(), item.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}



