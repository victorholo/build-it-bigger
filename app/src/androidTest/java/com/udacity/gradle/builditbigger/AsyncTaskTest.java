package com.udacity.gradle.builditbigger;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.Loader;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Victor Holotescu on 29-03-2018.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testVerifyJoke() throws InterruptedException {
        assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        JokesAsyncTask jokesTask = new JokesAsyncTask(mActivityTestRule.getActivity()) {
            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                if (result != null){
                    assertTrue(result.length() > 0);
                    latch.countDown();
                }
            }
        };
        jokesTask.execute();
        latch.await();
    }
}

