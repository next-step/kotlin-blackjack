package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class HandTest : StringSpec({
    "손패는 카드를 추가할 수 있다" {
        val sut = Hand()

        sut.add(Card(CardNumber.KING, Suit.SPADES))

        sut.cards.size shouldBe 1
        sut.cards[0] shouldBe Card(CardNumber.KING, Suit.SPADES)
    }
})
