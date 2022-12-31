package blackjack.domain.player.result

import blackjack.domain.card.CardNumber
import blackjack.domain.card.Cards
import blackjack.domain.card.data.CardsDataSet
import blackjack.view.console.toContentString
import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class PlayerResultTest : FunSpec({
    context("PlayerResult opposite 정상 작동") {
        withData(
            nameFn = { "${it.first}.opposite = ${it.second}" },
            listOf(
                PlayerResult.WIN to DealerResult.LOSE,
                PlayerResult.LOSE to DealerResult.WIN,
                PlayerResult.DRAW to DealerResult.DRAW,
                PlayerResult.NOT_FINISHED to DealerResult.NOT_FINISHED
            )
        ) { (fromResult, toResult) ->
            fromResult.opposite shouldBe toResult
        }
    }

    context("DealerResult opposite 정상 작동") {
        withData(
            nameFn = { "${it.first}.opposite = ${it.second}" },
            listOf(
                DealerResult.WIN to PlayerResult.LOSE,
                DealerResult.LOSE to PlayerResult.WIN,
                DealerResult.DRAW to PlayerResult.DRAW,
                DealerResult.NOT_FINISHED to PlayerResult.NOT_FINISHED
            )
        ) { (fromResult, toResult) ->
            fromResult.opposite shouldBe toResult
        }
    }

    context("player가 Bust인 경우, player가 무조건 ${PlayerResult.LOSE}") {
        withData(
            nameFn = { it.toContentString() },
            listOf(
                Tuple3(
                    CardsDataSet.testData(CardNumber.FIVE, CardNumber.KING, CardNumber.SEVEN),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.NINE),
                    PlayerResult.LOSE
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.FIVE, CardNumber.KING, CardNumber.SEVEN),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.TWO, CardNumber.FIVE),
                    PlayerResult.LOSE
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.FOUR, CardNumber.KING, CardNumber.NINE),
                    CardsDataSet.testData(CardNumber.SEVEN, CardNumber.FIVE, CardNumber.NINE),
                    PlayerResult.LOSE
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.FOUR, CardNumber.KING, CardNumber.NINE),
                    CardsDataSet.testData(CardNumber.FIVE, CardNumber.KING, CardNumber.SEVEN),
                    PlayerResult.LOSE
                ),
            )
        ) { (playerCards, dealerCards, expectedResult) ->
            playerCards.isBust() shouldBe true

            PlayerResult.of(playerCards, dealerCards) shouldBe expectedResult
        }
    }

    context("player가 Bust가 아니고 dealer가 Bust일 경우, player가 무조건 ${PlayerResult.WIN}") {
        withData(
            nameFn = { it.toContentString() },
            listOf(
                Tuple3(
                    CardsDataSet.testData(CardNumber.KING, CardNumber.NINE),
                    CardsDataSet.testData(CardNumber.FIVE, CardNumber.KING, CardNumber.SEVEN),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.KING, CardNumber.TWO, CardNumber.FIVE),
                    CardsDataSet.testData(CardNumber.FIVE, CardNumber.KING, CardNumber.SEVEN),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.SEVEN, CardNumber.ONE, CardNumber.TEN),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.FOUR, CardNumber.KING, CardNumber.NINE),
                    PlayerResult.WIN
                ),
            )
        ) { (playerCards, dealerCards, expectedResult) ->
            playerCards.isBust() shouldBe false
            dealerCards.isBust() shouldBe true

            PlayerResult.of(playerCards, dealerCards) shouldBe expectedResult
        }
    }

    context("player와 dealer의 카드 점수가 다를 경우, 점수 크기 비교에 따라 PlayerResult가 생성된다.") {
        withData(
            nameFn = { it.toContentString() },
            listOf(
                Tuple3(
                    CardsDataSet.testData(CardNumber.NINE, CardNumber.EIGHT),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.EIGHT),
                    PlayerResult.LOSE
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.TEN, CardNumber.NINE),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.SIX),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.SEVEN, CardNumber.SIX, CardNumber.EIGHT),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.SIX),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.KING, CardNumber.JACK),
                    CardsDataSet.testData(CardNumber.SEVEN, CardNumber.SIX, CardNumber.EIGHT),
                    PlayerResult.LOSE
                )
            )
        ) { (playerCards, dealerCards, expectedResult) ->
            playerCards.isBust() shouldBe false
            dealerCards.isBust() shouldBe false

            PlayerResult.of(playerCards, dealerCards) shouldBe expectedResult
        }
    }

    context("player와 dealer의 카드 점수가 같을 경우, 카드 수에 따라 PlayerResult가 생성된다.") {
        withData(
            nameFn = { it.toContentString() },
            listOf(
                Tuple3(
                    CardsDataSet.testData(CardNumber.NINE, CardNumber.EIGHT),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.TWO, CardNumber.FIVE),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.TEN, CardNumber.FOUR, CardNumber.SIX),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.JACK),
                    PlayerResult.LOSE
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.KING, CardNumber.ACE),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.SIX, CardNumber.FOUR),
                    PlayerResult.WIN
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.SIX, CardNumber.EIGHT, CardNumber.SIX),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.JACK),
                    PlayerResult.LOSE
                )
            )
        ) { (playerCards, dealerCards, expectedResult) ->
            playerCards.isBust() shouldBe false
            dealerCards.isBust() shouldBe false

            PlayerResult.of(playerCards, dealerCards) shouldBe expectedResult
        }
    }

    context("player와 dealer 카드 점수, 카드 수 모두 동일할 경우, ${PlayerResult.DRAW}") {
        withData(
            nameFn = { it.toContentString() },
            listOf(
                Tuple3(
                    CardsDataSet.testData(CardNumber.NINE, CardNumber.THREE, CardNumber.FIVE),
                    CardsDataSet.testData(CardNumber.KING, CardNumber.TWO, CardNumber.FIVE),
                    PlayerResult.DRAW
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.KING, CardNumber.ACE),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.QUEEN),
                    PlayerResult.DRAW
                ),
                Tuple3(
                    CardsDataSet.testData(CardNumber.SEVEN, CardNumber.TEN),
                    CardsDataSet.testData(CardNumber.ACE, CardNumber.SIX),
                    PlayerResult.DRAW
                )
            )
        ) { (playerCards, dealerCards, expectedResult) ->
            playerCards.isBust() shouldBe false
            dealerCards.isBust() shouldBe false

            PlayerResult.of(playerCards, dealerCards) shouldBe expectedResult
        }
    }
})

private fun Tuple3<Cards, Cards, PlayerResult>.toContentString(): String =
    "player: ${a.toContentString()}, dealer: ${b.toContentString()}, expected: $c"
