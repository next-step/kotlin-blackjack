package blackjack.domain.game

import blackjack.domain.card.Cards
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Gamers

class Game(
    private val cards: Cards,
    val dealer: Dealer,
    val gamers: Gamers
) {
    init {
        initialize()
    }

    private fun initialize() {
        repeat(INIT_CARD_COUNT) {
            dealer.addCardToDeck(cards.next())
            gamers.drawCards(cards)
        }
    }

    fun drawCardToDealer() {
        dealer.addCardToDeck(cards.next())
    }

    fun drawCardToPlayer(gamer: Gamer) {
        val foundGamer = gamers.findGamer(gamer.name)
        foundGamer.addCardToDeck(cards.next())
    }

    companion object {
        const val INIT_CARD_COUNT = 2
    }
}
