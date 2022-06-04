package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.Suite
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class ExtensionsTest : FreeSpec({

    "카드 문구를 생성한다" - {
        listOf(
            Card(Suite.HEARTS, Denomination.TWO) to "2하트",
            Card(Suite.SPADES, Denomination.ACE) to "A스페이드",
            Card(Suite.CLUBS, Denomination.FIVE) to "5클로버",
            Card(Suite.DIAMONDS, Denomination.QUEEN) to "Q다이아몬드",
        ).forEach { (card, expected) ->
            expected {
                card.text() shouldBe expected
            }
        }
    }

    "참가자 문구를 생성한다" {
        val player = Player(
            name = "user",
            hand = Hand(
                listOf(
                    Card(Suite.CLUBS, Denomination.NINE),
                    Card(Suite.SPADES, Denomination.JACK)
                )
            )
        )

        player.text() shouldBe "user카드: 9클로버, J스페이드"
    }
})
