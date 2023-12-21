package ru.nsu.ftt.edit_lesson.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.ftt.edit_lesson.presentation.theme.FTTTheme
import ru.nsu.ftt.edit_lesson.presentation.view.CreateLesson
import ru.nsu.ftt.edit_lesson.presentation.view.EditLesson

@AndroidEntryPoint
class EditLessonFragment : Fragment() {
    companion object {
        fun newInstance(isEdit: Boolean): EditLessonFragment {
            return EditLessonFragment().apply {
                arguments = bundleOf(
                    IS_EDIT to isEdit
                )
            }
        }

        private const val IS_EDIT = "IS_EDIT"
    }

    private val viewModel: EditLessonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FTTTheme {
                    if (arguments?.getBoolean(IS_EDIT) == true) {
                        EditLesson()
                    } else {
                        val state = viewModel.state.collectAsState()
                        CreateLesson(
                            Modifier,
                            state.value,
                            viewModel::saveLesson,
                            viewModel::exitFromFragment
                        )
                    }
                }
            }
        }
    }
}