package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : StringSpec({
    "플레이어의 카드 총합이 21을 초과하여 hit 을 할 수 없다면 버스트다." {
        val player = Player(
            Card(Suite.SPADE, Denomination.FIVE),
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.JACK)
        )

        val result = player.isHit()
        result shouldBe false
    }

    "플레이어 카드 총합이 21 미만이라면 hit 을 할 수 있다." {
        val player = Player(
            Card(Suite.SPADE, Denomination.FIVE),
            Card(Suite.HEART, Denomination.QUEEN)
        )

        val result = player.isHit()
        result shouldBe true
    }

    "플레이어 카드 총합이 21점이고 ACE 카드를 포함한 2장이라면 블랙잭이다." {
        val player = Player(
            Card(Suite.DIAMOND, Denomination.ACE),
            Card(Suite.SPADE, Denomination.JACK)
        )

        val result = player.isBlackJack()
        result shouldBe true
    }
})
