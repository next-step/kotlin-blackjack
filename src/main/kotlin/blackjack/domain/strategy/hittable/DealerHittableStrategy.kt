package blackjack.domain.strategy.hittable

object DealerHittableStrategy : HittableStrategy {

    private const val DEALER_ACCEPT_CRITERIA_SCORE = 16

    override fun canHit(score: Int): Boolean = score <= DEALER_ACCEPT_CRITERIA_SCORE
}
