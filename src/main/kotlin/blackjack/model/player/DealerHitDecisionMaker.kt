package blackjack.model.player

import blackjack.model.Config

class DealerHitDecisionMaker : HitDecisionMaker {
    override fun shouldHit(player: Player): Boolean {
        val cardCount = player.cardCount
        val score = player.state.finalScore
        return (cardCount <= Config.INITIAL_CARD_COUNT_OF_PLAYER && score <= Config.MAX_SCORE_FOR_DEALER_CAN_HIT)
    }
}
