package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    "랜덤한 카드 2장을 받아서 게임을 시작한다." {
        val randomCardPlayer = Player("test")
        randomCardPlayer.start()
        randomCardPlayer.deck.size() shouldBe 2
    }

    val player = Player("pobi")

    "플레이어는 이름을 갖는다." {
        player.name shouldBe "pobi"
    }

    "새로운 카드를 받을 수 있다." {
        player.hit(spade5)
        player.deck.size() shouldBe 1
    }
})
