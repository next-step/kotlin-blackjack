package blackjack.model.player

import blackjack.model.CardDistributor

val DealerHitDecisionMaker = HitDecisionMaker { player, _ ->

    val cardCount = player.cardCount
    val score = player.state.finalScore
    (cardCount <= CardDistributor.INITIAL_CARD_COUNT_FOR_EACH_PLAYER && score <= MAX_SCORE_FOR_DEALER_CAN_HIT)
}

const val MAX_SCORE_FOR_DEALER_CAN_HIT = 16
