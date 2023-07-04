package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "dealer의 point가 16보다 낮을 때, drawCardBySelfIfPointUnder(16)을 호출한 경우, card를 추가로 draw한다" {
        val dealer = Dealer()

        dealer.drawCardBySelfIfPointUnder(16)

        dealer.cards.toList().size shouldBe 1
    }

    "dealer의 point가 16일때, drawCardBySelfIfPointUnder(16)을 호출한 경우, card를 추가로 draw한다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.SIX))
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        dealer.drawCardBySelfIfPointUnder(16)

        dealer.cards.toList().size shouldBe 3
    }

    "dealer의 point가 16보다 높을 때, drawCardBySelfIfPointUnder(16)을 호출한 경우, card를 추가로 draw하지 않는다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.SEVEN))
        dealer.cards.addCard(Card(CardPattern.Club, CardNumber.TEN))

        dealer.drawCardBySelfIfPointUnder(16)

        dealer.cards.toList().size shouldBe 2
    }

    "dealer의 giveCardTo(player, n)이 정상적으로 player에게 card n 장을 전달한다" {
        val dealer = Dealer()
        val player = Player("testUser")

        dealer.giveCardTo(player, 3)

        player.cards.toList().size shouldBe 3
    }

    "dealer의 giveCardIfPlayerWantHit()가 player의 status가 hit인 경우, card를 1장 전달한다" {
        val dealer = Dealer()
        val player = Player("testUser")

        player.chooseHitOrStay(true)
        dealer.giveCardIfPlayerWantHit(player)

        player.cards.toList().size shouldBe 1
    }

    "dealer의 giveCardIfPlayerWantHit()가 player의 status가 stay인 경우, card를 전달하지 않는다" {
        val dealer = Dealer()
        val player = Player("testUser")

        player.chooseHitOrStay(false)
        dealer.giveCardIfPlayerWantHit(player)

        player.cards.toList().size shouldBe 0
    }
})
