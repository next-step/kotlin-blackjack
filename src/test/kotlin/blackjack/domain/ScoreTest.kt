package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

data class CardCase(
    val name: String,
    val cards: List<Card>,
    val isBlackjack: Boolean,
    val isBust: Boolean,
)

internal class ScoreTest : FreeSpec({
    val cardCases = listOf(
        CardCase(
            name = "21",
            cards = listOf(
                Card(Suite.DIAMONDS, Denomination.TWO),
                Card(Suite.SPADES, Denomination.QUEEN),
                Card(Suite.SPADES, Denomination.NINE),
            ),
            isBlackjack = true,
            isBust = false,
        ),
        CardCase(
            name = "21 미만",
            cards = listOf(
                Card(Suite.DIAMONDS, Denomination.TWO),
                Card(Suite.SPADES, Denomination.QUEEN),
                Card(Suite.SPADES, Denomination.EIGHT),
            ),
            isBlackjack = false,
            isBust = false,
        ),
        CardCase(
            name = "21 초과",
            cards = listOf(
                Card(Suite.DIAMONDS, Denomination.SEVEN),
                Card(Suite.SPADES, Denomination.QUEEN),
                Card(Suite.SPADES, Denomination.SIX),
            ),
            isBlackjack = false,
            isBust = true,
        ),
    )

    "카드의 총 합이" - {
        cardCases.forEach {
            "${it.name} 이면 블랙잭 여부는 ${it.isBlackjack}" {
                val score = Score(it.cards)
                score.isBlackjack shouldBe it.isBlackjack
            }
        }

        cardCases.forEach {
            "${it.name} 이면 버스트 여부는 ${it.isBust}" {
                val score = Score(it.cards)
                score.isBust shouldBe it.isBust
            }
        }
    }

    "Ace 의 개수에 따른 점수계산" - {
        "모든 경우가 bust 이면 가장 작은 점수를 반환한다" {
            val score = Score(
                listOf(
                    Card(Suite.DIAMONDS, Denomination.ACE),
                    Card(Suite.CLUBS, Denomination.ACE),
                    Card(Suite.CLUBS, Denomination.ACE),
                    Card(Suite.HEARTS, Denomination.SIX),
                    Card(Suite.HEARTS, Denomination.TEN),
                    Card(Suite.HEARTS, Denomination.FIVE),
                )
            )
            score.sum shouldBe 24
        }

        "4개인 경우" - {
            "14로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.DIAMONDS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.SEVEN),
                    )
                )
                score.sum shouldBe 21
            }

            "4로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.DIAMONDS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.JACK),
                    )
                )
                score.sum shouldBe 14
            }
        }

        "3개인 경우" - {
            "13으로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.DIAMONDS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.EIGHT),
                    )
                )
                score.sum shouldBe 21
            }

            "3으로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.DIAMONDS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.KING),
                    )
                )
                score.sum shouldBe 13
            }
        }

        "2개인 경우" - {
            "2로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.SPADES, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.FIVE),
                        Card(Suite.HEARTS, Denomination.TEN),
                    )
                )
                score.sum shouldBe 17
            }

            "12로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.SPADES, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.EIGHT),
                    )
                )
                score.sum shouldBe 20
            }
        }

        "1개인 경우" - {
            "1로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.CLUBS, Denomination.KING),
                        Card(Suite.HEARTS, Denomination.SIX),
                        Card(Suite.SPADES, Denomination.ACE),
                    )
                )
                score.sum shouldBe 17
            }

            "11로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.DIAMONDS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.NINE),
                    )
                )
                score.sum shouldBe 20
            }
        }
    }

    "카드가 없는 경우 합은 0이다" {
        val score = Score(emptyList())
        score.sum shouldBe 0
    }
})
