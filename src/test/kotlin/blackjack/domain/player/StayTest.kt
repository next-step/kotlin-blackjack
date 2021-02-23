package blackjack.domain.player


import blackjack.domain.deck.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class StayTest {
    @Test
    fun `draw 불가`() {
        val stay = Stay(Cards(listOf()))

        assertThrows<IllegalArgumentException> {
            stay.draw(Deck(Stack()))
        }
    }

    @Test
    fun `stay 불가`() {
        val stay = Stay(Cards(listOf()))

        assertThrows<IllegalArgumentException> {
            stay.stay()
        }
    }

    @Test
    fun `종료상태`() {
        val stay = Stay(Cards(listOf()))

        assertThat(stay.isFinished()).isTrue()
    }

    @Test
    fun `계산 테스트`() {
        val stay = Stay(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))

        assertThat(stay.calculate()).isEqualTo(21)
    }
}
