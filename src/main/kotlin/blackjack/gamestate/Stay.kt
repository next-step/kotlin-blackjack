package blackjack.gamestate

import blackjack.Card
import blackjack.Cards

class Stay(
    val cards: Cards,
) : GameState {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust().not()) { "버스트 카드로 생성될 수 없다." }
    }
    override fun draw(card: Card) = throw IllegalStateException("종료된 게임은 draw할 수 없다.")

    override fun stay() = throw IllegalStateException("종료된 게임은 stay할 수 없다.")
}
