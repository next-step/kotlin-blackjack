package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Hands

interface State {
    val hands: Hands
    fun draw(card: Card): State
    fun stay(): State
    fun getCards(): Cards = Cards(hands.cards)
}
