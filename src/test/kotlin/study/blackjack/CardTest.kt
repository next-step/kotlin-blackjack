package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Card
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class CardTest: StringSpec({
    "카드 Number 1 일 경우 카드 이름 테스트" {
        val card = Card(Suit.SPADE,1)

        card.name() shouldBe "A스페이드"
    }
    "카드 Number 11, 12, 13 일 경우 카드 이름 테스트" {
        var card = Card(Suit.SPADE,11)
        card.name() shouldBe "J스페이드"

        card = Card(Suit.SPADE,12)
        card.name() shouldBe "Q스페이드"

        card = Card(Suit.SPADE,13)
        card.name() shouldBe "K스페이드"
    }
    "카드 score ace 카드 점수 테스트" {
        val card = Card(Suit.SPADE,1)
        card.score(false) shouldBe 1
        card.score() shouldBe 11

    }
    "카드 score  카드 King, Queen, Jack 일경우 점수 테스트" {
        var card = Card(Suit.SPADE,11)
        card.score() shouldBe 10

        card = Card(Suit.SPADE,12)
        card.score() shouldBe 10

        card = Card(Suit.SPADE,13)
        card.score() shouldBe 10
    }
})

