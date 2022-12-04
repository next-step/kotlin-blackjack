package blackjack

import blackjack.strategy.RandomCardPickStrategy
import blackjack.util.RandomNumberGenerator
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockkObject

internal class DealerTest : BehaviorSpec({
    mockkObject(RandomNumberGenerator)
    Given("딜러는 ") {
        every { RandomNumberGenerator.generate(any()) } returnsMany(listOf(0, 0))
        When("랜덤으로 카드를 ") {
            val card = Dealer(RandomCardPickStrategy()).pickCard()
            Then("생성한다.") {
                card.toString() shouldBe "A하트"
            }
        }
    }
})
