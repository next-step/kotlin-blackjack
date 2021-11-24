package blackject

import blackject.controller.BlackjackController
import blackject.model.card.CardsDeck

fun main() {
    BlackjackController(CardsDeck).start()
}
