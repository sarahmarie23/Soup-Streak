import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

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

