package com.jvsl.androidcountryflags;

import android.content.Context;
import android.view.View;

/**
 * Created by joão on 09/02/17.
 */

public class Flag {
    private Context context;

    public Flag(Context context){
        this.context = context;
    }

    public int getFlagByCountryCode(String countryCode){
        return context.getResources().getIdentifier(countryCode.toLowerCase() , "drawable", context.getPackageName());


    }
}
