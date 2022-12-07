package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CardProviderTest : StringSpec({

    "시작하면 플레이어는 두장의 카드를 지급 받는다" {
        val player = Player("player1")
        val cardProvider = CardProvider()

        cardProvider.addPlayer(player)
        cardProvider.start()

        player.takeCards.size shouldBe 2
    }
})
