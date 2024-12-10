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
//    "카드 Number 1 일 경우 카드 이름 테스트" {
//        val card = Card(Suit.SPADE, CardRank.ACE)
//
//        card.name() shouldBe "A스페이드"
//    }
//    "카드 Number 11, 12, 13 일 경우 카드 이름 테스트" {
//        var card = Card(Suit.SPADE, CardRank.JACK)
//        card.name() shouldBe "J스페이드"
//
//        card = Card(Suit.SPADE, CardRank.QUEEN)
//        card.name() shouldBe "Q스페이드"
//
//        card = Card(Suit.SPADE, CardRank.KING)
//        card.name() shouldBe "K스페이드"
//    }
    "카드 score ace 카드 점수 테스트" {
        val card = Card(Suit.SPADE, CardRank.ACE)
        card.score(false) shouldBe 1
        card.score() shouldBe 11
    }
})
