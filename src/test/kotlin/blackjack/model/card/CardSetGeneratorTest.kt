package blackjack.model.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 세트 생성기 테스트")
class CardSetGeneratorTest {

    @Test
    fun `카드 세트 생성 기능이 정상 동작`() {
        // given
        val symbols = listOf(CardSymbol.하트, CardSymbol.클로버)
        val numbers = listOf(CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR)

        // when
        val generatedCards = CardSetGenerator.generateOneCardSet(symbols, numbers)

        // then
        val expected = mutableListOf(
            Card(CardSymbol.하트, CardNumber.TWO),
            Card(CardSymbol.하트, CardNumber.THREE),
            Card(CardSymbol.하트, CardNumber.FOUR),
            Card(CardSymbol.클로버, CardNumber.TWO),
            Card(CardSymbol.클로버, CardNumber.THREE),
            Card(CardSymbol.클로버, CardNumber.FOUR)
        )

        assertThat(generatedCards).isEqualTo(Cards(expected))
    }
}
