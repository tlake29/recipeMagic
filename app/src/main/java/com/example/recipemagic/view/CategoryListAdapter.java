package com.example.recipemagic.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipemagic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
* This class is designed to connect the CategoryListFragment with the
* RecyclerView. The XML layout fragment_categorylist_list.xml has a
* recyclerView that has multiple fragment_category.list.xml layouts.
*/
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<String> titles;
    private List<String> images;


    CategoryListAdapter(List<String> titles, List<String> images) {
        this.titles = titles;
        this.images = images;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_category, parent, false);
        return new ViewHolder(view);
    }

    /**
     * This function puts a category title and a picture inside each cardview.
     * It then implements an onClickListener for the cardview so that
     * each card, when clicked, will display the list of recipes in the
     * category selected.
     * @param holder
     * @param i
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {
        holder.category_title.setText(titles.get(i));
        Picasso.get().load(images.get(i)).into(holder.category_image);
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //passing data to recipe list activity
                Bundle bundle = new Bundle();
                bundle.putString("Category", titles.get(i));


                Fragment myFragment = new RecipeListFragment();
                myFragment.setArguments(bundle);

                //start Fragment
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, myFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    /**
     * This class is designed to set the view of each variable to a
     * specific part of the cardview.
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView category_title;
        ImageView category_image;
        CardView cardview;

        ViewHolder(View view) {
            super(view);
            category_title = (TextView) itemView.findViewById(R.id.category);
            category_image = (ImageView) itemView.findViewById(R.id.category_Img);
            cardview = (CardView) itemView.findViewById(R.id.cardview_category);
        }
    }
}