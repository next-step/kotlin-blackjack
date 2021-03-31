package blackjack.domain.card.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Running(
    override val cards: Cards
) : State {

    override fun draw(card: Card): State {
        cards.add(card)
        if (cards.score == BlackJack.SCORE) {
            return Stay(cards)
        }
        if (cards.score > BlackJack.SCORE) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun isFinished() = false

    override fun profit(money: Money) = throw IllegalStateException("아직 끝난 상태가 아니다")
}
