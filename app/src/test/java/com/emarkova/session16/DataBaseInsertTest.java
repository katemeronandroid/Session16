package com.emarkova.session16;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class DataBaseInsertTest {

    private DBManager manager;

    @Before
    public void setup() {
        Context context = org.robolectric.RuntimeEnvironment.application;
        manager = new DBManager(context);
    }

    @Test
    public void testInsert() {
        manager.addRow("10");
        assertEquals("10", manager.getRow("10"));
    }
}
