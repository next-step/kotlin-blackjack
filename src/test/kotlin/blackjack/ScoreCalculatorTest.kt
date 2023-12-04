package blackjack

import blackjack.card.AceCard
import blackjack.card.BlackJackCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class ScoreCalculatorTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `숫자 카드의 점수는 카드의 숫자이다`() {
        val card = NormalCard(1, CardPattern.CLOVER)
        val score = scoreCalculator.calculateGameScore(listOf(card))
        score shouldBe card.number
    }

    @Test
    fun `여러 장의 숫자 카드의 점수를 합산하여 반환한다`() {
        val cardList = listOf(
            NormalCard(1, CardPattern.CLOVER),
            NormalCard(2, CardPattern.CLOVER)
        )
        val score = scoreCalculator.calculateGameScore(cardList)
        score shouldBe 3
    }

    @Test
    fun `그림 카드는 10점으로 계산한다`() {
        val cardList = listOf(
            PictureCard(CardPicture.KING, CardPattern.CLOVER),
        )
        val score = scoreCalculator.calculateGameScore(cardList)
        score shouldBe 10
    }

    @Test
    fun `숫자 카드의 점수와 그림 카드의 점수를 계산한다`() {
        val cardList = listOf(
            NormalCard(7, CardPattern.CLOVER),
            PictureCard(CardPicture.KING, CardPattern.CLOVER),
        )
        val score = scoreCalculator.calculateGameScore(cardList)
        score shouldBe 17
    }

    @ParameterizedTest
    @MethodSource("createCardList")
    fun `에이스 카드는 1과 11 중 현재 점수에 더했을 때 21과 가장 가까운 숫자로 계산된다`(cards: List<BlackJackCard>, expected: Int) {
        val score = scoreCalculator.calculateGameScore(cards)
        score shouldBe expected
    }

    companion object {
        @JvmStatic
        fun createCardList(): List<Arguments> {
            return listOf(
                Arguments.of(
                    listOf(
                        NormalCard(7, CardPattern.CLOVER),
                        PictureCard(CardPicture.KING, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    18
                ),
                Arguments.of(
                    listOf(
                        NormalCard(9, CardPattern.CLOVER),
                        PictureCard(CardPicture.KING, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    20
                ),
                Arguments.of(
                    listOf(
                        NormalCard(3, CardPattern.CLOVER),
                        NormalCard(3, CardPattern.SPADE),
                        PictureCard(CardPicture.KING, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    17
                ),
                Arguments.of(
                    listOf(
                        NormalCard(7, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    18
                ),
                Arguments.of(
                    listOf(
                        PictureCard(CardPicture.KING, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    21
                ),
                Arguments.of(
                    listOf(
                        AceCard(CardPattern.SPADE),
                        AceCard(CardPattern.CLOVER)
                    ),
                    22
                ),
                Arguments.of(
                    listOf(
                        PictureCard(CardPicture.KING, CardPattern.CLOVER),
                        PictureCard(CardPicture.QUEEN, CardPattern.CLOVER),
                        PictureCard(CardPicture.JACK, CardPattern.CLOVER),
                        AceCard(CardPattern.CLOVER)
                    ),
                    31
                ),
            )
        }
    }
}
