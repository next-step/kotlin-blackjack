package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "dealer의 point가 16보다 낮을 때, draw하는 경우, card를 추가로 draw한다" {
        val dealer = Dealer()

        dealer.draw(dealer.deck.getNextCard())

        dealer.cards.toList().size shouldBe 1
    }

    "dealer의 point가 16일때, drawCardBySelfIfPointUnder(16)을 호출한 경우, card를 추가로 draw한다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.SIX))
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        dealer.draw(dealer.deck.getNextCard())

        dealer.cards.toList().size shouldBe 3
    }

    "dealer의 point가 16보다 높을 때, drawCardBySelfIfPointUnder(16)을 호출한 경우, card를 추가로 draw하지 않는다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.SEVEN))
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        dealer.draw(dealer.deck.getNextCard())

        dealer.cards.toList().size shouldBe 2
    }

})
