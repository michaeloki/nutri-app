package com.inspirati.nutriapp;

import android.content.res.Resources;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.recyclerview.widget.RecyclerView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class MainActivityTest {
    //Field mMainActivityViewModel of type MainActivityViewModel - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    RecyclerView mRecyclerView;
    //Field mAdapter of type CustomAdapter - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    SearchView mSearchView;
    @Mock
    AppCompatDelegate mDelegate;
    @Mock
    Resources mResources;
    @Mock
    FragmentController mFragments;
    @Mock
    LifecycleRegistry mFragmentLifecycleRegistry;
    @Mock
    SparseArrayCompat<String> mPendingFragmentActivityResults;
    @Mock
    LifecycleRegistry mLifecycleRegistry;
    //Field mSavedStateRegistryController of type SavedStateRegistryController - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    ViewModelStore mViewModelStore;
    @Mock
    ViewModelProvider.Factory mDefaultFactory;
    //Field mOnBackPressedDispatcher of type OnBackPressedDispatcher - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    SimpleArrayMap<Class<? extends ComponentActivity.ExtraData>, ComponentActivity.ExtraData> mExtraDataMap;
//
    @InjectMocks
    MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        mainActivity.onCreate(null);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme