package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards

class Bust(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = BUST_SCORE

    companion object {
        private const val BUST_SCORE: Int = 0
    }
}
