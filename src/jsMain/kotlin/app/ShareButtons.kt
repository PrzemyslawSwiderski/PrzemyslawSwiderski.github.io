package app

import app.utils.asCol
import app.utils.asRow
import app.utils.withClasses
import js.import.importAsync
import react.FC
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.div
import react.useEffect

private const val DATA_ID = "data-id"

val ShareButtons = FC {

    useEffect {
        importAsync<ShareButtonsModule>("share-buttons")
            .then { it.update() }
            .await()
    }

    div {
        asRow()
        withClasses("share-btn")
        a {
            asCol()
            withClasses("btn btn-lg btn-linkedin")
            asDynamic()[DATA_ID] = "in"
            +"Share on LinkedIn"
        }
        a {
            asCol()
            withClasses("btn btn-lg btn-twitter")
            asDynamic()[DATA_ID] = "tw"
            +"Share on Twitter"
        }
        a {
            asCol()
            withClasses("btn btn-lg btn-copy")
            asDynamic()[DATA_ID] = "copy"
            +"Copy Link"
        }
    }
}

external class ShareButtonsModule {
    fun update()
}
