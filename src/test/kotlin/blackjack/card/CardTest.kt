package blackjack.card


import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "숫자가 적힌 카드는 1에서 10 사이이다." {
        val card = Card(CardNumber.TWO, CardPattern.CLOVER)
        card.number shouldBeIn listOf(
            CardNumber.TWO,
            CardNumber.THREE,
            CardNumber.FOUR,
            CardNumber.FIVE,
            CardNumber.SIX,
            CardNumber.SEVEN,
            CardNumber.EIGHT,
            CardNumber.NINE,
            CardNumber.TEN
        )
    }
    "카드의 문양은 클로버, 다이아, 스페이드, 하트중 하나이다." {
        val card = Card(CardNumber.TWO, CardPattern.CLOVER)
        card.pattern shouldBeIn listOf(CardPattern.CLOVER, CardPattern.DIAMOND, CardPattern.SPADE, CardPattern.HEART)
    }
    "카드는 숫자 이외에도 Jack, Queen, King을 가질 수 있다." {
        val card = Card(CardNumber.JACK, CardPattern.CLOVER)
        card.number shouldBe CardNumber.JACK
        val card2 = Card(CardNumber.QUEEN, CardPattern.CLOVER)
        card2.number shouldBe CardNumber.QUEEN
        val card3 = Card(CardNumber.KING, CardPattern.CLOVER)
        card3.number shouldBe CardNumber.KING
    }
})
