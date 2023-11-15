package blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GameDealerTest :StringSpec({

    "딜러는 매번 서로 다른 52장의 카드를 준비한다." {
        val cards = (1..52).map { GameDealer.deal() }.toSet()
        cards.size shouldBe 52
    }
})
