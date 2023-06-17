package blackjack.domain.game

import blackjack.domain.player.Player
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize

class BlackJackGameTest : DescribeSpec({

    describe(name = "블랙잭 게임에 플레이어 이름을 제공해서 게임을 시작할 수 있다.") {
        val playerNames = listOf("진원", "패자")
        val blackJackGame = BlackJackGame(playerNames = playerNames)

        context(name = "게임을 시작하여 플레이어는 입력한 숫자만큼 생성된다.") {
            val actual = blackJackGame.players

            it(name = "입력한 문자열과 생성된 플레이어가 같다.") {
                actual shouldHaveSize playerNames.size
            }
        }

        context(name = "게임을 시작하여 이벤트를 입력하고 조건에 맞으면 이벤트가 실행된다.") {
            val expect = mutableListOf<Player>()

            val gameEvent = GameEvent(
                hitOrNot = { true },
                resultEvent = { expect.add(element = it) },
            )

            blackJackGame.start(gameEvent = gameEvent)

            it(name = "무조건 실행 조건을 넣었을 때 이벤트는 무조건 한 번 이상 실행된다.") {
                expect shouldHaveAtLeastSize 1
            }
        }
    }
})
