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
        player.toString() shouldBe "pobi"
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

    "갖고있는 카드들의 합을 구할 수 있다." {
        player.getScore() shouldBe 16
    }

    "갖고있는 카드에 ACE 가 포함되어 있으면 1, 11중 하나를 사용해 21을 넘지 않는 큰 수를 합으로 구한다." {
        val withAcePlayer = Player("ace")
        withAcePlayer.hit(cloverA)
        withAcePlayer.hit(heartQ)
        withAcePlayer.getScore() shouldBe 21
    }
})
