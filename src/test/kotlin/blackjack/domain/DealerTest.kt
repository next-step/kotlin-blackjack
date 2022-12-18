package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class DealerTest : StringSpec({

    "딜러의 점수가 21점을 초과하면 플레이어 점수에 상관없이 플레이어가 승리한다." {
        val dealer = Dealer(
            Card(Suite.SPADE, Denomination.SIX),
            Card(Suite.CLOVER, Denomination.JACK),
            Card(Suite.SPADE, Denomination.SEVEN)
        )
        val player = Player(
            Card(Suite.CLOVER, Denomination.SEVEN),
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.CLOVER, Denomination.QUEEN)
        )

        val result = dealer.getMatchResult(player)

        result shouldBe ResultStatus.WIN
    }

    "딜러의 점수가 21점이고 플레이어도 21점이라면 무승부다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.ACE)
        )

        val player = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        val result = dealer.getMatchResult(player)

        result shouldBe ResultStatus.DRAW
    }

    "딜러의 점수보다 플레이어의 점수가 21에 더 가깝다면 플레이어가 승리한다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val player = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.NINE)
        )

        val result = dealer.getMatchResult(player)

        result shouldBe ResultStatus.WIN
    }

    "플레이어의 점수보다 딜러가 21에 더 가깝다면 플레이어는 패배한다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.NINE)
        )

        val player = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.SEVEN)
        )

        val result = dealer.getMatchResult(player)

        result shouldBe ResultStatus.LOSE
    }

    "두명의 플레이어와 대결할 때 딜려가 다 졌다면 2패의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val player1 = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.ACE)
        )

        val player2 = Player(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.NINE)
        )

        dealer.getMatchResult(player1)
        dealer.getMatchResult(player2)

        dealer.results shouldBe listOf(ResultStatus.LOSE, ResultStatus.LOSE)
    }

    "두 명의 플레이어와 대결 할 때 딜러가 1명의 플레이어만 이기고 1명은 졌다면 1승 1패의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val player1 = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val player2 = Player(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.NINE)
        )

        dealer.getMatchResult(player1)
        dealer.getMatchResult(player2)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.LOSE)
    }

    "두 명의 플레이어와 대결 할 때 딜러가 모두 승리했다면 2승의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.EIGHT)
        )

        val player1 = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val player2 = Player(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.SIX)
        )

        dealer.getMatchResult(player1)
        dealer.getMatchResult(player2)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.WIN)
    }

    "세 명의 플레이어와 대결 할 때 딜러가 두 명의 플레이어한테는 승리하고 한 명과는 무승부였다면 2승 1무의 결과를 가진다." {
        val dealer = Dealer(
            Card(Suite.HEART, Denomination.QUEEN),
            Card(Suite.DIAMOND, Denomination.ACE)
        )

        val player1 = Player(
            Card(Suite.SPADE, Denomination.QUEEN),
            Card(Suite.CLOVER, Denomination.FIVE)
        )

        val player2 = Player(
            Card(Suite.SPADE, Denomination.JACK),
            Card(Suite.HEART, Denomination.SIX)
        )

        val player3 = Player(
            Card(Suite.SPADE, Denomination.KING),
            Card(Suite.HEART, Denomination.ACE)
        )

        dealer.getMatchResult(player1)
        dealer.getMatchResult(player2)
        dealer.getMatchResult(player3)

        dealer.results shouldBe listOf(ResultStatus.WIN, ResultStatus.WIN, ResultStatus.DRAW)
    }
})
