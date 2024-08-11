package app.model

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable

@Serializable
data class MdMetadata(
    val id: String = "ID",
    val title: String = "TITLE_MISSING",
    val date: LocalDate = Clock.System.todayIn(TimeZone.UTC),
    val path: String = ""
)
