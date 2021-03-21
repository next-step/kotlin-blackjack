package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardExtractorTest {
    private val cardExtractor = CardExtractor()

    @Test
    fun `카드를 추출할때 중복된 카드가 나오지 않는다`() {
        assertThat((1..52).map { cardExtractor.getCard() }.toSet().size).isEqualTo(52)
    }
}
