package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

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

    companion object {
        private const val NUMBER_OF_DENOMINATIONS = 13
        private const val NUMBER_OF_CARD_SHAPE = 4
        private const val NUMBER_OF_CARDS_ONE_SET = NUMBER_OF_DENOMINATIONS * NUMBER_OF_CARD_SHAPE
    }
}
