package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어의 이름을 반환할 수 있다." {
        val player = Player(PlayerName("dino"), Hand())
        player.getName() shouldBe "dino"
    }

    "플레이어의 카드의 합을 반환할 수 있다." {
        val player =
            Player(
                PlayerName("dino"),
                Hand.createInitial(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.TWO, Suit.DIAMONDS))),
            )
        player.calculateTotal() shouldBe 13
    }
})
