package blackjack.domain.player

import blackjack.domain.card.Hand
import blackjack.domain.card.Score

private const val DEALER_FIRST_OPEN_COUNT = 1
private val DEALER_HIT_MAX_SCORE = Score(16)
private val DEALER_NAME = PlayerName("딜러")

class Dealer(
    callback: AfterHitWhileCallback = AfterHitWhileCallback {},
    hand: Hand = Hand.createEmpty(),
) : Gamer(DEALER_NAME, hand, callback) {

    override fun wantHit(answerProvider: AnswerProvider): Boolean {
        return score <= DEALER_HIT_MAX_SCORE
    }

    override fun firstOpenCardsCount() = DEALER_FIRST_OPEN_COUNT
}
