package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({
    "덱에 있는 카드는 총 52장이어야 한다." {
        val deck = Deck.create()
        deck.size() shouldBe 52
    }

    "덱에서 카드 한 장을 랜덤으로 뽑을 수 있다." {
        val deck = Deck.create()
        deck.drawCard()
        deck.size() shouldBe 51
    }
})
