package com.jignesh.testapplication.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jignesh.testapplication.R
import com.jignesh.testapplication.domain.models.weather.Weather
import kotlinx.android.synthetic.main.item_weather.view.*

class HomeAdapter(val items : List<Weather>, val context: Context) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    lateinit var mClickListener: ClickListener;

    fun setOnItemClickListener(aClickListener: ClickListener) {
        mClickListener = aClickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, aView: View)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_weather, parent, false), context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tempTxt?.text = context.getString(R.string.temp) + " "+ items.get(position).temp
        holder.humidityTxt?.text = context.getString(R.string.humidity) + " "+ items.get(position).humidity
        holder.pressureTxt?.text = context.getString(R.string.pressure) + " "+ items.get(position).pressure
        holder.tempMaxTxt?.text = context.getString(R.string.temp_max) + " "+ items.get(position).temp_max
        holder.tempMinTxt?.text = context.getString(R.string.temp_min) + " "+ items.get(position).temp_min
    }

    inner class ViewHolder (view: View, context: Context) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        override fun onClick(v: View) {
            mClickListener.onClick(adapterPosition, v)
        }
        init {
            itemView.setOnClickListener(this)
        }

        val tempTxt = view.tempTxt
        val humidityTxt = view.humidityTxt
        val pressureTxt = view.pressureTxt
        val tempMaxTxt = view.tempMaxTxt
        val tempMinTxt = view.tempMinTxt
    }


}

