package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class GameResultCalculatorTest : StringSpec({
    "Dealer busts, all players win" {
        val dealer =
            Dealer().apply {
                addCard(Card(Rank.TEN, Suit.HEART))
                addCard(Card(Rank.TEN, Suit.DIAMOND))
                addCard(Card(Rank.THREE, Suit.SPADE))
            }
        val players =
            Players(
                listOf(
                    Player("pobi").apply {
                        addCard(Card(Rank.FIVE, Suit.CLUB))
                        addCard(Card(Rank.SIX, Suit.SPADE))
                    },
                    Player("jason").apply {
                        addCard(Card(Rank.SEVEN, Suit.DIAMOND))
                        addCard(Card(Rank.FOUR, Suit.CLUB))
                    },
                ),
            )

        val result = GameResultCalculator.calculate(dealer, players)

        result.dealerScore shouldBe dealer.getTotalValue()
        result.playerResults.shouldContainExactly(
            GameResult.PlayerGameResult("pobi", GameResult.Result.WIN),
            GameResult.PlayerGameResult("jason", GameResult.Result.WIN),
        )
    }

    "Dealer does not bust, compare scores" {
        val dealer =
            Dealer().apply {
                addCard(Card(Rank.TEN, Suit.HEART))
                addCard(Card(Rank.SEVEN, Suit.DIAMOND))
            }
        val players =
            Players(
                listOf(
                    Player("pobi").apply {
                        addCard(Card(Rank.NINE, Suit.CLUB))
                        addCard(Card(Rank.SEVEN, Suit.SPADE))
                    },
                    Player("jason").apply {
                        addCard(Card(Rank.EIGHT, Suit.DIAMOND))
                        addCard(Card(Rank.SIX, Suit.CLUB))
                    },
                ),
            )

        val result = GameResultCalculator.calculate(dealer, players)

        result.dealerScore shouldBe dealer.getTotalValue()
        result.playerResults.shouldContainExactly(
            GameResult.PlayerGameResult("pobi", GameResult.Result.LOSE),
            GameResult.PlayerGameResult("jason", GameResult.Result.LOSE),
        )
    }
})
