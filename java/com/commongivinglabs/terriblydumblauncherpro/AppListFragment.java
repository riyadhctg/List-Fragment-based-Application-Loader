package com.commongivinglabs.terriblydumblauncherpro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;


public class AppListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<ArrayList<AppModel>> {
    AppListAdapter mAdapter;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText("No Applications");

        mAdapter = new AppListAdapter(getActivity());
        setListAdapter(mAdapter);

        // till the data is loaded display a spinner
        setListShown(false);

        // create the loader to load the apps list in background
        getLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<ArrayList<AppModel>> onCreateLoader(int id, Bundle bundle) {
        return new AppsLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<AppModel>> loader, ArrayList<AppModel> apps) {
        mAdapter.setData(apps);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<AppModel>> loader) {
        mAdapter.setData(null);
    }

    public void onListItemClick(ListView g, View v, int position, long id) {
        AppModel app = (AppModel) getListAdapter().getItem(position);
        if (app != null) {
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(app.getApplicationPackageName());

            if (intent != null) {

                ((MainActivity) getActivity()).selectedAppIntent = intent;

                LinearLayout layone= (LinearLayout) getActivity().findViewById(R.id.map_layout);

                ImageButton imageButton = (ImageButton) getActivity().findViewById(R.id.custom_btn);
                imageButton.setImageDrawable(app.getIcon());
                imageButton.setVisibility(View.VISIBLE);

                ImageButton addCustomButton = (ImageButton) getActivity().findViewById(R.id.add_button);
                addCustomButton.setVisibility(View.GONE);


            }
        }
        LinearLayout layone= (LinearLayout) getActivity().findViewById(R.id.map_layout);// change id here
        layone.setVisibility(View.GONE);
    }



}
