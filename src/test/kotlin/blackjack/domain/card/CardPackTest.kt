package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class CardPackTest {

    @Test
    fun `카드가 없는 상태에서, 카드를 뽑으면 에러`() {
        val cardPack = CardPack(EmptyCardFactory())

        assertThatThrownBy { cardPack.poll() }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
