package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerResultTest : StringSpec({
    "플레이어가 딜러와의 대결에서 승리하면 승리한 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.SIX),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        val player = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.NINE)
        )

        val playerResult = PlayerResult.of(player, dealer)
        playerResult.result shouldBe ResultStatus.WIN
    }

    "플레이어가 딜러와의 대결에서 패하면 패한 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.SIX),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        val player = User(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val playerResult = PlayerResult.of(player, dealer)
        playerResult.result shouldBe ResultStatus.LOSE
    }

    "플레이어가 딜러와의 대결에서 무승부라면 무승부한 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.SIX)
        )

        val player = User(
            Card(Suite.SPADE, Denomination.KING),
            Card(Suite.CLOVER, Denomination.SIX)
        )

        val playerResult = PlayerResult.of(player, dealer)
        playerResult.result shouldBe ResultStatus.DRAW
    }
})
