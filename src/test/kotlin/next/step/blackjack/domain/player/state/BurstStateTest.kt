package next.step.blackjack.domain.player.state

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.player.PlayerCards
import org.junit.jupiter.api.assertThrows

class BurstStateTest : DescribeSpec({

    describe("BurstState") {
        context("canHit") {
            it("항상 false 제공") {
                BurstState.canHit() shouldBe false
            }
        }
        context("next") {
            it("항상 예외 발생") {
                assertThrows<IllegalArgumentException> { BurstState.next(PlayerCards.of(emptyList())) }
            }
        }
    }
})
