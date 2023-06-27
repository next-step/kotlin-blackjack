package blackjack.domain.gamestate.finished

import blackjack.domain.card.Cards
import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.GameState

class Bust(
    val cards: Cards,
) : Finished() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust()) { "버스트 아닌 카드로 생성될 수 없다." }
    }

    override fun cards() = cards.values

    override fun isBust() = true

    override fun score() = cards.score()

    override fun compete(gameState: GameState): Competition {
        require(gameState.isFinished()) { "게임이 종료되지 않은 상대와 비교할 수 없다."}
        return Competition.LOSE
    }
}
