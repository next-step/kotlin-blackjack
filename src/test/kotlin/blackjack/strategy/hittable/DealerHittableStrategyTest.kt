package blackjack.strategy.hittable

import blackjack.domain.strategy.hittable.DealerHittableStrategy
import blackjack.domain.strategy.hittable.HittableStrategy
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerHittableStrategyTest {

    private lateinit var dealerHittableStrategy: HittableStrategy

    @BeforeEach
    internal fun setUp() {
        dealerHittableStrategy = DealerHittableStrategy
    }

    @Test
    fun `DealerHittableStrategy의 canHit()은 16이하이면 참이다`() {
        val canHit1 = dealerHittableStrategy.canHit(16)
        assertThat(canHit1).isTrue
    }

    @Test
    fun `DealerHittableStrategy의 canHit()은 16초과면 거짓이다`() {
        val canHit1 = dealerHittableStrategy.canHit(17)
        assertThat(canHit1).isFalse
    }
}
