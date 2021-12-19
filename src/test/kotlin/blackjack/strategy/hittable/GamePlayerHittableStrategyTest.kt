package blackjack.strategy.hittable

import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy
import blackjack.domain.strategy.hittable.HittableStrategy
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GamePlayerHittableStrategyTest {

    private lateinit var gamePlayerHittableStrategy: HittableStrategy

    @BeforeEach
    internal fun setUp() {
        gamePlayerHittableStrategy = GamePlayerHittableStrategy
    }

    @Test
    fun `GamePlayerHittableStrategy의 canHit()는 21미만이면 참이다`() {
        val canHit1 = gamePlayerHittableStrategy.canHit(19)
        Assertions.assertThat(canHit1).isTrue
    }

    @Test
    fun `GamePlayerHittableStrategy의 canHit()는 21이상이면 거짓이다`() {
        val canHit1 = gamePlayerHittableStrategy.canHit(21)
        Assertions.assertThat(canHit1).isFalse
    }
}
