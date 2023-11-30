package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DeckTest : StringSpec({

    "카드를 한 장 뽑을 수 있다." {
        // given
        val deck = DeckHelper.createMockDeck()

        // when
        val card = deck.draw()

        // then
        card.number shouldBe CardNumber.ACE
        card.pattern shouldBe CardPattern.HEART
    }
})
