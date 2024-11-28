package blackjack.domain.deck

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 52장의 카드가 없으면 예외를 던진다." {
        val shapes = listOf(CardShape.Heart)
        val numbers = List(10) { CardNumber.Ten }
        shouldThrow<IllegalArgumentException> { FakeDeckGenerator(shapes, numbers).generate() }
    }

    "덱은 카드를 한 장씩 반환(드로우)할 수 있다." {
        val deck = DefaultDeckGenerator().generate()
        val size = deck.cards.size
        deck.cards.size shouldBe size - 1
    }
})
