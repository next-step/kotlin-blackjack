package blackjack.domain.state.finish

import blackjack.domain.card.PlayingCards
import blackjack.domain.state.FinishState

class Stay(playingCards: PlayingCards) : FinishState(playingCards = playingCards) {

    override fun resultScore(): Int = playingCards.calculateAceOptimalScore()
}
