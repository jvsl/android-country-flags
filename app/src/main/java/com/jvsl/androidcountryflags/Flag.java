package com.jvsl.androidcountryflags;

import android.content.Context;

/**
 * Created by joão on 09/02/17.
 */

public class Flag {

    public int getFlagByCountryCode(Context context, String countryCode){
        return context.getResources().getIdentifier(countryCode.toLowerCase() , "drawable", context.getPackageName());


    }
}
