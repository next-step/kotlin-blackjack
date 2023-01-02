package blackjack.domain.card

import blackjack.domain.player.CardHolder
import blackjack.view.console.toContentString
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class CardsTest : FunSpec({
    test("모든 카드는 총 56장이다.") {
        val expectedCount = CardNumber.values().size * CardShape.values().size

        expectedCount shouldBe 56
        Cards.ALL shouldHaveSize expectedCount
        Cards.ALL.toSet() shouldHaveSize expectedCount
    }

    context("Cards가 정상적으로 블랙잭 카드 점수를 계산한다.") {
        withData(
            nameFn = { "cards: ${it.first.toContentString()}, score: ${it.second}점" },
            listOf(
                Cards.of(
                    Card(CardNumber.TEN, CardShape.HEART),
                    Card(CardNumber.KING, CardShape.SPADE)
                ) to 20,
                Cards.of(
                    Card(CardNumber.FIVE, CardShape.HEART),
                    Card(CardNumber.SIX, CardShape.SPADE),
                    Card(CardNumber.QUEEN, CardShape.CLUB),
                ) to 21,
                Cards.of(
                    Card(CardNumber.ONE, CardShape.CLUB),
                    Card(CardNumber.FOUR, CardShape.HEART),
                    Card(CardNumber.JACK, CardShape.SPADE),
                    Card(CardNumber.TWO, CardShape.SPADE)
                ) to 17,
                Cards.of(
                    Card(CardNumber.THREE, CardShape.HEART),
                    Card(CardNumber.KING, CardShape.SPADE),
                    Card(CardNumber.SEVEN, CardShape.SPADE),
                ) to 20,
                Cards.of(
                    Card(CardNumber.QUEEN, CardShape.HEART),
                    Card(CardNumber.NINE, CardShape.DIAMOND),
                    Card(CardNumber.TEN, CardShape.HEART)
                ) to 29
            )
        ) { (cards, expectedScore) ->
            cards.score shouldBe expectedScore
            cards shouldHaveAtLeastSize CardHolder.INIT_CARD_COUNT
        }
    }

    context("Cards가 정상적으로 블랙잭 카드 점수를 계산한다. (ACE 예외 상황)") {
        withData(
            nameFn = { "cards: ${it.first.toContentString()}, score: ${it.second}점" },
            listOf(
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.ACE, CardShape.SPADE)
                ) to 12,
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.NINE, CardShape.SPADE)
                ) to 20,
                Cards.of(
                    Card(CardNumber.QUEEN, CardShape.CLUB),
                    Card(CardNumber.KING, CardShape.SPADE),
                    Card(CardNumber.ACE, CardShape.HEART)
                ) to 21,
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.KING, CardShape.SPADE)
                ) to 21,
                Cards.of(
                    Card(CardNumber.QUEEN, CardShape.CLUB),
                    Card(CardNumber.SIX, CardShape.DIAMOND),
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.FOUR, CardShape.HEART)
                ) to 21,
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.SEVEN, CardShape.DIAMOND),
                    Card(CardNumber.QUEEN, CardShape.CLUB),
                    Card(CardNumber.ACE, CardShape.DIAMOND),
                    Card(CardNumber.TWO, CardShape.CLUB),
                ) to 21,
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.SIX, CardShape.DIAMOND),
                    Card(CardNumber.KING, CardShape.CLUB),
                    Card(CardNumber.ACE, CardShape.DIAMOND),
                    Card(CardNumber.ACE, CardShape.SPADE),
                    Card(CardNumber.TWO, CardShape.HEART),
                ) to 21,
                Cards.of(
                    Card(CardNumber.ACE, CardShape.HEART),
                    Card(CardNumber.SEVEN, CardShape.DIAMOND),
                    Card(CardNumber.JACK, CardShape.CLUB),
                    Card(CardNumber.ACE, CardShape.DIAMOND),
                    Card(CardNumber.ACE, CardShape.CLUB),
                    Card(CardNumber.ACE, CardShape.SPADE)
                ) to 21
            )
        ) { (cards, expectedScore) ->
            cards.score shouldBe expectedScore
            cards shouldHaveAtLeastSize CardHolder.INIT_CARD_COUNT
        }
    }
})
