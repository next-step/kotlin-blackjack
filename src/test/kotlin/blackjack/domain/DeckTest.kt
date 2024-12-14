package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {

    @Test
    fun `덱에 카드가 없으면 에러를 던져야 한다`() {
        // given
        val deck = Deck(mutableListOf())

        // when
        val result = assertThrows<IllegalStateException> { deck.draw() }

        // then
        assertThat(result.message).isEqualTo("덱의 카드들이 모두 사용되었습니다. 다른 덱을 사용해주세요.")
    }

}
