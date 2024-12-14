package blackjack

import blackjack.domain.Card
import blackjack.domain.CardMark
import blackjack.domain.CardNumber
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드가 Ace 여부를 반환한다." {
        val aceCard = createAceCard()
        val basicCard = createBasicCard(CardNumber.FIVE, CardMark.HEART)

        aceCard.isAce() shouldBe true
        basicCard.isAce() shouldBe false
    }

    "카드는 공개 여부 상태를 가지고 있다." {
        val faceUpCard = Card(CardNumber.TEN, CardMark.CLOVER)
        val faceDownCard = Card(CardNumber.TEN, CardMark.CLOVER, false)

        faceUpCard.isFaceUp shouldBe true
        faceDownCard.isFaceUp shouldBe false
    }
})
