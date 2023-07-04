package blackjack.domain.player

import blackjack.domain.card.Denomination

data class Gamer(
    override val name: String
): Player(name) {
    override fun isDrawable(): Boolean = getDeckScore() < Denomination.WINNING_NUMBER
}
