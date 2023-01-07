package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class CardDeckTest : StringSpec({
    "총 52장의 카드를 만들 수 있다." {
        val cardDeck = CardDeck()
        cardDeck.cards.size shouldBe 52
    }
})
