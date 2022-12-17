package blackjack.domain

import domain.CardDeck
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe

class CardDeckTest : FreeSpec({
    "카드덱은 생성되면서 카드가 초기화된다" {
        val deck = CardDeck()
        deck.shouldNotBeNull()
        deck.cards.size shouldBe 52
    }
})
