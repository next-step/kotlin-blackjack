package blackjack.strategy.hittable

import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy
import blackjack.domain.strategy.hittable.HittableStrategy
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GamePlayerHittableStrategyTest {

    @Test
    fun `GamePlayerHittableStrategy의 canHit()조건은 21이하이다`() {
        val gamePlayerHittableStrategy: HittableStrategy = GamePlayerHittableStrategy

        val canHit1 = gamePlayerHittableStrategy.canHit(19)
        Assertions.assertThat(canHit1).isTrue

        val canHit2 = gamePlayerHittableStrategy.canHit(21)
        Assertions.assertThat(canHit2).isFalse
    }
}
