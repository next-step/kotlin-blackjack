package blackjack.domain.gamestate.running

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.GameState
import blackjack.domain.gamestate.finished.Blackjack
import blackjack.domain.gamestate.finished.Bust
import blackjack.domain.gamestate.finished.Stay

class Hit(
    val cards: Cards,
) : Running() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없습니다." }
        require(cards.isBust().not()) { "버스트 카드로 생성될 수 없습니다." }
    }

    override fun cards() = cards.values

    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        if (cards.isBust()) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun stay(): GameState {
        if (cards.isBlackjack()) {
            return Blackjack(cards)
        }
        return Stay(cards)
    }

    override fun isBust() = false

    override fun score() = cards.score()
}
