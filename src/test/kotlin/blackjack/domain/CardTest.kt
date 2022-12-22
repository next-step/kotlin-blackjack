package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드를 발급한다" {
        val card = Card(CardNumber.ACE, Pattern.CLOVER)
        card.cardNumber.value shouldBe 11
        card.cardNumber.display shouldBe "A"
        card.pattern.name shouldBe "CLOVER"
    }
})
