package blackjack.domain.deck

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 조합 가능한 모든 (모양, 숫자) 쌍의 카드를 갖고, 총 52장이다." {
        val deck = Deck()
        deck.cards.size shouldBe Deck.DECK_SIZE
    }
})
