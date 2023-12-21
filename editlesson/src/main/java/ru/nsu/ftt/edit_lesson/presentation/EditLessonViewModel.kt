package ru.nsu.ftt.edit_lesson.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.nsu.fit.common.FttRouter
import ru.nsu.fit.common.runCatchingNonCancellation
import ru.nsu.ftt.edit_lesson.data.remote_repository.EditLessonRepository
import ru.nsu.ftt.edit_lesson.presentation.view.EditLessonState
import ru.nsu.ftt.edit_lesson.presentation.view.InputError
import ru.nsu.ftt.edit_lesson.presentation.view.LessonForm
import javax.inject.Inject

@HiltViewModel
class EditLessonViewModel @Inject constructor(
    private val repository: EditLessonRepository,
    private val fttRouter: FttRouter
) : ViewModel() {

    private val _state = MutableStateFlow(EditLessonState())
    val state: StateFlow<EditLessonState>
        get() = _state


    fun saveLesson(lessonForm: LessonForm) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                val error = with(lessonForm) {
                    when {
                        subjectName.isEmpty() -> InputError.LESSON
                        else -> InputError.NO
                    }
                }
                if (error == InputError.NO) {
                    _state.emit(state.value.copy(isLoading = true))
                    repository.createLesson(lessonForm)
                } else {
                    _state.emit(state.value.copy(error = error))
                }
            }.onFailure {
                fttRouter.sendToast("Предмет не сохранен")
            }
        }
    }

    fun exitFromFragment() {
        viewModelScope.launch {
            fttRouter.back()
        }
    }
}