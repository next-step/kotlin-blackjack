package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "Card 생성 성공" {
        Card(CardShape.CLOVA, CardNumber.JACK) shouldBe Card(CardShape.CLOVA, CardNumber.JACK)
    }
})
