package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 카드를 한 장 뽑을 수 있다." {
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        val card = dealer.handCard()

        // 초기 2장 스킵
        card.number shouldBe CardNumber.THREE
        card.pattern shouldBe CardPattern.HEART
    }

    "딜러의 점수가 16 이하면 true를 반환한다." {
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)
        val card = Card(CardNumber.THREE, CardPattern.HEART)
        dealer.receiveCard(card) // 11 + 2 + 3

        dealer.canHit().shouldBeTrue()
    }

    "딜러의 점수가 17 이상이면 false를 반환한다." {
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)
        val card1 = Card(CardNumber.THREE, CardPattern.HEART)
        val card2 = Card(CardNumber.FOUR, CardPattern.HEART)
        dealer.receiveCard(card1) // 11 + 2 + 3
        dealer.receiveCard(card2) // 11 + 2 + 3 + 4

        dealer.canHit().shouldBeFalse()
    }
})
