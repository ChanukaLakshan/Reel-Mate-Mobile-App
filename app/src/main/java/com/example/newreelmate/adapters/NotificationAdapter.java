package com.example.newreelmate.adapters;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newreelmate.R;
import com.example.newreelmate.database.entities.NotificationEntity;
import java.util.ArrayList;
import java.util.List;
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private List<NotificationEntity> notifications = new ArrayList<>();
    public void setNotifications(List<NotificationEntity> list) {
        this.notifications = list != null ? list : new ArrayList<>();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotificationEntity n = notifications.get(position);
        holder.titleText.setText(n.title);
        holder.messageText.setText(n.message);
        CharSequence relTime = DateUtils.getRelativeTimeSpanString(
                n.createdAt, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        holder.timeText.setText(relTime);
        switch (n.type != null ? n.type : "") {
            case "welcome": holder.iconText.setText("\uD83D\uDC4B"); break;
            case "review":  holder.iconText.setText("\u2B50"); break;
            case "list":    holder.iconText.setText("\uD83D\uDCCB"); break;
            default:        holder.iconText.setText("\uD83C\uDFAC"); break;
        }
        holder.unreadDot.setVisibility(n.isRead ? View.GONE : View.VISIBLE);
    }
    @Override
    public int getItemCount() {
        return notifications.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, messageText, timeText, iconText;
        View unreadDot;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText   = itemView.findViewById(R.id.notificationTitleText);
            messageText = itemView.findViewById(R.id.notificationMessageText);
            timeText    = itemView.findViewById(R.id.notificationTimeText);
            iconText    = itemView.findViewById(R.id.notificationIconText);
            unreadDot   = itemView.findViewById(R.id.unreadDot);
        }
    }
}