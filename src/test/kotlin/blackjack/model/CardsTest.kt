package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardsTest {
    @Test
    fun `Cards 기본 생성자를 사용하면 2개의 카드가 생성된다`() {
        val result = Cards()

        assertThat(result.size).isEqualTo(2)
    }
}
