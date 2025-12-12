package domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class DealerTest : FreeSpec({

    "딜러 첫번째 라운드 카드 뽑기" {
        val cardDeck = CardDeck()
        val dealer = Dealer()
        repeat(2) {
            dealer.cards.addCard(cardDeck.drawCard())
        }

        dealer.getPublicCardsOnFirstRound().cards().size shouldBe 1
    }
})
