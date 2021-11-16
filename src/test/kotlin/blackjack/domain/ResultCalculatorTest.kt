package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class ResultCalculatorTest {
    @Test
    fun `CardNumber 로 ACE 한장, KING 한장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 21을 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.KING))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 20을 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(20)
    }

    @Test
    fun `CardNumber 로 JACK 한장, QUEEN 한장, ACE를 한장 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 21을 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.JACK))
        cards.add(Card(CardSymbol.HEART, CardNumber.QUEEN))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(21)
    }

    @Test
    fun `CardNumber 로 ACE 한장, TWO 한장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 13를 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.TWO))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 두장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 12를 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.ACE))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(12)
    }

    @Test
    fun `CardNumber 로 ACE 세장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 13를 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(13)
    }

    @Test
    fun `CardNumber 로 ACE 네장을 가지고 있는 cards에 대해 getCardsResultPoint() 메소드를 호출하면 14를 리턴한다`() {
        val resultCalculator = ResultCalculator()

        val cards = PlayerCards()
        cards.add(Card(CardSymbol.SPADE, CardNumber.ACE))
        cards.add(Card(CardSymbol.HEART, CardNumber.ACE))
        cards.add(Card(CardSymbol.DIAMOND, CardNumber.ACE))
        cards.add(Card(CardSymbol.CLUBS, CardNumber.ACE))

        assertThat(resultCalculator.getCardsResultPoint(cards)).isEqualTo(14)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10, 20, 21])
    fun `21이하의 CardPoint 합계에 대해 isBustedCardsResultPoint()를 호출하면 false 를 리턴한다`(sum: Int) {
        val resultCalculator = ResultCalculator()
        assertThat(resultCalculator.isBustedCardsResultPoint(sum)).isFalse
    }

    @ParameterizedTest
    @ValueSource(ints = [22, 23, 24, 25, 26, 27, 28, 29, 30])
    fun `22이상의 CardPoint 합계에 대해 isBustedCardsResultPoint()를 호출하면 true 를 리턴한다`(sum: Int) {
        val resultCalculator = ResultCalculator()
        assertThat(resultCalculator.isBustedCardsResultPoint(sum)).isTrue
    }
}
