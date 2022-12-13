package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldNotBeNull

class CardDeckTest : StringSpec({

    "카드 덱에서 랜덤 카드가 반환됩니다" {
        val cardDeck = CardDeck()
        cardDeck.draw().shouldNotBeNull()
    }
})
