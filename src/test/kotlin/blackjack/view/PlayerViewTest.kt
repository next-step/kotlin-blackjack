package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerViewTest : StringSpec({

    "참가자 현황을 출력한다" {
        val io = StubIO()
        val players = listOf(
            createPlayer(
                "pobi",
                Card(Suite.HEARTS, Denomination.TWO),
                Card(Suite.SPADES, Denomination.EIGHT),
            ),
            createPlayer(
                "jason",
                Card(Suite.CLUBS, Denomination.SEVEN),
                Card(Suite.SPADES, Denomination.KING),
            ),
        )
        val playerView = PlayerView(io, players)

        playerView.run()

        io.printed shouldBe listOf(
            "pobi, jason에게 2장의 나누었습니다.",
            "pobi카드: 2하트, 8스페이드",
            "jason카드: 7클로버, K스페이드",
            "",
        )
    }
})
