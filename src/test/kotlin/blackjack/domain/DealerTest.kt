package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러의 카드 상태를 확인한다" {
        val dealer = Dealer()
        val gamer1 = Gamer("a").apply { changeState(true) }
        val gamer2 = Gamer("b").apply { changeState(true) }
        val gamer3 = Gamer("c").apply { changeState(true) }
        val gamer4 = Gamer("d").apply { changeState(false) }
        val gamerList = listOf(gamer1, gamer2, gamer3, gamer4)

        dealer.checkResult(gamerList)

        dealer.states.count { it == State.WIN } shouldBe 1
        dealer.states.count { it == State.LOSE } shouldBe 3
    }
})
