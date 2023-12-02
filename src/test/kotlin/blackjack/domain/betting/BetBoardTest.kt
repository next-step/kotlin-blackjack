package blackjack.domain.betting

import blackjack.domain.batting.Amount
import blackjack.domain.batting.BetBoard
import blackjack.domain.batting.PlayerBet
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.mock.player
import blackjack.mock.players
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

class BetBoardTest : DescribeSpec({
    describe("BettingBoard") {
        context("플레이어 이름으로 베팅 금액 등록 (kim: 3_000원, lee: 4_000원)") {
            val players = players(player("kim"), player("lee"))
            val kimAmount = Amount(3_000)
            val leeAmount = Amount(4_000)
            val betAmount = { player: Player ->
                if (player.name.value == "kim") kimAmount
                else leeAmount
            }

            val betBoard: BetBoard = BetBoard.of(players, betAmount)

            it("등록된 플레이어 베팅은 2개") {
                betBoard.playerBets.size shouldBe 2
            }

            it("플레이어 베팅은 모두 BetPlaced 상태") {
                betBoard.playerBets.values.forAll { it is PlayerBet.BetPlaced }
            }

            it("플레이어 kim 의 베팅 금액은 3_000") {
                val name = PlayerName("kim")
                val playerBet = betBoard.playerBet(name)

                playerBet.playerName shouldBe name
                (playerBet as? PlayerBet.BetPlaced)?.betAmount shouldBe kimAmount
            }

            it("플레이어 lee 의 베팅 금액은 4_000") {
                val name = PlayerName("lee")
                val playerBet = betBoard.playerBet(name)

                playerBet.playerName shouldBe name
                (playerBet as? PlayerBet.BetPlaced)?.betAmount shouldBe leeAmount
            }
        }
    }

    describe("playerBet") {
        context("kim 이름으로 플레이어 베팅을 등록하면") {
            val kim = player("kim")
            val amount = Amount(3_000)

            val board = BetBoard.of(players(kim, player())) { player: Player ->
                if (player == kim) amount
                else Amount(5_000)
            }

            it("kim의 베팅 금액이 조회 된다") {
                val result = board.playerBet(PlayerName("kim"))

                result.shouldBeTypeOf<PlayerBet.BetPlaced>()
                result.betAmount shouldBe amount
            }
        }
    }
})
