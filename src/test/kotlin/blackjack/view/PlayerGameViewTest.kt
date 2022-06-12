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

//    "아무도 카드를 더 받을 수 없는 경우 게임을 종료한다" {
//        val dealer = createDealer()
//        val players = listOf(
//            createPlayer(
//                "blackjack",
//                Card(Suite.CLUBS, Denomination.QUEEN),
//                Card(Suite.CLUBS, Denomination.ACE),
//            ),
//            createPlayer(
//                "bust",
//                Card(Suite.CLUBS, Denomination.NINE),
//                Card(Suite.CLUBS, Denomination.THREE),
//                Card(Suite.CLUBS, Denomination.KING),
//            ),
//        )
//        val io = StubIO()
//        val playerGameView = PlayerGameView(io, dealer, players)
//
//        playerGameView.run()
//
//        io.printed shouldBe listOf("")
//        players.map { it.hand.count } shouldBe listOf(2, 3)
//    }
//
//    "카드를 받을 수 있지만 거절하는 경우 손패가 그대로 유지된다" {
//        val dealer = createDealer()
//        val player = createPlayer(
//            "player",
//            Card(Suite.CLUBS, Denomination.NINE),
//            Card(Suite.CLUBS, Denomination.ACE),
//        )
//        val io = StubIO()
//        val playerGameView = PlayerGameView(io, dealer, listOf(player))
//        io.addInput("n")
//
//        playerGameView.run()
//
//        io.printed shouldBe listOf(
//            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
//            "",
//        )
//        player.hand.count shouldBe 2
//    }
//
//    "카드를 받을 수 있고 수락하는 경우 손패에 카드가 추가된다" {
//        val dealer = createDealer(Card(Suite.CLUBS, Denomination.ACE))
//        val player = createPlayer(
//            "player",
//            Card(Suite.HEARTS, Denomination.TWO),
//            Card(Suite.SPADES, Denomination.EIGHT),
//        )
//        val io = StubIO()
//        val playerGameView = PlayerGameView(io, dealer, listOf(player))
//        io.addInput("y")
//
//        playerGameView.run()
//
//        io.printed shouldBe listOf(
//            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
//            "player카드: 2하트, 8스페이드, A클로버",
//            "",
//        )
//        player.hand.count shouldBe 3
//    }
//
//    "유효하지 않는 입력을 제공하면 다시 입력받는다" {
//        val dealer = createDealer(Card(Suite.CLUBS, Denomination.ACE))
//        val player = createPlayer(
//            "player",
//            Card(Suite.HEARTS, Denomination.TWO),
//            Card(Suite.SPADES, Denomination.EIGHT),
//        )
//        val io = StubIO()
//        val playerGameView = PlayerGameView(io, dealer, listOf(player))
//        io.addInput("")
//        io.addInput("yn")
//        io.addInput("invalid")
//        io.addInput("  n  ")
//
//        playerGameView.run()
//
//        io.printed shouldBe listOf(
//            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
//            "잘못된 입력입니다. 다시 입력해주세요.",
//            "잘못된 입력입니다. 다시 입력해주세요.",
//            "잘못된 입력입니다. 다시 입력해주세요.",
//            "",
//        )
//    }
})
