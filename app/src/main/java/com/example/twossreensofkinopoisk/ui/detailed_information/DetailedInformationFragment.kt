package com.example.twossreensofkinopoisk.ui.detailed_information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.ui.MainVM


class DetailedInformationFragment: Fragment() {

    private val viewModel: MainVM by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_detailed_information, container, false)
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                DetailedInformationScreen(
                    filmItem = viewModel.selectedItem.value!!,
                    clickBack = {
                        viewModel.selectItem(null)
                    }
                )
            }
        }
        return view
    }
}