package blackject

import blackject.controller.BlackjectController
import blackject.model.card.CardsDeck

fun main() {
    BlackjectController(CardsDeck).start()
}
