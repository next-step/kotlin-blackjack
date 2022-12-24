package blackjack.domain.card

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    test("모든 카드는 총 56장이다.") {
        val expectedCount = CardNumber.values().size * CardShape.values().size

        expectedCount shouldBe 56
        Cards.ALL shouldHaveSize expectedCount
        Cards.ALL.toSet() shouldHaveSize expectedCount
    }
})
