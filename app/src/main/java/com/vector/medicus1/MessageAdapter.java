package com.vector.medicus1;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<ItemMessage> {
    public MessageAdapter(Context context, int resource, List<ItemMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        ItemMessage message = getItem(position);
        messageTextView.setVisibility(messageTextView.VISIBLE);
        authorTextView.setText(message.getName());
        messageTextView.setText(message.getText());

        return convertView;
    }
}