package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResultViewTest : StringSpec({

    "게임 결과를 출력한다" {
        val io = StubIO()
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
        val resultView = ResultView(io, players)

        resultView.run()

        io.printed shouldBe listOf(
            "pobi카드: 2하트, 8스페이드, A클로버 - 결과: 21",
            "jason카드: 7클로버, K스페이드 - 결과: 17",
        )
    }
})
