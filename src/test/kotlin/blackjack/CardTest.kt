package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({
    "카드는 스페이드, 다이아몬드, 하트, 클로버 무늬로 구분된다." {
        forAll(
            row(Suit.SPADE, Suit.SPADE),
            row(Suit.DIAMOND, Suit.DIAMOND),
            row(Suit.CLUB, Suit.CLUB),
            row(Suit.HEART, Suit.HEART),
        ) { inputSuit, expectedSuit ->

            val card = Card(inputSuit)

            card.suit shouldBe expectedSuit
        }
    }
})
