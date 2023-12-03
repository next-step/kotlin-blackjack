package blackjack.domain.participant

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 카드를 한 장 뽑을 수 있다." {
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        val card = dealer.drawCard()

        card.number shouldBe CardNumber.THREE
        card.pattern shouldBe CardPattern.HEART
    }
})
