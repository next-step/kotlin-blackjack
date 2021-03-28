package blackjack.domain

import blackjack.domain.card.EmptyCardFactory
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

internal class CardPackTest {

    @Test
    fun `카드가 없는 상태에서, 카드를 뽑으면 에러`() {
        val cardPack = CardPack(EmptyCardFactory())

        Assertions.assertThatThrownBy { cardPack.poll() }
            .isInstanceOf(IllegalStateException::class.java)
    }
}
