package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class DealerTest : FreeSpec({

    "주어진 참가자의 손패가 패를 추가할 수 있는 상태인지 판별한다" - {
        "추가할 수 없는 경우" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.QUEEN),
                Card(Suite.HEARTS, Denomination.ACE)
            )

            val canDraw = createDealer().checkCardDrawable(player)

            canDraw.shouldBeFalse()
        }

        "추가할 수 있는 경우" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.QUEEN)
            )

            val canDraw = createDealer().checkCardDrawable(player)

            canDraw.shouldBeTrue()
        }
    }

    "참가자가 카드 추가를 요청하는 경우" - {
        "카드를 추가할 수 있음" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.FOUR),
            )

            createDealer().giveCard(player)

            player.hand.count shouldBe 2
        }

        "카드를 추가할 수 없음" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.FOUR),
                Card(Suite.HEARTS, Denomination.JACK),
                Card(Suite.HEARTS, Denomination.QUEEN),
            )

            shouldThrow<IllegalStateException> {
                createDealer().giveCard(player)
            }
        }
    }

    "참가자 이름을 받아 카드 2장을 가진 참가자를 생성한다" {
        val player = createDealer().makePlayer("user")

        player.name shouldBe "user"
        player.hand shouldBe Hand(
            listOf(
                Card(Suite.SPADES, Denomination.FIVE),
                Card(Suite.SPADES, Denomination.FOUR),
            )
        )
    }
})

fun createDealer(): Dealer = Dealer(
    Deck(
        listOf(
            Card(Suite.SPADES, Denomination.FIVE),
            Card(Suite.SPADES, Denomination.FOUR),
        )
    )
)

fun createPlayer(vararg cards: Card): Player = Player("player", Hand(cards.toList()))
