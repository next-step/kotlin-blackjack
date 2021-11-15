package blackjack.domain.state

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드들(Cards)")
internal class CardsTest {

    @Test
    fun `카드들에는 빈값이 들어올 수 있다`() {
        val cards: Cards = Cards.initialize()

        assertAll(
            { assertThat(cards).isNotNull },
            { assertThat(cards).isExactlyInstanceOf(Cards::class.java) },
        )
    }
}
