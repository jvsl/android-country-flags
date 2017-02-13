package com.jvsl.androidcountryflags;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.jvsl.androidcountryflags.EspressoTestsMatchers.withDrawable;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class FlagTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
                MainActivity.class);

     @Test
    public void testBrFlag__countryNameBr__True(){
         onView(withId(R.id.txt_flag)).perform(typeText("br"), closeSoftKeyboard());
         onView(withId(R.id.btn_add_flag)).perform(click());
         // Check that the text was changed.
         onView(withId(R.id.img_flag)).check(matches(isDisplayed()));
         onView(withId(R.id.img_flag)).check(matches(withDrawable(R.drawable.br)));
     }

    @Test
    public void testArFlag__countryNameAr__True(){
        onView(withId(R.id.txt_flag)).perform(typeText("ar"), closeSoftKeyboard());
        onView(withId(R.id.btn_add_flag)).perform(click());
        // Check that the text was changed.
        onView(withId(R.id.img_flag)).check(matches(isDisplayed()));
        onView(withId(R.id.img_flag)).check(matches(withDrawable(R.drawable.ar)));
    }

    @Test
    public void testInvalidCountryCode__invalidCountryCode__True(){
        onView(withId(R.id.txt_flag)).perform(typeText("xpto"), closeSoftKeyboard());
        onView(withId(R.id.btn_add_flag)).perform(click());
        // Check that the text was changed.
        onView(withText("Tip a valid country code")).
                inRoot(withDecorView(not(is(mActivityRule.getActivity().
                        getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
}

class EspressoTestsMatchers {

    public static Matcher<View> withDrawable(final int resourceId) {
        return new DrawableMatcher(resourceId);
    }

    public static Matcher<View> noDrawable() {
        return new DrawableMatcher(-1);
    }


}

class DrawableMatcher extends TypeSafeMatcher<View> {


    private final int expectedId;
    String resourceName;

    public DrawableMatcher(int expectedId) {
        super(View.class);
        this.expectedId = expectedId;
    }

    @Override
    protected boolean matchesSafely(View target) {
        if (!(target instanceof ImageView)){
            return false;
        }
        ImageView imageView = (ImageView) target;
        if (expectedId < 0){
            return imageView.getDrawable() == null;
        }
        Resources resources = target.getContext().getResources();
        Drawable expectedDrawable = resources.getDrawable(expectedId);
        resourceName = resources.getResourceEntryName(expectedId);

        if (expectedDrawable == null) {
            return false;
        }

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        Bitmap otherBitmap = ((BitmapDrawable) expectedDrawable).getBitmap();
        return bitmap.sameAs(otherBitmap);
    }


    @Override
    public void describeTo(Description description) {
        description.appendText("with drawable from resource id: ");
        description.appendValue(expectedId);
        if (resourceName != null) {
            description.appendText("[");
            description.appendText(resourceName);
            description.appendText("]");
        }
    }
}






