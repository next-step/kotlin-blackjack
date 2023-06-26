package blackjack.domain.gamestate.finished

import blackjack.domain.card.Cards
import blackjack.domain.gamestate.Competition

class Stay(
    val cards: Cards,
) : Finished() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBust().not()) { "버스트 카드로 생성될 수 없다." }
    }

    override fun cards() = cards.values

    override fun isBust() = false

    override fun score() = cards.score()

    override fun compete(gameState: Finished): Competition {
        if (gameState.isBust()) {
            return Competition.WIN
        }
        val score = this.score()
        val otherScore = gameState.score()
        if (score < otherScore) {
            return Competition.LOSE
        }
        if (score > otherScore) {
            return Competition.WIN
        }
        TODO("todo")
    }
}
