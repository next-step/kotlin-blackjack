package blackjack.scorerule.domain

import blackjack.common.service.DeckManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({
    "딜러는 게임을 시작할 때에 2장의 카드를 받는다." {
        val emptyArray = arrayOf<ScorePlayer>()
        val dealer = ScoreDealer()
        val deckManager = DeckManager()
        dealer.beginRound(deckManager, emptyArray)
        dealer.hands().size shouldBe 2
    }
})
