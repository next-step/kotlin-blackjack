package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어가 카드를 hit 하면 카드의 수가 증가한다." {
        val player = Player("tony")

        player.hit(Card(CardType.CLOVER, Denomination.ACE))
        player.cards.cards.size shouldBe 1
    }
})
