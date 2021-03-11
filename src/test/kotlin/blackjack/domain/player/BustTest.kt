package blackjack.domain.player

import blackjack.domain.deck.*
import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class BustTest {
    @Test
    fun `draw 불가`() {
        val bust = Bust(Cards(listOf()))

        assertThrows<IllegalArgumentException> {
            bust.draw(Deck(Stack()))
        }
    }

    @Test
    fun `stay 불가`() {
        val bust = Bust(Cards(listOf()))

        assertThrows<IllegalArgumentException> {
            bust.stay()
        }
    }

    @Test
    fun `종료상태`() {
        val bust = Bust(Cards(listOf()))

        Assertions.assertThat(bust.isFinished()).isTrue()
    }

    @Test
    fun `계산 테스트`() {
        val bust = Bust(
            Cards(
                listOf(
                    Card(Denomination.TEN, Suit.CLOVER),
                    Card(Denomination.TEN, Suit.CLOVER),
                    Card(Denomination.TEN, Suit.CLOVER)
                )
            )
        )

        Assertions.assertThat(bust.calculate()).isEqualTo(30)
    }
}
