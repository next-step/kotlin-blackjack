package blackjack

import blackjack.application.BlackjackService
import blackjack.domain.Deck
import blackjack.domain.ShuffledDeck

object ApplicationContext {
    fun deck(): Deck = ShuffledDeck()

    private fun blackjackService(): BlackjackService = BlackjackService()

    fun gameController(): BlackjackController = BlackjackController(blackjackService())
}

fun main() {
    val deck = ApplicationContext.deck()
    ApplicationContext
        .gameController()
        .start(deck)
}
