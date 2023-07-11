package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어가 카드를 hit 하면 카드의 수가 증가한다." {
        val player = Player("tony")

        player.hit(Card(CardType.CLOVER, Denomination.ACE))
        player.cards.cards.size shouldBe 1
    }

    "플레이어의 스코어를 계산한다." {
        val player = Player("tony")

        player.hit(Card(CardType.CLOVER, Denomination.ACE))
        player.hit(Card(CardType.CLOVER, Denomination.KING))

        player.score() shouldBe 21
    }

    "스코어가 21 미만일 경우만 카드를 더 받을 수 있다." {
        val player = Player("tony")

        player.hit(Card(CardType.HEART, Denomination.KING))
        player.hit(Card(CardType.CLOVER, Denomination.KING))
        player.hit(Card(CardType.DIAMOND, Denomination.KING))

        player.canReceive() shouldBe false
    }
})
