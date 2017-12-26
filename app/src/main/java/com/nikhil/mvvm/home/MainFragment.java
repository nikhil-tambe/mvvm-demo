package com.nikhil.mvvm.home;

import android.app.Activity;
import android.app.ActivityOptions;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.transition.Fade;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nikhil.mvvm.R;
import com.nikhil.mvvm.add.AddTaskActivity;
import com.nikhil.mvvm.app.AppClass;
import com.nikhil.mvvm.data.ListItem;
import com.nikhil.mvvm.details.DetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.nikhil.mvvm.app.Constants.Intents.EXTRA_ITEM_ID;

/**
 * Created by nikhil on 24/12/17.
 */

public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";
    private List<ListItem> listItems;
    private CustomAdapter adapter;
    private LayoutInflater layoutInflater;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    ViewModelProvider.Factory viewFactory;

    MainViewModel mainViewModel;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((AppClass) getActivity().getApplication())
                .getAppComponent()
                .inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = ViewModelProviders.of(this, viewFactory).get(MainViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, v);
        layoutInflater = getActivity().getLayoutInflater();

        //Toast.makeText(getActivity(), "Created", Toast.LENGTH_SHORT).show();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        mainViewModel.getListOfData().observe(this, new Observer<List<ListItem>>() {
            @Override
            public void onChanged(@Nullable List<ListItem> listItems) {
                /*if (MainFragment.this.listItems == null){
                    setListData(listItems);
                }*/
                setListData(listItems);
            }
        });
    }

    @OnClick(R.id.floatingActionButton)
    public void fabClicked(){
        startActivity(new Intent(getActivity(), AddTaskActivity.class));
    }

    /*private void setListData(List<ListItem> listItems) {
        this.listItems = listItems;
//        Log.d(TAG, "setListData: ===== DB Started =====");
//        for (ListItem item : listItems){
//            Log.d(TAG, "setListData: " + item.getDateTime() + ": " + item.getMessage());
//        }
//        Log.d(TAG, "setListData: ===== Ended =====");Ended
    }*/

    public void setListData(List<ListItem> listItems) {
        this.listItems = listItems;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                        getActivity(),
                        R.drawable.divider_white
                )
        );

        recyclerView.addItemDecoration(
                itemDecoration
        );


        /*ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);*/
    }


    /*-------------------- RecyclerView Boilerplate ----------------------*/

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {//6

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_data, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            //11. and now the ViewHolder data
            ListItem currentItem = listItems.get(position);

            holder.coloredCircle.setImageResource(currentItem.getColorResource());


            holder.message.setText(
                    currentItem.getMessage()
            );

            holder.dateAndTime.setText(
                    currentItem.getDateTime()
            );

            holder.loading.setVisibility(View.INVISIBLE);
        }

        @Override
        public int getItemCount() {
            // 12. Returning 0 here will tell our Adapter not to make any Items. Let's fix that.
            return listItems.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            //10. now that we've made our layouts, let's bind them
            private CircleImageView coloredCircle;
            private TextView dateAndTime;
            private TextView message;
            private ViewGroup container;
            private ProgressBar loading;

            public CustomViewHolder(View itemView) {
                super(itemView);
                this.coloredCircle = (CircleImageView) itemView.findViewById(R.id.imv_list_item_circle);
                this.dateAndTime = (TextView) itemView.findViewById(R.id.lbl_date_and_time);
                this.message = (TextView) itemView.findViewById(R.id.lbl_message);
                this.loading = (ProgressBar) itemView.findViewById(R.id.pro_item_data);

                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);
                /*
                We can pass "this" as an Argument, because "this", which refers to the Current
                Instance of type CustomViewHolder currently conforms to (implements) the
                View.OnClickListener interface. I have a Video on my channel which goes into
                Interfaces with Detailed Examples.

                Search "Android WTF: Java Interfaces by Example"
                 */
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //getAdapterPosition() get's an Integer based on which the position of the current
                //ViewHolder (this) in the Adapter. This is how we get the correct Data.
                ListItem listItem = listItems.get(
                        this.getAdapterPosition()
                );

                startDetailActivity(listItem.getDateTime(), v);

            }
        }

    }

    public void startDetailActivity(String itemId, View viewRoot) {
        Activity container = getActivity();
        Intent i = new Intent(container, DetailsActivity.class);
        i.putExtra(EXTRA_ITEM_ID, itemId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            container.getWindow().setEnterTransition(new Fade(Fade.IN));
            container.getWindow().setEnterTransition(new Fade(Fade.OUT));

            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(container,
                            new Pair<>(viewRoot.findViewById(R.id.imv_list_item_circle),
                                    getString(R.string.transition_drawable)),
                            new Pair<>(viewRoot.findViewById(R.id.lbl_message),
                                    getString(R.string.transition_message)),
                            new Pair<>(viewRoot.findViewById(R.id.lbl_date_and_time),
                                    getString(R.string.transition_time_and_date)));

            startActivity(i, options.toBundle());

        } else {
            startActivity(i);
        }
    }


    private void deleteTask(ListItem item){
        mainViewModel.deleteTask(item);
    }
}
