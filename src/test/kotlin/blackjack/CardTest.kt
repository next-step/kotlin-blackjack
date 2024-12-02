package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드가 Ace 여부를 반환한다." {
        val aceCard = createAceCard()
        val basicCard = createBasicCard(CardNumber.FIVE, CardMark.HEART)

        aceCard.isAce() shouldBe true
        basicCard.isAce() shouldBe false
    }
})
