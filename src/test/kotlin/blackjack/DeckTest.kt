package blackjack

import blackjack.domain.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "카드 덱은 52장이다" {
        val deck = Deck.make()
        deck.getCards().size shouldBe 52
    }
})
