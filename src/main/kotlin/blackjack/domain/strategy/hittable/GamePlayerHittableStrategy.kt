package blackjack.domain.strategy.hittable

object GamePlayerHittableStrategy : HittableStrategy {

    private const val BLACK_JACK_SCORE = 21

    override fun canHit(score: Int): Boolean = score < BLACK_JACK_SCORE
}
