package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class DealerTest : FreeSpec({
    val deck = Deck(listOf(Card(Suite.SPADES, Denomination.FIVE)))

    "주어진 참가자의 손패가 패를 추가할 수 있는 상태인지 판별한다" - {
        "추가할 수 없는 경우" {
            val player = Player(
                name = "player",
                hand = Hand(
                    listOf(
                        Card(Suite.HEARTS, Denomination.QUEEN),
                        Card(Suite.HEARTS, Denomination.ACE)
                    ),
                )
            )
            val dealer = Dealer(deck)

            val canDraw = dealer.checkCardDrawable(player)

            canDraw.shouldBeFalse()
        }

        "추가할 수 있는 경우" {
            val player = Player(
                name = "player",
                hand = Hand(listOf(Card(Suite.HEARTS, Denomination.QUEEN)))
            )
            val dealer = Dealer(deck)

            val canDraw = dealer.checkCardDrawable(player)

            canDraw.shouldBeTrue()
        }
    }
})
