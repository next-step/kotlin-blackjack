package blackjack.domain.game

import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerInfo
import blackjack.event.DealerEvent
import blackjack.event.GameEvent
import blackjack.event.PlayerEvent
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize

class BlackJackGameTest : DescribeSpec({

    describe(name = "블랙잭 게임에 플레이어 이름을 제공해서 게임을 시작할 수 있다.") {
        val playerInfos = listOf(
            PlayerInfo(name = "진원", betAmount = 0.0),
            PlayerInfo(name = "패자", betAmount = 0.0),
        )

        val blackJackGame = BlackjackGame(playerInfos = playerInfos)

        context(name = "게임을 시작하여 플레이어는 입력한 숫자만큼 생성된다.") {
            val actual = blackJackGame.players

            it(name = "입력한 문자열과 생성된 플레이어가 같다.") {
                actual shouldHaveSize playerInfos.size
            }
        }

        context(name = "게임을 시작하여 이벤트를 입력하고 조건에 맞으면 이벤트가 실행된다.") {
            val expect = mutableListOf<Player>()

            val gameEvent = GameEvent(
                playerEvent = PlayerEvent(
                    hitOrNot = { true },
                    resultEvent = { expect.add(element = it) },
                ),

                dealerEvent = DealerEvent { },
            )

            blackJackGame.start(gameEvent = gameEvent)

            it(name = "무조건 실행 조건을 넣었을 때 이벤트는 무조건 한 번 이상 실행된다.") {
                expect shouldHaveAtLeastSize 1
            }
        }
    }
})
