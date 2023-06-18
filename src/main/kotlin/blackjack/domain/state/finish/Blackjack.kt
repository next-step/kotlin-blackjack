package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState

class Blackjack(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = BLACKJACK_SCORE

    companion object {
        private const val BLACKJACK_SCORE: Int = 21
    }
}
