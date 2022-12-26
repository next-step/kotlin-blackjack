package blackjack

import blackjack.domain.Card
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드의 개수는 52장이다" {
        Card.values().size shouldBe 52
    }
})
