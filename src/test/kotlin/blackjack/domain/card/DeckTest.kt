package blackjack.domain.card

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "카드 뭉치의 크기는 초기에 52장이다." {
        val deck = Deck.makeDeck()
        deck.cards.size shouldBe 52
    }
})
