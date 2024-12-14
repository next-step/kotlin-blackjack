package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : FreeSpec({

    "카드 목록의 점수를 합산할 수 있다" - {
        "ACE가 없는 경우" {
            val cards =
                listOf(
                    Card(Suit.HEARTS, Rank.TEN),
                    Card(Suit.SPADES, Rank.JACK),
                    Card(Suit.DIAMONDS, Rank.QUEEN),
                    Card(Suit.CLUBS, Rank.KING),
                )

            val totalScore: Int = ScoreCalculator.calculate(cards)

            totalScore shouldBe 40
        }

        "ACE가 있는 경우 21을 초과하지 않고 21에 가깝도록 1점 혹은 11점을 선택한다" - {
            "ACE가 한장일 때" - {
                "1점을 선택하는 경우" - {
                    listOf(
                        listOf(
                            Card(Suit.HEARTS, Rank.TEN),
                            Card(Suit.SPADES, Rank.TEN),
                            Card(Suit.HEARTS, Rank.ACE),
                        ) to 21,
                        listOf(
                            Card(Suit.HEARTS, Rank.NINE),
                            Card(Suit.SPADES, Rank.TWO),
                            Card(Suit.HEARTS, Rank.ACE),
                        ) to 12,
                        listOf(
                            Card(Suit.HEARTS, Rank.TEN),
                            Card(Suit.SPADES, Rank.NINE),
                            Card(Suit.SPADES, Rank.TWO),
                            Card(Suit.HEARTS, Rank.ACE),
                        ) to 22,
                    ).forEach { cardsTotalScorePair ->
                        val cards = cardsTotalScorePair.first
                        val totalScore = cardsTotalScorePair.second

                        "입력값: cards=$cards, expectedTotalScore=$totalScore" {
                            ScoreCalculator.calculate(cards) shouldBe totalScore
                        }
                    }
                }

                "11점을 선택하는 경우" - {
                    listOf(
                        listOf(
                            Card(Suit.HEARTS, Rank.TEN),
                            Card(Suit.HEARTS, Rank.ACE),
                        ) to 21,
                        listOf(
                            Card(Suit.HEARTS, Rank.NINE),
                            Card(Suit.HEARTS, Rank.ACE),
                        ) to 20,
                    ).forEach { cardsTotalScorePair ->
                        val cards = cardsTotalScorePair.first
                        val totalScore = cardsTotalScorePair.second

                        "입력값: cards=$cards, expectedTotalScore=$totalScore" {
                            ScoreCalculator.calculate(cards) shouldBe totalScore
                        }
                    }
                }
            }

            "ACE가 두장 이상일 때" - {
                listOf(
                    listOf(
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.ACE),
                    ) to 12,
                    listOf(
                        Card(Suit.HEARTS, Rank.TWO),
                        Card(Suit.HEARTS, Rank.ACE),
                        Card(Suit.SPADES, Rank.ACE),
                        Card(Suit.CLUBS, Rank.ACE),
                    ) to 15,
                ).forEach { cardsTotalScorePair ->
                    val cards = cardsTotalScorePair.first
                    val totalScore = cardsTotalScorePair.second
                    "입력값: cards=$cards, expectedTotalScore=$totalScore" {
                        ScoreCalculator.calculate(cards) shouldBe totalScore
                    }
                }
            }
        }
    }
})
