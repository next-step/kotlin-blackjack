package blackjack.domain.behavior

import blackjack.domain.card.PlayingCards

class FinishState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    override fun resultScore(): Int = playingCards.calculateOptimalScore()
}
