package blackjack.domain.card.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Running(
    override val cards: Cards
) : State {

    override fun draw(card: Card): State {
        cards.add(card)
        if (cards.score == BlackJack.SCORE) {
            return BlackJack(cards)
        }
        if (cards.score > BlackJack.SCORE) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun isFinished() = false
}
