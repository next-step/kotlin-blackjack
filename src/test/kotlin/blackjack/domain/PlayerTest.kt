package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : StringSpec({
    "플레이어의 카드 총합이 21을 초과하여 hit 을 할 수 없다면 버스트다." {
        val player = User(
            Card(Suite.SPADE, Denomination.FIVE),
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.JACK)
        )

        val result = player.isHit()
        result shouldBe false
    }

    "플레이어 카드 총합이 21 미만이라면 hit 을 할 수 있다." {
        val player = User(
            Card(Suite.SPADE, Denomination.FIVE),
            Card(Suite.HEART, Denomination.QUEEN)
        )

        val result = player.isHit()
        result shouldBe true
    }

    "플레이어 카드 총합이 21점이고 ACE 카드를 포함한 2장이라면 블랙잭이다." {
        val player = User(
            Card(Suite.DIAMOND, Denomination.ACE),
            Card(Suite.SPADE, Denomination.JACK)
        )

        val result = player.isBlackJack()
        result shouldBe true
    }

    "플레이어의 점수보다 비교할 점수가 더 크다면 LOSE 다." {
        val player = User(
            Card(Suite.SPADE, Denomination.EIGHT),
            Card(Suite.SPADE, Denomination.JACK),
        )
        val dealerScore = 20

        val result = player.match(dealerScore)
        result shouldBe ResultStatus.LOSE
    }

    "플레이어의 점수보다 딜러의 점수가 더 작다면 WIN 이다." {
        val player = User(
            Card(Suite.SPADE, Denomination.EIGHT),
            Card(Suite.SPADE, Denomination.JACK),
        )
        val dealerScore = 15

        val result = player.match(dealerScore)
        result shouldBe ResultStatus.WIN
    }

    // 둘 다 블랙잭인 상황은 다른 로직에서 판별한다.
    "플레이어의 점수와 딜러의 점수가 같다면 DRAW 다." {
        val player = User(
            Card(Suite.SPADE, Denomination.EIGHT),
            Card(Suite.SPADE, Denomination.JACK),
        )
        val dealerScore = 18

        val result = player.match(dealerScore)
        result shouldBe ResultStatus.DRAW
    }
})
