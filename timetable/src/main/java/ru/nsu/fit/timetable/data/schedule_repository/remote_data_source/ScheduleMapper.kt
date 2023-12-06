package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source

import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models.LessonDateRemote
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models.LessonRemote
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models.PlaceRemote
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models.WeekScheduleRemote
import ru.nsu.fit.timetable.domain.models.Lesson
import ru.nsu.fit.timetable.domain.models.LessonDate
import ru.nsu.fit.timetable.domain.models.LessonParity
import ru.nsu.fit.timetable.domain.models.Place
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import ru.nsu.fit.timetable.domain.models.lesson
import ru.nsu.fit.timetable.domain.models.lessonDate
import ru.nsu.fit.timetable.domain.models.place
import ru.nsu.fit.timetable.domain.models.weekSchedule
import java.sql.Time

fun PlaceRemote.mapToPlace(): Place {
    return place {
        this.room = this@mapToPlace.room
        this.remoteLink = this@mapToPlace.meetLink ?: ""
    }
}

fun LessonRemote.mapToLesson(): Lesson {
    return lesson {
        this.id = this@mapToLesson.id
        this.parity = this@mapToLesson.parity ?: LessonParity.ALWAYS
        this.place = this@mapToLesson.place?.mapToPlace() ?: place {  }
        this.startTime = this@mapToLesson.startTime
//        this.finishTime
        this.subject = this@mapToLesson.subject ?: ""
        this.teacher = this@mapToLesson.teacher ?: ""
        this.lessonType = this@mapToLesson.type
    }
}

fun WeekScheduleRemote.mapToWeekSchedule(): WeekSchedule {
    return weekSchedule {
        this.monday = this@mapToWeekSchedule.monday.lessons.map { it.mapToLesson() }
        this.tuesday = this@mapToWeekSchedule.tuesday.lessons.map { it.mapToLesson() }
        this.wednesday = this@mapToWeekSchedule.wednesday.lessons.map { it.mapToLesson() }
        this.thursday = this@mapToWeekSchedule.thursday.lessons.map { it.mapToLesson() }
        this.friday = this@mapToWeekSchedule.friday.lessons.map { it.mapToLesson() }
        this.saturday = this@mapToWeekSchedule.saturday.lessons.map { it.mapToLesson() }
    }
}

fun LessonDateRemote.mapToLessonDate(): LessonDate {
    return lessonDate {
        this.weekDay = this@mapToLessonDate.weekDay
        this.startTime = this@mapToLessonDate.startTime.valueOfOrDefault { Time.valueOf("00:00") }
    }
}

fun String.valueOfOrDefault(block: (Throwable) -> Time): Time {
    return kotlin.runCatching { Time.valueOf(this) }.getOrElse(block)
}