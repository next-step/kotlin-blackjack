package blackjack.fixture

import blackjack.model.CardDistributor
import blackjack.model.player.HitDecisionMaker
import blackjack.model.player.Player

object AlwaysHitDecisionMaker : HitDecisionMaker {
    override fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean = true
}
