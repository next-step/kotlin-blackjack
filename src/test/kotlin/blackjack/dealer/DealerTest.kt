package blackjack.dealer

import blackjack.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "게임이 시작되면 딜러는 참가자에게 카드를 2장 나누어준다." {
        val dealer = Dealer()
        val players = listOf(Player("참가자1"), Player("참가자2"))

        dealer.dealInitialCards(players)
        players[0].getHandSize() shouldBe 2
        players[1].getHandSize() shouldBe 2
    }
})
