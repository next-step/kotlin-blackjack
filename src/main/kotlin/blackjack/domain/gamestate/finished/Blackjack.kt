package blackjack.domain.gamestate.finished

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.GameState
import blackjack.domain.player.Money

class Blackjack(
    val cards: Cards,
) : Finished() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없습니다." }
        require(cards.isBlackjack()) { "21인 카드만 블랙잭이 될 수 있습니다." }
    }

    override fun cards(): Set<Card> = cards.values

    override fun isBust(): Boolean = false

    override fun score(): Int = cards.score()

    override fun profit(money: Money, gameState: GameState): Int {
        require(gameState.isFinished()) { "게임이 종료되지 않은 상대와 비교할 수 없습니다."}
        if (gameState is Blackjack) {
            return 0
        }
        return money.times(1.5)
    }
}
