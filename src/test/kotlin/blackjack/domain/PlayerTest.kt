package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class PlayerTest : StringSpec({

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
})
