package blackjack

import blackjack.CardsCompound.Companion.BEST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsCompoundTest {
    @Test
    fun `CardsCompound에 숫자 추가시 관련 조합을 맞춰준다`() {
        val empty = CardsCompound.get()
        val first = empty.addNumber(CardNumber.KING)
        assertThat(first.bestNumber).isEqualTo(10)
        val second = first.addNumber(CardNumber.FIVE)
        assertThat(second.bestNumber).isEqualTo(15)
        val third = second.addNumber(CardNumber.FOUR)
        assertThat(third.bestNumber).isEqualTo(19)
        val busted = third.addNumber(CardNumber.THREE)
        assertThat(busted.bestNumber).isGreaterThan(BEST)
    }

    @Test
    fun `CardsCompound에 에이스 추가 시 1과 11을 모두 고려한다`() {
        val empty = CardsCompound.get()
        val king = empty.addNumber(CardNumber.KING)

        val kingAce = king.addNumber(CardNumber.ACE)
        assertThat(kingAce.bestNumber).isEqualTo(21)

        val kingAce2 = kingAce.addNumber(CardNumber.ACE)
        assertThat(kingAce2.bestNumber).isEqualTo(12)
    }
}
