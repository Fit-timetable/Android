package ru.nsu.fit.timetable.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.view.TimeTableScreen

@AndroidEntryPoint
class TimeTableFragment : Fragment() {

    private val timeTableViewModel: TimetableViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = timeTableViewModel.stateFlow.collectAsState()
                TimeTableScreen(state = state.value, onClickDate = ::onClickDate, onChangeTextWithNumberGroup = ::onChaneNumberGroup,)
            }
        }
    }

    private fun onClickDate(group: String, date: DateUi) {
        timeTableViewModel.processEvent(TimeTableEvent.OnGetScheduleForDayClick(group, date))
    }

    private fun onChaneNumberGroup(group : String) {
        lifecycleScope.launch {
            timeTableViewModel.sharedFlow.emit(group)
        }
    }
}
