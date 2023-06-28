package blackjack.domain.state

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class WaitTest : StringSpec({

    "카드 목록이 비어있다" {
        Wait().cards.value.isEmpty() shouldBe true
    }
})
