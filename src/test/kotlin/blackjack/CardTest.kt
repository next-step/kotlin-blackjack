package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.shouldBe

class CardTest : StringSpec({

    "카드 모양은 Spade, Hart, Diamond, Clover 4개의 모양이 있다" {
        val card = Card(Card.CardShape.Clover, CardNumber.create(3))

        card.cardShape shouldBeIn Card.CardShape.values()
    }

    "카드의 숫자는 1-13까지 부여 받을 수 있다" {
        val card = Card(Card.CardShape.Clover, CardNumber.create(3))

        card.number.value shouldBeInRange (1..13)
    }

    "카드 숫자 중 1은 Ace로 표시한다" {
        val card = Card(Card.CardShape.Clover, CardNumber.Ace())

        card.number.name shouldBe "A"
    }

    "카드 숫자 중 11은 Jack, 12는 Queen, 13은 King 으로 표시한다" {
        val cardJack = Card(Card.CardShape.Clover, CardNumber.Jack())
        val cardQueen = Card(Card.CardShape.Clover, CardNumber.Queen())
        val cardKing = Card(Card.CardShape.Clover, CardNumber.King())

        cardJack.number.name shouldBe "J"
        cardQueen.number.name shouldBe "Q"
        cardKing.number.name shouldBe "K"
    }
})
