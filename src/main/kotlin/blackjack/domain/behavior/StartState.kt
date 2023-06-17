package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards

class StartState(playingCards: InitPlayingCards) : State(playingCards = playingCards) {

    override fun hit(card: Card): State = RunningState(playingCards = playingCards)
        .hit(card = card)

    override fun stay(): FinishState = FinishState(playingCards = playingCards)

    override fun availableTurn(): Boolean = true
}
