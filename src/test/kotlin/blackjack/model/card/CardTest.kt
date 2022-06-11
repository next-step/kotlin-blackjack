package blackjack.model.card

import blackjack.dummy.toCardSet
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardTest {

    @Test
    fun `CardId 테스트`() {

        repeat(NUMBER_OF_CARDS_ONE_SET) { cardId ->
            val cardId1 = Card.of(cardId)
            val cardId2 = Card.of(cardId1.denomination, cardId1.shape)

            assertAll(
                { assertThat(cardId1.denomination).isEqualTo(cardId2.denomination) }, //  숫자
                { assertThat(cardId1.shape).isEqualTo(cardId2.shape) }, // 모양
                { assertThat(cardId1.cardId).isEqualTo(cardId2.cardId) }, // ID

            )
        }
    }

    @Test
    fun `52장 카드 테스트`() {

        val shuffledCards = Cards.shuffled

        assertAll(
            { assertThat(Denomination.count).isEqualTo(NUMBER_OF_DENOMINATIONS) }, // 13종 숫자
            { assertThat(CardShape.count).isEqualTo(NUMBER_OF_CARD_SHAPE) }, // 4종 모양
            { assertThat(Denomination.values()).hasSize(Denomination.count) }, // 13종 숫자
            { assertThat(CardShape.values()).hasSize(CardShape.count) }, // 4종 모양
            { assertThat(shuffledCards).hasSize(NUMBER_OF_CARDS_ONE_SET) }, // 52장
            { assertThat(shuffledCards.distinct()).hasSize(NUMBER_OF_CARDS_ONE_SET) }, // 중복없음
            { assertThat(shuffledCards.filter { it.shape == CardShape.SPADES }).hasSize(Denomination.count) },
            { assertThat(shuffledCards.filter { it.denomination == Denomination.ACE }).hasSize(CardShape.count) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "'AS,JD','11,21',21",
        "'AS,QH','11,21',21",
        "'AS,KC','11,21',21"
    )
    fun `블랙잭 점수 테스트`(
        cardListString: String,
        scoreListString: String,
        finalScore: Int
    ) {
        val cardSet = cardListString.toCardSet()
        val expectedScoreList = scoreListString.split(",").map { it.toInt() }
        val state = State.of(cardSet)
        val actualScoreList = Scores.of(cardSet).toList()

        assertAll(
            { assertThat(actualScoreList).isEqualTo(expectedScoreList) },
            { assertThat(state.finalScore).isEqualTo(finalScore) },
            { assertThat(state).isInstanceOf(State.BlackJack::class.java) }
        )
    }

    companion object {
        private const val NUMBER_OF_DENOMINATIONS = 13
        private const val NUMBER_OF_CARD_SHAPE = 4
        private const val NUMBER_OF_CARDS_ONE_SET = NUMBER_OF_DENOMINATIONS * NUMBER_OF_CARD_SHAPE
    }
}
