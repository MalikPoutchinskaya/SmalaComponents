package com.kayakstudio.smalacomponents

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.kayakstudio.smalacomponents.swipefoldercard.CallBackPlayer
import com.kayakstudio.smalacomponents.swipefoldercard.PlayerAdapter
import com.kayakstudio.smalacomponents.swipefoldercard.PlayerGenerator.generatePlayers
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import com.ramotion.foldingcell.FoldingCell
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), CallBackPlayer {
    private var isLinear = true

    override fun expend(foldingCell: FoldingCell) {
        foldingCell.toggle(false)
    }


    private val mAdapter: PlayerAdapter =
        PlayerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecycler()
        initFab()
    }

    private fun initRecycler() {
        main_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        main_recyclerView.adapter = mAdapter
        mAdapter.items = generatePlayers()
    }

    private fun initFab() {
        speedDial.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_custom_color, R.drawable.ic_check_black_24dp)
                .setFabImageTintColor(ResourcesCompat.getColor(resources, R.color.colorOrange, theme))
                .setLabel("Search")
                .setLabelColor(Color.WHITE)
                .setLabelBackgroundColor(
                    ResourcesCompat.getColor(
                        resources, R.color.colorPrimary,
                        theme
                    )
                )
                .create()
        )



        speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_custom_color -> {
                    if (isLinear) {

                        main_recyclerView.layoutManager =
                                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                        isLinear = false
                    } else {
                        main_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        isLinear = true
                    }

                    Toast.makeText(this, "Validate", LENGTH_LONG).show()
                    return@OnActionSelectedListener true // false will close it without animation
                }
            }
            true // To keep the Speed Dial open
        })
    }
}
