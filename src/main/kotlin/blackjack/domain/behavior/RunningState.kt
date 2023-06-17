package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards

class RunningState(playingCards: PlayingCards) : State(playingCards = playingCards) {

    override fun hit(card: Card): State {
        playingCards.add(card = card)

        return when {
            playingCards.isBust() -> BustState(playingCards = playingCards)
            else -> this
        }
    }

    override fun stay(): FinishState = FinishState(playingCards = playingCards)

    override fun availableTurn(): Boolean = true
}
