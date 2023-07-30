package blackjack.domain

val SPADES_ACE = Card.of(CardRank.ACE, CardSuit.SPADE)
val SPADES_TWO = Card.of(CardRank.TWO, CardSuit.SPADE)
val SPADES_TEN = Card.of(CardRank.TEN, CardSuit.SPADE)

val BLACKJACK_SCORE = Score(21)
val BURST_SCORE = Score(22)
val TWO_SCORE = Score(2)
val TEN_SCORE = Score(10)
val SEVENTEEN_SCORE = Score(17)

fun createChallengerWithTwelveScore(): Challenger {
    return Challenger("A").apply { initializeHands(SPADES_TWO, SPADES_TEN) }
}

fun createDealerWithTwelveScore(): Dealer {
    return Dealer().apply { initializeHands(SPADES_TWO, SPADES_TEN) }
}
