package com.example.recipemagic.presenter;


import android.content.Context;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import com.example.recipemagic.model.CookBook;

/**
* MainPresenter is used to start the downloading process
* and to store all recipes and categories inside the cookbook
* that will be used throughout the program.
*/
public class MainPresenter {
    private CookBook book;
    private boolean bookReady;
    private List<Listener> registeredDataUsers;

    public MainPresenter(Context context, ProgressBar pb){
        book = new CookBook();
        bookReady = false;
        registeredDataUsers = new ArrayList<Listener>();
        DownloadCategories task = new DownloadCategories(this, pb, context);
        task.execute();
    }

    /**
     * 
     * @param dataUser
     */
    public void registerDataUser(MainPresenter.Listener dataUser){
        registeredDataUsers.add(dataUser);
        if (bookReady){
            dataUser.notifyDataReady();
        }
    }

    /**
    * Only after the cookbook is done loading, everything is displayed.
    */
    public void notifyDataUsers() {
        bookReady = true;
        for (MainPresenter.Listener dataUser : registeredDataUsers) {
            dataUser.notifyDataReady();
        }
    }

    /**
     * This returns the cookBook that everything is downloaded into.
     * @return
     */
    public CookBook getCookBook() {
        return book;
    }

    /**
     * This function lets the user know if all the downloading is done.
     * @return
     */
    public boolean isBookReady(){
        return bookReady;
    }

    /**
     * This interface has is implemented in the fragments
     */
    public interface Listener{
        void notifyDataReady();
    }
}