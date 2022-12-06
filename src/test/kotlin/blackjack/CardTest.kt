package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드 모양은 Spade, Hart, Diamond, Clover 4개의 모양이 있다" {
        val card = Card()

        println(card.cardShape.name)
        card.cardShape shouldBeIn CARD_SHAPE.values()
    }

    "카드의 숫자는 1-13까지 부여 받을 수 있다" {
        val card = Card()

        card.number.value shouldBeInRange (1..13)
    }

    "카드 숫자 중 1은 Ace로 표시한다" {
        val card = Card(CARD_SHAPE.Clover, CardNumber(1))

        card.number.toName() shouldBe "Ace"
    }

    "카드 숫자 중 11은 Jack, 12는 Queen, 13은 King 으로 표시한다" {
        val cardJack = Card(CARD_SHAPE.Clover, CardNumber(11))
        val cardQueen = Card(CARD_SHAPE.Clover, CardNumber(12))
        val cardKing = Card(CARD_SHAPE.Clover, CardNumber(13))

        cardJack.number.toName() shouldBe "Jack"
        cardQueen.number.toName() shouldBe "Queen"
        cardKing.number.toName() shouldBe "King"
    }
})
