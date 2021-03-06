package com.zdominguez.sdksandbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewViewStubs extends AppCompatActivity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<String> mEntries = Arrays.asList(Cheeses.sCheeseStrings);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_viewstubs);
        ButterKnife.bind(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(new RecyclerViewDemoAdapter());
    }

    class RecyclerViewDemoAdapter extends RecyclerView.Adapter<ViewStubbyHolder> {

        @Override
        public ViewStubbyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewStubbyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_viewstubs, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewStubbyHolder holder, int position) {
            String cheeseName = mEntries.get(position);

            // For even positions, show bottom
            TextView textView;
            boolean isEven = position % 2 == 0;
            if(isEven) {
                holder.mTopStub.setVisibility(View.GONE);
                holder.mBottomStub.setVisibility(View.VISIBLE);
                textView = ButterKnife.findById(holder.mBottomStub, R.id.text);
                textView = holder.mTextViewBottom;
            } else {
                holder.mTopStub.setVisibility(View.VISIBLE);
                holder.mBottomStub.setVisibility(View.GONE);
                textView = ButterKnife.findById(holder.mTopStub, R.id.text);
                textView = holder.mTextView;
            }
            textView.setText(position + ": " + cheeseName);
        }

        @Override
        public int getItemCount() {
            return mEntries.size();
        }
    }

    class ViewStubbyHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.stub_bottom)
        ViewGroup mBottomStub;

        @Bind(R.id.stub_top)
        ViewGroup mTopStub;

        @Bind(R.id.text)
        TextView mTextView;

        @Bind(R.id.bottom_text)
        TextView mTextViewBottom;

        public ViewStubbyHolder(View view) {
            super(view);
            ButterKnife.bind(this, this.itemView);
        }
    }

}
