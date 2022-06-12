package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.GameStatus
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class WinnerViewTest : StringSpec({

    "게임의 승자와 패자에 대한 정보를 출력한다" {
        val io = StubIO()
        val dealer = createDealer(
            Card(Suite.CLUBS, Denomination.JACK),
            Card(Suite.DIAMONDS, Denomination.NINE),
        )
        repeat(2) { dealer.drawSelf() }
        val player = listOf(
            createPlayer(
                "player1",
                Card(Suite.SPADES, Denomination.JACK),
                Card(Suite.HEARTS, Denomination.ACE),
            ),
            createPlayer(
                "player2",
                Card(Suite.CLUBS, Denomination.JACK),
                Card(Suite.DIAMONDS, Denomination.NINE),
            ),
            createPlayer(
                "player3",
                Card(Suite.SPADES, Denomination.JACK),
                Card(Suite.HEARTS, Denomination.EIGHT),
            ),
        )
        val status = GameStatus(dealer, player)
        val winnerView = WinnerView(io)

        winnerView.run(status)

        io.printed shouldBe listOf(
            "## 최종 승패",
            "딜러: 1승 1무 1패",
            "player1: 승",
            "player2: 무",
            "player3: 패",
        )
    }
})
