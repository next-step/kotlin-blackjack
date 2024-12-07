package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class DeckTest : StringSpec({
    "덱에서 카드를 하나 꺼낸다" {
        val deck = Deck.create()
        val card = deck.draw()

        card shouldNotBe null
    }

    "덱에 카드가 없을 때 카드를 꺼내면 예외 발생한다" {
        val deck = Deck.create()
        repeat(52) { deck.draw() }

        shouldThrow<IllegalStateException> { deck.draw() }
    }
})
