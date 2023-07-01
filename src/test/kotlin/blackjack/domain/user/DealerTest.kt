package blackjack.domain.user

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "dealer의 giveCardTo(player, n)이 정상적으로 player에게 card n 장을 전달한다" {
        val dealer = Dealer()
        val player = Player("testUser")

        dealer.giveCardTo(player, 3)

        player.getCards().size shouldBe 3
    }

    "dealer의 giveCardIfPlayerWantHit()가 player의 status가 hit인 경우, card를 1장 전달한다" {
        val dealer = Dealer()
        val player = Player("testUser")

        player.chooseHitOrStay(Player.WANT_HIT)
        dealer.giveCardIfPlayerWantHit(player)

        player.getCards().size shouldBe 1
    }

    "dealer의 giveCardIfPlayerWantHit()가 player의 status가 stay인 경우, card를 전달하지 않는다" {
        val dealer = Dealer()
        val player = Player("testUser")

        player.chooseHitOrStay(Player.WANT_STAY)
        dealer.giveCardIfPlayerWantHit(player)

        player.getCards().size shouldBe 0
    }
})
