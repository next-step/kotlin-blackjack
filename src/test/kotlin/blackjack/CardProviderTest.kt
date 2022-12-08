package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.ints.shouldNotBeLessThan
import io.kotest.matchers.shouldBe

class CardProviderTest : StringSpec({

    "시작하면 플레이어는 두장의 카드를 지급 받는다" {
        val player = Player("player1") { "y" }
        val cardProvider = CardProvider()

        cardProvider.addPlayer(player)
        cardProvider.start()

        player.takeCards.size shouldBe 2
    }

    "플레이어는 카드의 합이 21이 넘지 않는 경우 원한다면 카드를 계속 받을 수 있다" {
        val player = Player("player1") { "y" }
        val cardProvider = CardProvider()

        cardProvider.addPlayer(player)
        cardProvider.start()
        cardProvider.play()

        CardCalculator(player.takeCards).sum() shouldNotBeLessThan 20
    }
})
