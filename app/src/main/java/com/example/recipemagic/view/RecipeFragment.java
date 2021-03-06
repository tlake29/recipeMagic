package com.example.recipemagic.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recipemagic.R;

import com.example.recipemagic.view.dummy.DummyContent.DummyItem;
import com.squareup.picasso.Picasso;

import java.util.Objects;

/**
 * This function is designed to display the recipe title, image, instructions,
 * and directions to the user.
 */
public class RecipeFragment extends Fragment{

    private OnListFragmentInteractionListener mListener;
    private String title;
    private String image;
    private String recipe;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressWarnings("unused")
    public static RecipeListFragment newInstance(int columnCount) {
        RecipeListFragment fragment = new RecipeListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This function officially creates the view of the recipes.
     * It sets the title, directions, ingredients and images from
     * a bundle created in the RecipeListAdapter. It then sets these
     * variables into the view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            title = Objects.requireNonNull(bundle.get("Title")).toString();
            String directions = Objects.requireNonNull(bundle.get("Direction")).toString();
            String ingredients = Objects.requireNonNull(bundle.get("Ingredient")).toString();
            image = Objects.requireNonNull(bundle.get("Image")).toString();
            recipe = ingredients + "\n" + directions;
        }

        TextView recipe_text = (TextView) view.findViewById(R.id.textDescription);
        TextView recipe_title = (TextView) view.findViewById(R.id.textTitle);
        ImageView recipe_image = (ImageView) view.findViewById(R.id.recipe_picture);

        recipe_title.setText(title);
        Picasso.get().load(image).into(recipe_image);
        recipe_text.setText(recipe);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}