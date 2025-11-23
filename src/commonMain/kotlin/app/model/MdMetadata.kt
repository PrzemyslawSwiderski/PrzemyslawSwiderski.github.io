package app.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Serializable
data class MdMetadata(
    val id: String = "ID",
    val title: String = "TITLE_MISSING",
    val created: LocalDate = Clock.System.todayIn(TimeZone.UTC),
    val readTime: String = "5 mins",
    val path: String = "",
    val location: String = ""
)
