package blackjack.domain.game

import blackjack.domain.card.Cards
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers

class Game(
    private val cards: Cards,
    val gamers: Gamers
) {
    init {
        initialize()
    }

    private fun initialize() {
        repeat(INIT_CARD_COUNT) {
            gamers.dealCards(cards)
        }
    }

    fun dealCardToPlayer(gamer: Gamer) {
        val foundGamer = gamers.findGamer(gamer.name)
        foundGamer.addCardToDeck(cards.next())
    }

    companion object {
        const val INIT_CARD_COUNT = 2
        const val WINNING_NUMBER = 21
    }
}
