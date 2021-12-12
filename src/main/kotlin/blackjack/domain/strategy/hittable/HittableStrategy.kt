package blackjack.domain.strategy.hittable

interface HittableStrategy {
    fun canHit(score: Int): Boolean
}
