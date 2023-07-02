package blackjack.domain

import blackjack.enums.Denomination
import blackjack.enums.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    val card = Card(Denomination.KING, Suit.SPADE)
    "카드는 무늬와 끗수로 이루어지며 각 끗수에 해당하는 값을 가져올 수 있다." {
        card.denomination shouldBe Denomination.KING
        card.suit shouldBe Suit.SPADE
        card.value shouldBe 10
    }

    "카드의 이름을 가져올 수 있다." {
        card.name shouldBe "K스페이드"
    }
})
