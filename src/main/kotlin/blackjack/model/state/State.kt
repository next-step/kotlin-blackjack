package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.CardDeck

interface State {
    val cardDeck: CardDeck

    fun draw(card: Card): State

    fun isFinished(): Boolean

    fun cards(): CardDeck
}
