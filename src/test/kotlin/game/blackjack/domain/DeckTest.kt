package game.blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "게임 덱은 총 52장이다." {
        val deck = Deck()

        deck.size shouldBe 52
    }

    "게임 덱의 각 카드는 서로 중복되지 않으면서 52장이다." {
        val deck = Deck()

        deck.cards.toSet().size shouldBe 52
    }
})
