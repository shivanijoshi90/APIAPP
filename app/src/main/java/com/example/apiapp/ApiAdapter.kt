package com.example.apiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apiapp.databinding.ApiTileBinding

class ApiAdapter(val apiList: ApiModel) : RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {

    class ApiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var binding = ApiTileBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.api_tile, parent, false)
        return ApiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apiList.data.size

    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.binding.txtfistname.setText("${apiList.data[position].first_name}")
        holder.binding.txtlastname.setText("${apiList.data[position].last_name}")
        holder.binding.txtemail.setText("${apiList.data[position].email}")

        Glide.with(holder.itemView).load(apiList.data[position].avatar).into(holder.binding.images)

    }
}