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
        "4개면 4점으로 계산된다" {
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

        "3개면 3점으로 계산된다" {
            val score = Score(
                listOf(
                    Card(Suite.DIAMONDS, Denomination.ACE),
                    Card(Suite.CLUBS, Denomination.ACE),
                    Card(Suite.HEARTS, Denomination.ACE),
                    Card(Suite.CLUBS, Denomination.NINE),
                )
            )
            score.sum shouldBe 12
        }

        "2개인 경우" - {
            "2로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.SPADES, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.TWO),
                        Card(Suite.HEARTS, Denomination.FIVE),
                    )
                )
                score.sum shouldBe 9
            }

            "12로 판단" {
                val score = Score(
                    listOf(
                        Card(Suite.SPADES, Denomination.ACE),
                        Card(Suite.HEARTS, Denomination.ACE),
                        Card(Suite.CLUBS, Denomination.NINE),
                    )
                )
                score.sum shouldBe 21
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
                        Card(Suite.CLUBS, Denomination.TEN),
                    )
                )
                score.sum shouldBe 21
            }
        }
    }

    "카드가 없는 경우 합은 0이다" {
        val score = Score(emptyList())
        score.sum shouldBe 0
    }
})
