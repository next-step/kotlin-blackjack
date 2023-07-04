package blackjack.domain.user

import blackjack.domain.BlackJackGame
import blackjack.domain.card.Deck

class Dealer(private val deck: Deck = Deck.create(), name: String = "dealer") : Player(name) {
    fun giveCardTo(player: Player, cardCount: Int = 1) {
        repeat(cardCount) { player.cards.addCard(deck.getNextCard()) }
        player.updateStatus()
    }

    fun giveCardIfPlayerWantHit(player: Player) {
        if (player.wantHit()) {
            giveCardTo(player)
        }
    }

    fun drawCardBySelfIfPointUnder(dealerDrawThresholdPoint: Int): Boolean {
        if (cards.getOptimizedPoint() <= dealerDrawThresholdPoint) {
            giveCardTo(this)
            return true
        }
        return false
    }
}
