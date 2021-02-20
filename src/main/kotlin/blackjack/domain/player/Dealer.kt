package blackjack.domain.player

import blackjack.domain.deck.Card


class Dealer {
    val player: Player = Player(DEALER_NAME, DEALER_BETTING_MONEY)

    fun enableAdditionalDraw(): Boolean {
        return player.score() < ADDITIONAL_DRAW_CUT
    }

    fun addCard(card: Card) {
        player.addCard(card)
    }

    fun score(): Int {
        return player.score()
    }

    fun isBust(): Boolean {
        return player.isBust()
    }

    fun isBlackJack(): Boolean {
        return player.cards.size == 2 && player.score() == 21
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val ADDITIONAL_DRAW_CUT = 17
        const val DEALER_BETTING_MONEY = 0
    }
}
