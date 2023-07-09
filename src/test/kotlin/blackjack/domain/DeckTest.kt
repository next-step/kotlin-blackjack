package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "덱 생성시 카드의 총 개수는 52개이다." {
        val deck = Deck.create()
        deck.cards.list.size shouldBe 52
    }
})
