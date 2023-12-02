package blackjack.domain

import blackjack.controller.ResultProcessor
import blackjack.domain.player.PlayerName
import blackjack.mock.InputProcessorMock
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BlackJackGameTest : DescribeSpec({
    describe("게임 생성") {
        context("게임에 참여할 2명의 이름 전달") {
            val name1 = "Hong"
            val name2 = "Kim"
            val game = BlackJackGame(
                inputProcessor = InputProcessorMock(
                    playerNames = listOf(name1, name2)
                ),
                resultProcessor = ResultProcessor(),
            )

            it("전달된 이름으로 플레이어 세팅") {
                val playerNames = game.dealCards.table.players.value.map { it.name }

                playerNames[0] shouldBe PlayerName(name1)
                playerNames[1] shouldBe PlayerName(name2)
            }
        }
    }
})
