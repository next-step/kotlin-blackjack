package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.GameStatus
import blackjack.domain.Suite
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerViewTest : StringSpec({

    "딜러와 참가자 현황을 출력한다" {
        val io = StubIO()
        val dealer = createDealer(
            Card(Suite.DIAMONDS, Denomination.THREE),
            Card(Suite.SPADES, Denomination.FOUR),
        )
        repeat(2) { dealer.giveCard(dealer) }
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
        val playerView = PlayerView(io)
        val status = GameStatus(dealer, players)

        playerView.printPlayers(status)

        io.printed shouldBe listOf(
            "딜러와 pobi, jason에게 2장의 나누었습니다.",
            "딜러카드: 3다이아몬드",
            "pobi카드: 2하트, 8스페이드",
            "jason카드: 7클로버, K스페이드",
            "",
        )
    }
})
