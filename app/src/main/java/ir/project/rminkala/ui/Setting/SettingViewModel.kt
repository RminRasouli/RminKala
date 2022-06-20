package ir.project.rminkala.ui.Setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pourkazemi.mahdi.mymaktabplus.data.localdetabase.data_store.SettingDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.project.rminkala.data.local.data_store.Theme
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val settingDataStore: SettingDataStore
) : ViewModel() {

    fun updateTheme(theme: Theme) {
        viewModelScope.launch {
            settingDataStore.updateTheme(theme)
        }
    }
}