package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드를 생성한다." {
        val card = Card()
        card.createCard()
        card.cardList.size shouldBe 52
    }
})