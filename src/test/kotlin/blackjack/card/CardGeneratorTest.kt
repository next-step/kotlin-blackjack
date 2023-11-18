package blackjack.card

import blackjack.domain.Card
import blackjack.domain.CardGenerator
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import blackjack.entity.Cards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CardGeneratorTest : StringSpec({
    val cardDeque = ArrayDeque<Card>(
        listOf(
            Card(CardNumber.J, CardShape.CLOVER),
            Card(CardNumber.TWO, CardShape.HEART),
            Card(CardNumber.THREE, CardShape.DIAMOND),
            Card(CardNumber.TEN, CardShape.SPADE),
        )
    )
    val deque = Cards(cardDeque)
    "인자로 전달받은 수만큼 카드의 개수를 생성한다" {
        val input = 3
        val expected = 3
        CardGenerator.generateCard(input, deque).size shouldBe expected
    }

    "생성된 카드 안의 숫자, shape는 CardNumber, CardShape의 타입인가" {
        val input = 1
        val cards = CardGenerator.generateCard(input, deque)
        cards.forEach { card ->
            card.shape.shouldBeInstanceOf<CardShape>()
            card.number.shouldBeInstanceOf<CardNumber>()
        }
    }
})
