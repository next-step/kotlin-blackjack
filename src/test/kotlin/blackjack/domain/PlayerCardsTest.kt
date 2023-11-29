package blackjack.domain

import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerCardsTest : StringSpec({

    "카드를 한 장 추가할 수 있다." {
        // given
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()
        val playerCards = PlayerCards()

        // when
        playerCards.add(card)

        // then
        playerCards.values.size shouldBe 1
        playerCards.values shouldBe listOf(card)
    }
})
