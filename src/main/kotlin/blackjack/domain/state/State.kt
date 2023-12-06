package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score

interface State {
    val cards: Cards
    fun draw(card: Card): State
    fun stand(): State
    fun scoring(): Score
}
