package com.app.infideap.readcontact;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.infideap.readcontact.ChatFragment.OnListFragmentInteractionListener;
import com.app.infideap.readcontact.dummy.DummyContent.DummyItem;
import com.app.infideap.readcontact.entity.Chat;

import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.OwnMessageViewHolder> {

    private final List<Chat> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ChatRecyclerViewAdapter(List<Chat> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public OwnMessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        if(viewType==0)
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_chat, parent, false);
        else
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_chat_left, parent, false);
        return new OwnMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OwnMessageViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
//        String text = mValues.get(position).message.concat("\t\t00:00");
        String text = String.format(
                Locale.getDefault(),
                "%s &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;"
                ,holder.mItem.message
        );
        final SpannableString styledResultText = new SpannableString(text);
        styledResultText.setSpan((new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE)),
                text.length() - 5, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledResultText.setSpan((new ForegroundColorSpan(Color.GRAY)),
                text.length() - 5, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        holder.mIdView.setText(Html.fromHtml(text));
//        holder.mContentView.setText(
//                String.valueOf(mValues.get(position).datetime)
//        );

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }

    @Override
    public int getItemViewType(int position) {

        return mValues.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class OwnMessageViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Chat mItem;

        public OwnMessageViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.textView_message);
            mContentView = (TextView) view.findViewById(R.id.textView_datetime);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }


}