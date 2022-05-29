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
})
