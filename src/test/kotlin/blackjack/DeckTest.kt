package blackjack

import blackjack.domain.Deck
import blackjack.domain.RandomCardStrategy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "카드 덱은 52장이다" {
        val deck = Deck(RandomCardStrategy())
        deck.getCards().size shouldBe 52
    }
})
