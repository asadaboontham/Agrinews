package com.example.asadaboomtham.agrinewsw.Adapter;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcelable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asadaboomtham.agrinewsw.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static android.provider.Settings.Global.getString;

/**
 * Created by asada boomtham on 27/9/2560.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    private List<ListItem> listItems;
    private Context context;


    private ArrayList<ListItem> mArrayList;
    private List<ListItem> mFilteredList;


    public MyAdapter(List<ListItem> arrayList, Context context) {

        this.mArrayList = (ArrayList<ListItem>) arrayList;
        this.context = context;
        this.mFilteredList = arrayList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ListItem listItem = mArrayList.get(position);

        holder.textViewHead.setText(listItem.getHead());
        holder.textViewDes.setText(listItem.getDesc());
        holder.textViewRef.setText(listItem.getRef());


        Picasso.with(context)
                .load(listItem.getImageUrl())
                .into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {

//                //Toast.makeText(context, "clicked" + listItem.getHead(), Toast.LENGTH_LONG).show();
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mFilteredList.get(position).getLink()));
//                context.startActivity(browserIntent);

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder().setShowTitle(true);
                CustomTabsIntent customTabsIntent = builder.build();

                //Open the Custom Tab
                customTabsIntent.launchUrl(context, Uri.parse(mFilteredList.get(position).getLink()));
//                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.colorAccent, null));

//                String url = "https://www.youtube.com/";
//                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder().setShowTitle(true);
//                CustomTabsIntent customTabsIntent = builder.build();
//                customTabsIntent.launchUrl(context, Uri.parse(mFilteredList.get(position).getLink()));
//                builder.addDefaultShareMenuItem();


                // Setting a custom back button

//                // CustomTabsIntent used to launch the URL
//                CustomTabsIntent customTabsIntent = builder.build();
//
//                //Open the Custom Tab
//                customTabsIntent.launchUrl(MainActivity.this, Uri.parse(url));


                // CustomTabsIntent.Builder used to configure CustomTabsIntent.
//                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder().setShowTitle(true);

                // Change the background color of the Toolbar.
//                builder.setToolbarColor(ResourcesCompat.getColor(context.getResources(), R.color.colorPrimary, null));

                // Configure custom enter and exit animations
//                builder.setStartAnimations(MainActivity.this, R.anim.slide_in_right, R.anim.slide_out_left);
//                builder.setExitAnimations(MainActivity.this, R.anim.slide_in_left, R.anim.slide_out_right);

                // Setting a custom back button
//                builder.setCloseButtonIcon(BitmapFactory.decodeResource(
//                        context.getResources(), R.drawable.ic_arrow_back_black_24dp));
//
//                // Hide status bar once the user scrolls down the content
//                builder.enableUrlBarHiding();
//
//                // For adding menu item
//                builder.addMenuItem("Share", getItem());
//
//                // For adding Action button
//                builder.setActionButton(BitmapFactory.decodeResource(context.getResources(),
//                        R.drawable.ic_share), "Share", getItem(), true);

            }

        });
    }

//    private PendingIntent getItem() {
//        Intent shareIntent = new Intent(Intent.ACTION_SEND);
//        shareIntent.setType("text/plain");
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Droidmentor is a site, which contains android tutorials");
//        Log.d(TAG, "setMenuItem: " + mFilteredList);
////        shareIntent.putExtra(Intent.EXTRA_TEXT, (Parcelable) mFilteredList);
//        return PendingIntent.getActivity(context, 0, shareIntent, 0);
//    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    //Search Filter
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {


                    mFilteredList = mArrayList;
                } else {

                    List<ListItem> filteredList = new ArrayList<>();

                    for (ListItem listItem : mArrayList) {

                        if (listItem.getHead().toLowerCase().contains(charString) || listItem.getDesc().toLowerCase().contains(charString) || listItem.getRef().toLowerCase().contains(charString)) {

                            filteredList.add(listItem);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<ListItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewHead;
        public TextView textViewDes;
        public TextView textViewRef;
        public ImageView imageView;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHead = (TextView) itemView.findViewById(R.id.textViewHead);
            textViewDes = (TextView) itemView.findViewById(R.id.textViewDes);
            textViewRef = (TextView) itemView.findViewById(R.id.textViewRef);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }


}
