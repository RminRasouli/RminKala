package ir.project.rminkala.ui.Search

import com.mancj.materialsearchbar.MaterialSearchBar.OnSearchActionListener


abstract class SearchAction : OnSearchActionListener {
    override fun onSearchStateChanged(enabled: Boolean) {}
    override fun onSearchConfirmed(text: CharSequence) {}
    override fun onButtonClicked(buttonCode: Int) {}
}