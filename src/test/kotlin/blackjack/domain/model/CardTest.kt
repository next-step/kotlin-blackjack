package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardTest : FunSpec({
    test("카드 이름 정상 반환 테스트") {
        val card = Card.of(CardNumber.ACE, CardShape.Spade)
        val answer = "A스페이드"

        card.toString() shouldBe answer
    }

    test("카드 숫자가 2~10일 경우 점수가 1개이며 점수가 10인지 테스트") {
        forAll(
            row(Card.of(CardNumber.TWO, CardShape.Spade), Score(2)),
            row(Card.of(CardNumber.THREE, CardShape.Spade), Score(3)),
            row(Card.of(CardNumber.FOUR, CardShape.Spade), Score(4)),
            row(Card.of(CardNumber.FIVE, CardShape.Spade), Score(5)),
            row(Card.of(CardNumber.SIX, CardShape.Spade), Score(6)),
            row(Card.of(CardNumber.SEVEN, CardShape.Spade), Score(7)),
            row(Card.of(CardNumber.EIGHT, CardShape.Spade), Score(8)),
            row(Card.of(CardNumber.NINE, CardShape.Spade), Score(9)),
            row(Card.of(CardNumber.TEN, CardShape.Spade), Score(10)),
        ) { card, answer ->
            card.scores.size shouldBe 1
            card.scores[0] shouldBe answer
        }
    }

    test("카드 숫자가 ACE일 경우 점수가 2개이고 점수가 1, 11인지 테스트") {
        val card = Card.of(CardNumber.ACE, CardShape.Spade)

        card.scores.size shouldBe 2
        card.scores[0] shouldBe Score(1)
        card.scores[1] shouldBe Score(11)
    }

    test("카드 숫자가 K, Q, J일 경우 점수가 1개이고 점수가 10인지 테스트") {
        forAll(
            row(Card.of(CardNumber.JACK, CardShape.Spade)),
            row(Card.of(CardNumber.QUEEN, CardShape.Spade)),
            row(Card.of(CardNumber.KING, CardShape.Spade)),
        ) { card ->
            card.scores.size shouldBe 1
            card.scores[0] shouldBe Score(10)
        }
    }
})
