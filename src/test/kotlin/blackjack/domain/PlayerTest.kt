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

    "새로운 카드를 받을 때 21을 초과하지 않으면 더 받을 수 있다.(힛)" {
        player.takeOrBust(cloverA) shouldBe PlayerState.HIT
        player.hit(cloverA)
    }

    "새로운 카드를 받을 때 21을 넘으면 더이상 받을 수 없다.(버스트)" {
        player.hit(heartQ)
        player.takeOrBust(dia7) shouldBe PlayerState.BUST
    }
})
