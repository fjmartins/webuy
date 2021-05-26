package com.fjmartins.webuy.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fjmartins.webuy.R
import com.fjmartins.webuy.model.Listing
import java.text.DecimalFormat

class MainFragmentAdapter(
    private val listener: OnClickListener,
    private val listings: List<Listing>
) :
    RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val brand_name: TextView = view.findViewById(R.id.brand_name)
        val liked: ImageView = view.findViewById(R.id.liked)
        val price: TextView = view.findViewById(R.id.price)
        val sold_out: View = view.findViewById(R.id.sold_out_view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.webuy_listing, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val listing = listings[position]

        Glide.with(viewHolder.itemView.context)
            .load(listing.img_url)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(viewHolder.image)

        viewHolder.brand_name.text = listing.brand_name
        viewHolder.price.text = viewHolder.itemView.context.getString(R.string.price).format(
            DecimalFormat("#,###,###").format(listing.price)
        )

        viewHolder.sold_out.visibility = if (listing.t_status == 0) {
            View.VISIBLE
        } else {
            View.GONE
        }

        viewHolder.liked.setBackgroundResource(
            if (listing.liked) {
                R.drawable.ic_like_on
            } else R.drawable.ic_like_off
        )

        viewHolder.itemView.setOnClickListener {
            listener.onClick(position, listing)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = listings.size
}
