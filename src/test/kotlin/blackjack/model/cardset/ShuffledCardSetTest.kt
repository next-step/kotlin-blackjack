package blackjack.model.cardset

import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ShuffledCardSetTest {

    @Test
    fun `52장 카드 테스트`() {

        val shuffledCards = ShuffledCardSet.shuffled()

        assertAll(
            { assertThat(Denomination.values()).hasSize(NUMBER_OF_DENOMINATIONS) }, // 13종 숫자
            { assertThat(CardShape.values()).hasSize(NUMBER_OF_CARD_SHAPE) }, // 4종 모양
            { assertThat(shuffledCards).hasSize(NUMBER_OF_CARDS_ONE_SET) }, // 52장
            { assertThat(shuffledCards.distinct()).hasSize(NUMBER_OF_CARDS_ONE_SET) }, // 중복없음
            { assertThat(shuffledCards.filter { it.shape == CardShape.SPADES }).hasSize(NUMBER_OF_DENOMINATIONS) },
            { assertThat(shuffledCards.filter { it.denomination == Denomination.ACE }).hasSize(NUMBER_OF_CARD_SHAPE) }

        )
    }

    companion object {
        private const val NUMBER_OF_DENOMINATIONS = 13
        private const val NUMBER_OF_CARD_SHAPE = 4
        private const val NUMBER_OF_CARDS_ONE_SET = NUMBER_OF_DENOMINATIONS * NUMBER_OF_CARD_SHAPE
    }
}
