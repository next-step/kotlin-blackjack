package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberCalculatorTest {

    private lateinit var cardNumberCalculator: CardNumberCalculator

    @BeforeEach
    fun setUp() {
        cardNumberCalculator = CardNumberCalculator()
    }

    @Test
    fun `1부터 10까지의 카드 리스트 숫자의 합을 계산한다`() {
        var cardList = listOf<Card>(
            Card(shape = CardShape.SPADE, number = CardNumber.ONE),
            Card(shape = CardShape.DIAMOND, number = CardNumber.TWO),
            Card(shape = CardShape.HEART, number = CardNumber.THREE),
            Card(shape = CardShape.CLOVER, number = CardNumber.FOUR),
            Card(shape = CardShape.SPADE, number = CardNumber.FIVE),
            Card(shape = CardShape.DIAMOND, number = CardNumber.SIX),
            Card(shape = CardShape.HEART, number = CardNumber.SEVEN),
            Card(shape = CardShape.CLOVER, number = CardNumber.EIGHT),
            Card(shape = CardShape.SPADE, number = CardNumber.NINE),
            Card(shape = CardShape.DIAMOND, number = CardNumber.TEN)
        )


    }

    @Test
    fun `King, Queen, Jack 카드 리스트 숫자의 합을 계산한다`() {
        var cardList = listOf<Card>(
            Card(shape = CardShape.SPADE, number = CardNumber.K),
            Card(shape = CardShape.DIAMOND, number = CardNumber.Q),
            Card(shape = CardShape.HEART, number = CardNumber.J)
        )
    }

    @ParameterizedTest
    @ValueSource(strings = ["ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE", "TEN"])
    fun `카드의 숫자 계산은 카드 숫자를 기본으로 한다`(cardNumber: CardNumber) {
        val startSum = 0

        val actual = cardNumberCalculator.calculateCardNumber(cardNumber, startSum)

        assertThat(actual).isEqualTo(cardNumber.value)
    }

    @ParameterizedTest
    @ValueSource(strings = ["K", "Q", "J"])
    fun `King, Queen, Jack은 각각 10으로 계산한다`(cardNumber: CardNumber) {
        val startSum = 0

        val actual = cardNumberCalculator.calculateCardNumber(cardNumber, startSum)

        assertThat(actual).isEqualTo(cardNumber.value)
    }

    @Test
    fun `Ace는 1 또는 11로 계산할 수 있다 - Ace를 더해서 21이하인 경우는 11로 계산한다`() {
        val ace = CardNumber.A
        val startSum = 0

        val actual = cardNumberCalculator.calculateCardNumber(ace, startSum)

        assertThat(actual).isEqualTo(11)
    }

    @Test
    fun `Ace는 1 또는 11로 계산할 수 있다 - Ace를 더해서 21초과하는 경우는 1로 계산한다`() {
        val ace = CardNumber.A
        val startSum = 11

        val actual = cardNumberCalculator.calculateCardNumber(ace, startSum)

        assertThat(actual).isEqualTo(12)
    }
}
