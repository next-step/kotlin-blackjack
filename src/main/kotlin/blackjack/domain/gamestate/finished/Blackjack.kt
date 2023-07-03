package blackjack.domain.gamestate.finished

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.GameState

class Blackjack(
    val cards: Cards,
) : Finished() {
    init {
        require(cards.isInitialHand().not()) { "2장 미만의 카드로 생성될 수 없다." }
        require(cards.isBlackjack()) { "21인 카드만 블랙잭이 될 수 있다." }
    }

    override fun cards(): Set<Card> = cards.values

    override fun isBust(): Boolean = false

    override fun score(): Int {
        TODO("Not yet implemented")
    }

    override fun compete(gameState: GameState): Competition {
        TODO("Not yet implemented")
    }

    companion object {

    }
}
