package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Denomination
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Suite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldBeEmpty
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
        val gameView = GameView(io, dealer, players)

        gameView.run()

        io.printed.shouldBeEmpty()
        players.map { it.hand.count } shouldBe listOf(2, 3)
    }
})

fun createDealer(vararg cards: Card): Dealer = Dealer(Deck(cards.toList()))

fun createPlayer(name: String, vararg cards: Card): Player = Player(name, Hand(cards.toList()))
