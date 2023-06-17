package blackjack.domain.behavior

import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards

class StartState(playingCards: InitPlayingCards) {

    fun hit(card: Card): State = RunningState(playingCards = playingCards)
        .hit(card = card)

    fun stay(): FinishState = FinishState(playingCards = playingCards)

    fun availableTurn(): Boolean = true
}
