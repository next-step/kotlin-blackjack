package blackjack.domain.gamestate

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Bust(
    val cards: Cards,
) : GameState {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust()) { "버스트 아닌 카드로 생성될 수 없다." }
    }

    override fun cards() = cards.values

    override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 draw할 수 없다.")

    override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")

    override fun isBust() = true

    override fun score() = cards.score()
}
