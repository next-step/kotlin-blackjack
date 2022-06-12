package blackjack.model.player

import blackjack.model.CardDistributor

fun interface HitDecisionMaker {
    fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean
}
