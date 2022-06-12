package blackjack.fixture

import blackjack.model.CardDistributor
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player

object AlwaysStayDecisionMaker : HitDecisionMaker {
    override fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean = false
}
