package step2.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardsTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3])
    fun `n 장의 카드를 뽑는데 성공한다`(count: Int) {
        // given
        val cards = Cards()
        val initSize = cards.cards.size

        // when
        cards.draw(count)

        // then
        assertThat(cards.cards.size).isSameAs(initSize + count)
    }
}
