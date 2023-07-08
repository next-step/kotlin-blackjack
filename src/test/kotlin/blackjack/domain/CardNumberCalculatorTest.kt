package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardNumberCalculatorTest {

    private lateinit var cardNumberCalculator: CardNumberCalculator

    @BeforeEach
    fun setUp() {
        cardNumberCalculator = CardNumberCalculator()
    }

    @Test
    fun `플레이어가 가진 카드 리스트의 숫자합을 계산한다`() {
        var cardList = listOf(
            Card(shape = CardShape.DIAMOND, number = CardNumber.TEN),
            Card(shape = CardShape.SPADE, number = CardNumber.NINE),
            Card(shape = CardShape.CLOVER, number = CardNumber.EIGHT)
        )

        val actual = cardNumberCalculator.calculateSumOfCardNumbers(cardList)

        assertThat(actual).isEqualTo(27)
    }

    @Test
    fun `King, Queen, Jack 카드 리스트 숫자의 합을 계산한다`() {
        var cardList = listOf(
            Card(shape = CardShape.SPADE, number = CardNumber.K),
            Card(shape = CardShape.DIAMOND, number = CardNumber.Q),
            Card(shape = CardShape.HEART, number = CardNumber.J)
        )

        val actual = cardNumberCalculator.calculateSumOfCardNumbers(cardList)

        assertThat(actual).isEqualTo(30)
    }

    @ParameterizedTest
    @EnumSource(CardNumber::class, mode = EnumSource.Mode.EXCLUDE, names = ["A"])
    fun `카드의 숫자 계산은 카드 숫자를 기본으로 한다`(cardNumber: CardNumber) {
        val cardList = listOf(Card(shape = CardShape.DIAMOND, cardNumber))
        val actual = cardNumberCalculator.calculateSumOfCardNumbers(cardList)

        assertThat(actual).isEqualTo(cardNumber.value)
    }

    @Test
    fun `Ace는 1 또는 11로 계산할 수 있다 - Ace를 더해서 21이하인 경우는 11로 계산한다`() {
        val ace = CardNumber.A
        val cardList = listOf(Card(shape = CardShape.DIAMOND, ace))
        val actual = cardNumberCalculator.calculateSumOfCardNumbers(cardList)

        assertThat(actual).isEqualTo(11)
    }

    @Test
    fun `Ace는 1 또는 11로 계산할 수 있다 - Ace를 더해서 21초과하는 경우는 1로 계산한다`() {
        val cardList = listOf(
            Card(shape = CardShape.DIAMOND, CardNumber.TEN),
            Card(shape = CardShape.DIAMOND, CardNumber.TWO),
            Card(shape = CardShape.DIAMOND, CardNumber.A)
        )
        val actual = cardNumberCalculator.calculateSumOfCardNumbers(cardList)

        assertThat(actual).isEqualTo(13)
    }
}
