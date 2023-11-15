package blackjack.card

import blackjack.domain.CardGenerator
import blackjack.entity.CardNumber
import blackjack.entity.CardShape
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CardGeneratorTest : StringSpec({
    "인자로 전달받은 수만큼 카드의 개수를 생성한다" {
        val input = 3
        val expected = 3
        CardGenerator.generateCard(input).size shouldBe expected
    }

    "생성된 카드 안의 숫자, shape는 CardNumber, CardShape의 타입인가" {
        val input = 1
        val cards = CardGenerator.generateCard(input)
        cards.forEach { card ->
            card.shape.shouldBeInstanceOf<CardShape>()
            card.number.shouldBeInstanceOf<CardNumber>()
        }
    }
})
