package blackjack.domain.behavior

import blackjack.domain.card.PlayingCards

class BustState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    override fun resultScore(): Int = ZERO

    companion object {
        private const val ZERO: Int = 0
    }
}
