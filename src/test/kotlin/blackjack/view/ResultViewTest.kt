package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.GameStatus
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResultViewTest : StringSpec({

    "게임 결과를 출력한다" {
        val io = StubIO()
        val dealer = createDealer(
            Card(Suite.DIAMONDS, Denomination.THREE),
            Card(Suite.CLUBS, Denomination.NINE),
            Card(Suite.DIAMONDS, Denomination.EIGHT)
        )
        repeat(3) { dealer.drawSelf() }
        val players = listOf(
            createPlayer(
                "pobi",
                Card(Suite.HEARTS, Denomination.TWO),
                Card(Suite.SPADES, Denomination.EIGHT),
                Card(Suite.CLUBS, Denomination.ACE),
            ),
            createPlayer(
                "jason",
                Card(Suite.CLUBS, Denomination.SEVEN),
                Card(Suite.SPADES, Denomination.KING),
            )
        )
        val status = GameStatus(dealer, players)
        val resultView = ResultView(io)

        resultView.run(status)

        io.printed shouldBe listOf(
            "딜러카드: 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20",
            "pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21",
            "jason카드: 7클로버, K스페이드 - 결과: 17",
            "",
        )
    }
})
