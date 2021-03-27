package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardExtractorTest {
    private val cardExtractor = RandomCardExtractor()

    @Test
    fun `카드를 추출할때 중복된 카드가 나오지 않는다`() {
        assertThat((1..52).map { cardExtractor.getCard() }.toSet().size).isEqualTo(52)
    }

    @Test
    fun `카드를 무제한으로 추출할수 있다`() {
        repeat(100) { cardExtractor.getCard() }
    }
}
