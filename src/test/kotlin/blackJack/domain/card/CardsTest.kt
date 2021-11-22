package blackJack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CardsTest {

    @Test
    fun `카드 뭉치는 총 52장이다`() {
        // given
        val cards = Cards.create()

        // when
        val cardsSize = cards.size

        // then
        assertThat(cardsSize).isEqualTo(52)
    }

    @Test
    fun `카드 뭉치(52장)에서 1장을 빼면 51장이다`() {
        // given
        val cards = Cards.create()
        val card = Card(Suit.DIAMONDS, Denomination.KING)

        // when
        val cardsSize = (cards - card).size

        // then
        assertThat(cardsSize).isEqualTo(51)
    }

    @Test
    fun `카드 합계가 일치하는지 테스트`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.DIAMONDS, Denomination.FIVE),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
            )
        )

        // when
        val sumCards = cards.sumCards()

        // then
        assertThat(sumCards).isEqualTo(21)
    }

    @Test
    fun `에이스가 한개 일 때 총합이 일치하는지 테스트`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.DIAMONDS, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.KING),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
            )
        )

        // when
        val sumCards = cards.sumCards()

        // then
        assertThat(sumCards).isEqualTo(27)
    }

    @Test
    fun `에이스가 두개 이상일 때 총합에 따라 각각 점수가 달라야 한다`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.DIAMONDS, Denomination.ACE),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
                Card(Suit.DIAMONDS, Denomination.ACE)
            )
        )

        // when
        val sumCards = cards.sumCards()

        // then
        assertThat(sumCards).isEqualTo(20)
    }

    @Test
    fun `에이스가 세개 일 때 총합이 일치하는지 테스트`() {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.DIAMONDS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.KING),
                Card(Suit.DIAMONDS, Denomination.EIGHT),
            )
        )

        // when
        val sumCards = cards.sumCards()

        // then
        assertThat(sumCards).isEqualTo(21)
    }

    @ParameterizedTest
    @MethodSource("sumProvider")
    fun `에이스가 네개 일 때 총합이 일치하는지 테스트`(denomination: Denomination, sum: Int) {
        // given
        val cards = Cards(
            listOf(
                Card(Suit.DIAMONDS, Denomination.ACE),
                Card(Suit.SPADES, Denomination.ACE),
                Card(Suit.CLUBS, Denomination.ACE),
                Card(Suit.DIAMONDS, denomination),
                Card(Suit.HEARTS, Denomination.ACE)
            )
        )

        // when
        val sumCards = cards.sumCards()

        // then
        assertThat(sumCards).isEqualTo(sum)
    }

    companion object {
        @JvmStatic
        fun sumProvider(): List<Arguments> = listOf(
            Arguments.of(Denomination.SEVEN, 21),
            Arguments.of(Denomination.KING, 14),
            Arguments.of(Denomination.SIX, 20),
            Arguments.of(Denomination.EIGHT, 12)
        )
    }
}
