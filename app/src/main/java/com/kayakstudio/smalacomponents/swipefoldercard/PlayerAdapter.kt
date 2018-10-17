package com.kayakstudio.smalacomponents.swipefoldercard

import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import com.kayakstudio.smalacomponents.R
import com.kayakstudio.smalacomponents.databinding.AdapterPlayerBinding
import ru.rambler.libs.swipe_layout.SwipeLayout
import kotlin.properties.Delegates


/**
 * adapter of keyValue answer
 */

class PlayerAdapter(private val callBack: CallBackPlayer) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>(),
    AutoUpdatableAdapter {


    var items: List<Player> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AdapterPlayerBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.adapter_player, parent, false)
        val viewHolder = ViewHolder(binding)
        initSwipeLayoutListener(viewHolder)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.player = items[position]
        holder.binding.callBack = callBack
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(var binding: AdapterPlayerBinding) : RecyclerView.ViewHolder(binding.root)

    private fun initSwipeLayoutListener(holder: ViewHolder) {
        holder.binding.adapterSwipeLayout.setOnSwipeListener(object : SwipeLayout.OnSwipeListener {
            override fun onBeginSwipe(swipeLayout: SwipeLayout, moveToRight: Boolean) {}

            override fun onSwipeClampReached(swipeLayout: SwipeLayout, moveToRight: Boolean) {

                if (!moveToRight) {
                    if (holder.binding.player?.active == false) {
                        holder.binding.valid.setBackgroundColor(
                            ContextCompat.getColor(
                                holder.binding.valid.context,
                                R.color.colorOrange
                            )
                        )

                        holder.binding.valid.setImageDrawable(
                            ContextCompat.getDrawable(
                                holder.binding.valid.context,
                                R.drawable.ic_close_black_24dp
                            )
                        )
                        holder.binding.player?.active = true
                        holder.binding.iconValid.visibility = VISIBLE

                    } else{
                        holder.binding.valid.setBackgroundColor(
                            ContextCompat.getColor(
                                holder.binding.valid.context,
                                R.color.colorGreen
                            )
                        )

                        holder.binding.valid.setImageDrawable(
                            ContextCompat.getDrawable(
                                holder.binding.valid.context,
                                R.drawable.ic_check_black_24dp
                            )
                        )
                        holder.binding.player?.active = false
                        holder.binding.iconValid.visibility = GONE

                    }


                    swipeLayout.reset()
                }
            }

            override fun onLeftStickyEdge(swipeLayout: SwipeLayout, moveToRight: Boolean) {}

            override fun onRightStickyEdge(swipeLayout: SwipeLayout, moveToRight: Boolean) {}
        })
    }
}

