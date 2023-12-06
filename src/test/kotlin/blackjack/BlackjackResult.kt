package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Player
import blackjack.domain.result.BlackjackResult
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

data class DealerCompeteTestData(
    val dealerHand: Hand,
    val playerHand: Hand,
    val playerBet: Int,
    val expectedEarning: Int
)

class CompareResultTest : FunSpec({
    context("BlackjackResult는 게임의 결과를 반환") {
        withData(
            listOf(
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.NINE, CardShape.DIAMOND),
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.FIVE, CardShape.SPADE),
                            Card(CardNumber.JACK, CardShape.SPADE),
                        ),
                    ),
                    playerBet = 10000,
                    expectedEarning = -10000
                ),
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                            Card(CardNumber.JACK, CardShape.DIAMOND),
                            Card(CardNumber.QUEEN, CardShape.DIAMOND),
                            Card(CardNumber.TWO, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.HEART),
                            Card(CardNumber.JACK, CardShape.HEART),
                            Card(CardNumber.QUEEN, CardShape.HEART),
                            Card(CardNumber.TWO, CardShape.HEART),
                        ),
                    ),
                    playerBet = 10000,
                    expectedEarning = 10000
                ),
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.JACK, CardShape.DIAMOND),
                            Card(CardNumber.ACE, CardShape.DIAMOND),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.ACE, CardShape.SPADE),
                            Card(CardNumber.JACK, CardShape.SPADE),
                        ),
                    ),
                    playerBet = 10000,
                    expectedEarning = 0
                ),
                DealerCompeteTestData(
                    dealerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.SEVEN, CardShape.SPADE),
                            Card(CardNumber.JACK, CardShape.SPADE),
                        ),
                    ),
                    playerHand = Hand(
                        cards = listOf(
                            Card(CardNumber.TWO, CardShape.CLOVER),
                            Card(CardNumber.SIX, CardShape.CLOVER),
                            Card(CardNumber.EIGHT, CardShape.CLOVER),
                            Card(CardNumber.EIGHT, CardShape.CLOVER),
                        ),
                    ),
                    playerBet = 10000,
                    expectedEarning = -10000
                ),
            )
        ) { (dealerHand, playerHand, playerBet, expectedEarning) ->
            val dealer = Dealer(dealerHand)
            val player = Player("", playerBet, playerHand)

            val actual = BlackjackResult(dealer = dealer, players = listOf(player))

            actual.playerCompeteResults[0].getEarnMoney() shouldBe expectedEarning
        }
    }
})
