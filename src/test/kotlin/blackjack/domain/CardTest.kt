package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `같은 카드 2번 드로우 하면 null 값이 출력`() {
        val firstDraw = Card.drawCard(
            Pattern.SPADE,
            "4"
        )
        val secondDraw = Card.drawCard(
            Pattern.SPADE,
            "4"
        )

        assertThat(firstDraw).isNotNull
        assertThat(secondDraw).isNull()
    }
}
