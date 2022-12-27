package blackjack.domain.card

import blackjack.domain.Blackjack
import blackjack.domain.player.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldHaveAtLeastSize
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import kotlin.math.abs

class CardsTest : FunSpec({
    test("모든 카드는 총 56장이다.") {
        val expectedCount = CardNumber.values().size * CardShape.values().size

        expectedCount shouldBe 56
        Cards.ALL shouldHaveSize expectedCount
        Cards.ALL.toSet() shouldHaveSize expectedCount
    }

    context("Cards가 정상적으로 블랙잭 카드 점수를 계산한다.") {
        withData(
            nameFn = { "${it.first} : ${it.second}점" },
            (1..10).map { CardsDataSet.testDataWithTwoCards() }
                .map { cards -> cards to cards.blackjackScore() }
        ) { (cards, expectedScore) ->
            cards.score shouldBe expectedScore
            cards shouldHaveAtLeastSize Player.INIT_CARD_COUNT
        }
    }

    context("Cards가 정상적으로 블랙잭 카드 점수를 계산한다. (ACE 예외 상황)") {
        withData(
            nameFn = { "${it.first} : ${it.second}점" },
            listOf(
                Cards(
                    listOf(
                        Card(CardNumber.ACE, CardShape.HEART),
                        Card(CardNumber.ACE, CardShape.SPADE)
                    )
                ) to 12,
                Cards(
                    listOf(
                        Card(CardNumber.ACE, CardShape.HEART),
                        Card(CardNumber.NINE, CardShape.SPADE)
                    )
                ) to 20,
                Cards(
                    listOf(
                        Card(CardNumber.QUEEN, CardShape.CLUB),
                        Card(CardNumber.ACE, CardShape.HEART),
                        Card(CardNumber.KING, CardShape.SPADE)
                    )
                ) to 21,
                Cards(
                    listOf(
                        Card(CardNumber.ACE, CardShape.HEART),
                        Card(CardNumber.KING, CardShape.SPADE)
                    )
                ) to 21,
                Cards(
                    listOf(
                        Card(CardNumber.QUEEN, CardShape.CLUB),
                        Card(CardNumber.ACE, CardShape.HEART),
                        Card(CardNumber.SIX, CardShape.DIAMOND),
                        Card(CardNumber.FOUR, CardShape.HEART)
                    )
                ) to 21
            )
        ) { (cards, expectedScore) ->
            cards.score shouldBe expectedScore
            cards shouldHaveAtLeastSize Player.INIT_CARD_COUNT
        }
    }
})

fun Cards.blackjackScore(): Int =
    cards.map { it.candidateScores }
        .cartesianProduct()
        .map { it.sum() }
        .filter { it <= Blackjack.BLACKJACK_BEST_SCORE }
        .minWithOrNull(compareBy { Blackjack.BLACKJACK_BEST_SCORE - it })
        ?: proximateScore()

fun Cards.proximateScore(): Int =
    cards.map { it.candidateScores }
        .cartesianProduct()
        .map { it.sum() }
        .minWithOrNull(compareBy { abs(it - Blackjack.BLACKJACK_BEST_SCORE) })
        ?: (Blackjack.BLACKJACK_BEST_SCORE + 1)

private fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }
