package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "숫자가 적힌 카드는 1에서 10 사이이다." {
        val card = Card(2)
        card.number shouldBeInRange 1..10
    }

    "숫자가 1에서 10 사이가 아니면 IllegalArgumentException이 발생한다." {
        shouldThrow<IllegalArgumentException> {
            Card(0)
            Card(11)
        }.message shouldBe "숫자는 1에서 10 사이여야 합니다."
    }
})
