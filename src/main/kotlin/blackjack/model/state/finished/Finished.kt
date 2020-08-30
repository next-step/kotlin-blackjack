package blackjack.model.state.finished

import blackjack.model.card.Card
import blackjack.model.state.State

abstract class Finished : State {

    override val isFinished: Boolean = true

    override fun addCard(newCard: Card): State = this

    override fun stay(): State = this
}
