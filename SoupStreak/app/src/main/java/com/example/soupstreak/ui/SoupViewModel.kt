import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.soupstreak.ui.SoupScreen

class SoupViewModel : ViewModel() {
    private val _count = mutableStateOf(0)
    private val _maxCount = mutableStateOf(0)

    val count: State<Int> = _count
    val maxCount: State<Int> = _maxCount

    fun incrementCount() {
        _count.value++
        if (_count.value > _maxCount.value) {
            _maxCount.value = _count.value
        }
    }

    fun resetCount() {
        _count.value = 0
    }

    fun resetMaxCount() {
        _maxCount.value = 0
        resetCount()
    }
}


@Composable
fun MyApp() {
    val viewModel: SoupViewModel = viewModel(factory = CounterViewModelFactory(LocalContext.current))
    SoupScreen(viewModel)
}

class CounterViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SoupViewModel::class.java)) {
            return SoupViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
