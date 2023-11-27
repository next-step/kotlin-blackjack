package blackjack.domain.model

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    test("카드 덱 생성 정상 반환 테스트") {
        val cards = Cards.create()
        val answer = CardShape.values().size * CardNumber.values().size

        cards.cards.forEach {
            println(it)
        }

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
            row(Cards.of(ace, two), listOf(3, 13)),
            row(Cards.of(ace, three), listOf(4, 14)),
            row(Cards.of(ace, jack), listOf(11, 21)),
            row(Cards.of(ace, queen), listOf(11, 21)),
            row(Cards.of(ace, king), listOf(11, 21)),

            row(Cards.of(two, three), listOf(5)),

            row(Cards.of(two, jack), listOf(12)),
            row(Cards.of(two, king), listOf(12)),
            row(Cards.of(two, queen), listOf(12)),

            row(Cards.of(jack, queen), listOf(20)),
            row(Cards.of(queen, king), listOf(20)),
            row(Cards.of(jack, king), listOf(20)),
        ) { cards, answer ->
            cards.scores() shouldBe answer
        }
    }
})
