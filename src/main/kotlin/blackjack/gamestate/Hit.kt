package blackjack.gamestate

import blackjack.Card
import blackjack.Cards

class Hit(
    val cards: Cards,
) : GameState {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust().not()) { "버스트 카드로 생성될 수 없다." }
    }

    override fun draw(card: Card): GameState {
        val cards = this.cards.addCard(card)
        if (cards.isBust()) {
            return Bust(cards)
        }
        return Hit(cards)
    }

    override fun stay() = Stay(cards)
}
