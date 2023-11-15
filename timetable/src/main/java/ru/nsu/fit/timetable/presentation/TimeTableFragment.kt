package ru.nsu.fit.timetable.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class TimeTableFragment : Fragment() {

    private val timeTableViewModel by activityViewModels<TimetableViewModel> {
        TimetableViewModel.ViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val state = timeTableViewModel.stateFlow.collectAsState()
                TimeTableScreen(state = state.value)
                timeTableViewModel.processEvent(TimeTableEvent.OnGetScheduleForDayClick("20207", "" , " "))
            }
        }
    }
}
