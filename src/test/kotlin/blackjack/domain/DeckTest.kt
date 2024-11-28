package blackjack.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱이 모두 소멸되면 에러를 던져야 한다`() {
        // given
        val deck = Deck()
        repeat(52) {
            deck.draw()
        }

        // when & then
        assertThatThrownBy { deck.draw() }.isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("덱의 카드들이 모두 사용되었습니다. 다른 덱을 사용해주세요.")
    }

}
