package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.CardDeck

interface State {
    val cardDeck: CardDeck

    fun draw(card: Card): State

    fun stay(): State

    fun isFinished(): Boolean

    fun isBust(): Boolean

    fun isBlackJack(): Boolean

    fun isStay(): Boolean

    fun cards(): List<Card>

    fun calculateScore(): Int
}
