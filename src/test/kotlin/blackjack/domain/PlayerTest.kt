package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

internal class PlayerTest : FreeSpec({

    "이름과 손패를 통해 인스턴스를 생성한다" {
        val player = Player(
            name = "user",
            hand = Hand(listOf(Card(Suite.CLUBS, Denomination.NINE)))
        )

        player.name shouldBe "user"
        player.hand.cards shouldBe listOf(Card(Suite.CLUBS, Denomination.NINE))
    }

    "손패에 카드를 추가할 수 있다" {
        val player = Player(
            name = "user",
            hand = Hand(listOf(Card(Suite.CLUBS, Denomination.NINE)))
        )

        player.addCard(Card(Suite.DIAMONDS, Denomination.ACE))

        player.hand.cards shouldBe listOf(
            Card(Suite.CLUBS, Denomination.NINE),
            Card(Suite.DIAMONDS, Denomination.ACE)
        )
    }

    "현재 점수를 반환할 수 있다" {
        val player = Player(
            name = "user",
            hand = Hand(listOf(Card(Suite.CLUBS, Denomination.NINE)))
        )

        player.score shouldBe Score(listOf(Card(Suite.CLUBS, Denomination.NINE)))
    }

    "카드를 추가할 수 있는 상태인지 판별한다" - {
        "추가할 수 없는 경우" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.QUEEN),
                Card(Suite.HEARTS, Denomination.ACE)
            )

            val canDraw = player.canDrawCard

            canDraw.shouldBeFalse()
        }

        "추가할 수 있는 경우" {
            val player = createPlayer(
                Card(Suite.HEARTS, Denomination.QUEEN)
            )

            val canDraw = player.canDrawCard

            canDraw.shouldBeTrue()
        }
    }
})
