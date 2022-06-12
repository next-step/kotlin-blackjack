package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlayerGameViewTest : FreeSpec({

    "주어진 참가자의 카드정보를 출력한다" {
        val io = StubIO()
        val player = createPlayer(
            "player",
            Card(Suite.CLUBS, Denomination.QUEEN),
            Card(Suite.CLUBS, Denomination.ACE),
        )
        val playerGameView = PlayerGameView(io)

        playerGameView.printStatus(player)

        io.printed shouldBe listOf("player카드: Q클로버, A클로버")
    }

    "카드 추가여부" - {
        "안내문구를 출력한다" {
            val io = StubIO()
            io.addInput("y")
            val player = createPlayer("player")
            val playerGameView = PlayerGameView(io)

            playerGameView.getDrawChoice(player)

            io.printed shouldBe listOf("player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        }

        "y를 입력하는 경우 true 를 반환한다" {
            val io = StubIO()
            io.addInput("y")
            val player = createPlayer("player")
            val playerGameView = PlayerGameView(io)

            val result = playerGameView.getDrawChoice(player)

            result.shouldBeTrue()
        }

        "n를 입력하는 경우 false 를 반환한다" {
            val io = StubIO()
            io.addInput("n")
            val player = createPlayer("player")
            val playerGameView = PlayerGameView(io)

            val result = playerGameView.getDrawChoice(player)

            result.shouldBeFalse()
        }

        "잘못 입력하는 경우 다시 입력받는다" {
            val io = StubIO()
            io.addInput("invalid")
            io.addInput("N")
            val player = createPlayer("player")
            val playerGameView = PlayerGameView(io)

            playerGameView.getDrawChoice(player)

            io.printed.last() shouldBe "잘못된 입력입니다. 다시 입력해주세요."
        }
    }
})
