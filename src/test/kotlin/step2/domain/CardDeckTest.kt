package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardDeckTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun `n 장의 카드를 뽑는데 성공한다`(count: Int) {
        // when
        val result = CardDeck.draw(count)

        // then
        assertThat(result.size).isSameAs(count)
    }
}
