package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@DisplayName("카드 세트 생성기 테스트")
class CardSetGeneratorTest {

    @Test
    fun `카드 세트 생성 기능이 정상 동작`() {
        // given
        val symbols = listOf(CardSymbol.하트, CardSymbol.클로버)
        val numbers = listOf(CardNumber.ONE, CardNumber.TWO, CardNumber.THREE)

        // when
        val generatedCards = CardSetGenerator.generateOneCardSet(symbols, numbers)

        // then
        val cards = listOf(
            Card(CardSymbol.하트, CardNumber.ONE),
            Card(CardSymbol.하트, CardNumber.TWO),
            Card(CardSymbol.하트, CardNumber.THREE),
            Card(CardSymbol.클로버, CardNumber.ONE),
            Card(CardSymbol.클로버, CardNumber.TWO),
            Card(CardSymbol.클로버, CardNumber.THREE)
        )

        assertAll(
            "generate card test",
            { assertThat(generatedCards.containsAll(cards)).isTrue },
            { assertThat(generatedCards.size).isEqualTo(6) }
        )
    }
}
