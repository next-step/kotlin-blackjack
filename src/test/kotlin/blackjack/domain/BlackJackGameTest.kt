package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
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
            )

            it("전달된 이름으로 플레이어 세팅") {
                game.table.players shouldBe Players(
                    listOf(
                        Player(PlayerName(name1), { Action.HIT }),
                        Player(PlayerName(name2), { Action.HIT })
                    )
                )
            }
        }
    }
})
