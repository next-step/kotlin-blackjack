package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    test("카드 덱 생성 정상 반환 테스트") {
        val cards = Cards.create()
        val answer = CardShape.values().size * CardNumber.values().size

        cards.cards.size shouldBe answer
    }

    test("카드 점수의 합 정상 반환 테스트") {
        val ace = Card.of(CardNumber.ACE, CardShape.Spade)
        val two = Card.of(CardNumber.TWO, CardShape.Spade)
        val three = Card.of(CardNumber.THREE, CardShape.Spade)
        val jack = Card.of(CardNumber.JACK, CardShape.Spade)
        val queen = Card.of(CardNumber.QUEEN, CardShape.Spade)
        val king = Card.of(CardNumber.KING, CardShape.Spade)

        forAll(
            row(Cards.of(ace, two), listOf(Score(3), Score(13))),
            row(Cards.of(ace, three), listOf(Score(4), Score(14))),
            row(Cards.of(ace, jack), listOf(Score(11), Score(21))),
            row(Cards.of(ace, queen), listOf(Score(11), Score(21))),
            row(Cards.of(ace, king), listOf(Score(11), Score(21))),

            row(Cards.of(two, three), listOf(Score(5))),

            row(Cards.of(two, jack), listOf(Score(12))),
            row(Cards.of(two, king), listOf(Score(12))),
            row(Cards.of(two, queen), listOf(Score(12))),

            row(Cards.of(jack, queen), listOf(Score(20))),
            row(Cards.of(queen, king), listOf(Score(20))),
            row(Cards.of(jack, king), listOf(Score(20))),
        ) { cards, answer ->
            cards.scores() shouldBe answer
        }
    }

    test("힛 가능 여부 정상 반환 테스트") {
        val ace = Card.of(CardNumber.ACE, CardShape.Spade)
        val two = Card.of(CardNumber.TWO, CardShape.Spade)
        val jack = Card.of(CardNumber.JACK, CardShape.Spade)
        val queen = Card.of(CardNumber.QUEEN, CardShape.Spade)

        forAll(
            row(Cards.of(ace, two), true),
            row(Cards.of(ace, jack), false),
            row(Cards.of(two, jack, queen), false),
        ) { cards, answer ->
            cards.isPossibleToHit() shouldBe answer
        }
    }
})
