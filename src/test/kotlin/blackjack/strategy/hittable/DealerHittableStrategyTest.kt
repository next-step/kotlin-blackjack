package blackjack.strategy.hittable

import blackjack.domain.strategy.hittable.DealerHittableStrategy
import blackjack.domain.strategy.hittable.HittableStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerHittableStrategyTest {

    @Test
    fun `DealerHittableStrategy의 canHit()조건은 16이하이다`() {
        val dealerHittableStrategy: HittableStrategy = DealerHittableStrategy

        val canHit1 = dealerHittableStrategy.canHit(16)
        assertThat(canHit1).isTrue

        val canHit2 = dealerHittableStrategy.canHit(17)
        assertThat(canHit2).isFalse
    }
}
