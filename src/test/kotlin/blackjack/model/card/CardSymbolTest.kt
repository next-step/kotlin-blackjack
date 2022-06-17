package blackjack.model.card

import blackjack.model.card.CardSymbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드 문양 테스트")
class CardSymbolTest {

    @Test
    fun `모든 종류의 카드 문양을 생성하는 기능이 정상 동작`() {
        // given
        val generated = CardSymbol.generateAllKinds()

        // when
        val allKindsCardSymbols = CardSymbol.from(listOf("하트", "클로버", "다이아", "스페이드"))

        // then
        assertThat(generated.containsAll(allKindsCardSymbols)).isTrue
        assertThat(generated.size).isEqualTo(4)
    }
}
