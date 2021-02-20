package blackjack.domain.player

import blackjack.domain.deck.Card


class Dealer {
    val player: Player = Player(DEALER_NAME)

    fun enableAdditionalDraw(): Boolean {
        return player.score() < ADDITIONAL_DRAW_CUT
    }

    fun addCard(card: Card) {
        player.addCard(card)
    }

    fun score(): Int {
        return player.score()
    }

    fun isBurst(): Boolean {
        return player.isBurst()
    }

    companion object {
        const val DEALER_NAME = "딜러"
        const val ADDITIONAL_DRAW_CUT = 17
    }
}
