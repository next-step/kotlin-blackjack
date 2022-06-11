package blackjack.model.player

import blackjack.model.CardDistributor

object DealerHitDecisionMaker : HitDecisionMaker {
    override fun shouldHit(player: Player, cardDistributor: CardDistributor): Boolean {
        val cardCount = player.cardCount
        val score = player.state.finalScore
        return (cardCount <= cardDistributor.initialCardCountForEachPlayer && score <= MAX_SCORE_FOR_DEALER_CAN_HIT)
    }

    const val MAX_SCORE_FOR_DEALER_CAN_HIT = 16
}
