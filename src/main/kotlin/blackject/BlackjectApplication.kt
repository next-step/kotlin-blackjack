package blackject

import blackject.controller.BlackjectController
import blackject.model.Rule
import blackject.model.card.CardsDeck

fun main() {
    BlackjectController(Rule, CardsDeck).start()
}
