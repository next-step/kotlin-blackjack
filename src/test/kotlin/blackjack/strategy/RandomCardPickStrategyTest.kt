package blackjack.strategy

import blackjack.domain.strategy.RandomCardPickStrategy
import blackjack.domain.util.RandomNumberGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject

internal class RandomCardPickStrategyTest : BehaviorSpec({
    mockkObject(RandomNumberGenerator)
    Given("랜덤 카드 생성 전략은 ") {
        every { RandomNumberGenerator.generate(any()) } returnsMany(listOf(0, 0))
        When("랜덤으로 전략을 ") {
            val card = RandomCardPickStrategy().pick()
            Then("생성한다.") {
                card.toString() shouldBe "A하트"
            }
        }
    }
})
