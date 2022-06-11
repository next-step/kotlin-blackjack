package blackjack.model.player

import blackjack.model.CardDistributor

interface HitDecisionMaker {
    fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean
}
