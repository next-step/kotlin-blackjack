package blackjack.domain.player

import blackjack.domain.deck.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

internal class BlackjackTest {

    @Test
    fun draw_불가() {
        val blackjack =
            Blackjack(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        assertThrows<IllegalArgumentException> {
            blackjack.draw(Deck(Stack()))
        }
    }

    @Test
    fun stay_불가() {
        val blackjack =
            Blackjack(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        assertThrows<IllegalArgumentException> {
            blackjack.draw(Deck(Stack()))
        }
    }

    @Test
    fun 종료_상태_확인() {
        val blackjack =
            Blackjack(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        assertThat(blackjack.isFinished()).isTrue()
    }

    @Test
    fun `계산 테스트`() {
        val blackjack =
            Blackjack(Cards(listOf(Card(Denomination.TEN, Suit.CLOVER), Card(Denomination.ACE, Suit.CLOVER))))
        assertThat(blackjack.calculate()).isEqualTo(21)
    }
}
