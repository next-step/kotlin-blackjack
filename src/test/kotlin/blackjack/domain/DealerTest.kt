package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 게임을 시작할 때에 2장의 카드를 받는다." {
        val emptyArray = arrayOf<Player>()
        val dealer = Dealer(players = emptyArray)
        dealer.initializeRound()
        dealer.hands().size shouldBe 2
    }
})
