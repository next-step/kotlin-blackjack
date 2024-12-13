package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.Card
import study.blackjack.model.CardRank
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class CardTest : StringSpec({
    "카드 score ace 카드 점수 테스트" {
        val card = Card(Suit.SPADE, CardRank.ACE)
        card.score() shouldBe 1
    }
})
