package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "뽑힌 카드인지 확인한다" {
        val card = Card(CardNumber.ACE, Pattern.DIAMOND)
        Deck.draw(card)
        Deck.isDrawn(card) shouldBe true
    }
})
