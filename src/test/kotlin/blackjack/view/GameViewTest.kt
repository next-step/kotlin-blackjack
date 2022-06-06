package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Suite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class GameViewTest : FreeSpec({

    "아무도 카드를 더 받을 수 없는 경우 게임을 종료한다" {
        val dealer = createDealer()
        val players = listOf(
            createPlayer(
                "blackjack",
                Card(Suite.CLUBS, Denomination.QUEEN),
                Card(Suite.CLUBS, Denomination.ACE),
            ),
            createPlayer(
                "bust",
                Card(Suite.CLUBS, Denomination.NINE),
                Card(Suite.CLUBS, Denomination.THREE),
                Card(Suite.CLUBS, Denomination.KING),
            ),
        )
        val io = StubIO()
        val playerGameView = PlayerGameView(io, dealer, players)

        playerGameView.run()

        io.printed shouldBe listOf("")
        players.map { it.hand.count } shouldBe listOf(2, 3)
    }

    "카드를 받을 수 있지만 거절하는 경우 손패가 그대로 유지된다" {
        val dealer = createDealer()
        val player = createPlayer(
            "player",
            Card(Suite.CLUBS, Denomination.NINE),
            Card(Suite.CLUBS, Denomination.ACE),
        )
        val io = StubIO()
        val playerGameView = PlayerGameView(io, dealer, listOf(player))
        io.addInput("n")

        playerGameView.run()

        io.printed shouldBe listOf(
            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
            "",
        )
        player.hand.count shouldBe 2
    }

    "카드를 받을 수 있고 수락하는 경우 손패에 카드가 추가된다" {
        val dealer = createDealer(Card(Suite.CLUBS, Denomination.ACE))
        val player = createPlayer(
            "player",
            Card(Suite.HEARTS, Denomination.TWO),
            Card(Suite.SPADES, Denomination.EIGHT),
        )
        val io = StubIO()
        val playerGameView = PlayerGameView(io, dealer, listOf(player))
        io.addInput("y")

        playerGameView.run()

        io.printed shouldBe listOf(
            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
            "player카드: 2하트, 8스페이드, A클로버",
            "",
        )
        player.hand.count shouldBe 3
    }

    "유효하지 않는 입력을 제공하면 다시 입력받는다" {
        val dealer = createDealer(Card(Suite.CLUBS, Denomination.ACE))
        val player = createPlayer(
            "player",
            Card(Suite.HEARTS, Denomination.TWO),
            Card(Suite.SPADES, Denomination.EIGHT),
        )
        val io = StubIO()
        val playerGameView = PlayerGameView(io, dealer, listOf(player))
        io.addInput("")
        io.addInput("yn")
        io.addInput("invalid")
        io.addInput("  n  ")

        playerGameView.run()

        io.printed shouldBe listOf(
            "player는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)",
            "잘못된 입력입니다. 다시 입력해주세요.",
            "잘못된 입력입니다. 다시 입력해주세요.",
            "잘못된 입력입니다. 다시 입력해주세요.",
            "",
        )
    }
})

fun createDealer(vararg cards: Card): Dealer = Dealer(Deck(cards.toList()))

fun createPlayer(name: String, vararg cards: Card): Player = Player(name, Hand(cards.toList()))
