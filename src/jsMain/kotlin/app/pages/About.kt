package app.pages

import app.state.about
import app.state.state
import react.FC

val About = FC {
    InnerHtml.invoke {
        data = state.about
    }
}
