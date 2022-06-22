package blackjack.dealer

import blackjack.card.Card
import blackjack.card.Deck
import blackjack.player.Player

class Dealer(private val deck: Deck) {
    fun drawTo(count: Int, vararg player: Player) {
        player.map { giveCards(it, count) }
    }

    private fun giveCards(player: Player, count: Int) {
        repeat(count) {
            player.give(deck.draw())
        }
    }

    private fun Player.give(card: Card) {
        addCard(card)
    }
}
