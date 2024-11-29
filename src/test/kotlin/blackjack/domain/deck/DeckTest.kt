package blackjack.domain.deck

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱은 카드를 한 장씩 반환(드로우)할 수 있다." {
        val deck = DefaultDeckGenerator().generate()
        val size = deck.cards.size
        deck.draw()
        deck.cards.size shouldBe size - 1
    }
})
