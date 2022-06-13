package blackjack.model.player

val DealerHitDecisionMaker = HitDecisionMaker { player, cardDistributor ->

    val cardCount = player.cardCount
    val score = player.state.finalScore
    (cardCount <= cardDistributor.initialCardCountForEachPlayer && score <= MAX_SCORE_FOR_DEALER_CAN_HIT)
}

const val MAX_SCORE_FOR_DEALER_CAN_HIT = 16
