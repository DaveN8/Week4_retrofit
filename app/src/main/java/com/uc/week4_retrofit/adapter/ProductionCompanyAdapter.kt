package com.uc.week4_retrofit.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4_retrofit.R
import com.uc.week4_retrofit.helper.Const
import com.uc.week4_retrofit.model.ProductionCompany


class ProductionCompanyAdapter(private val dataSet: List<ProductionCompany>) :
        RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val gambar: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            gambar = view.findViewById(R.id.iv_card_production_company)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_production_company, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.gambar.setImageURI(Uri.parse(Const.IMG_URL + dataSet[position].logo_path))
        Glide.with(viewHolder.itemView)
            .load(Const.IMG_URL + dataSet[position].logo_path)
            .into(viewHolder.gambar)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
