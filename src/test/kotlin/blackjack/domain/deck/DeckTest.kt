package blackjack.domain.deck

import blackjack.domain.Deck
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 조합 가능한 모든 (모양, 숫자) 쌍의 카드를 갖고, 총 52장이다." {
        val deck = Deck()
        deck.cards.size shouldBe Deck.DECK_SIZE
    }

    "덱은 카드를 한 장씩 반환(드로우)할 수 있다." {
        val deck = Deck()
        val card = deck.draw()

        deck.cards.size shouldBe Deck.DECK_SIZE - 1
        card shouldBeIn Deck().cards
    }
})
