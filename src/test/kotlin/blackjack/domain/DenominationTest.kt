package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DenominationTest {
    @Test
    fun `13가지 종류의 끗수를 가지고 있다`() {
        assertThat(Denomination.values().size).isEqualTo(13)
    }

    @Test
    fun `일반 숫자 테스트`() {
        assertThat(Denomination.TWO.calculate(0)).isEqualTo(2)
        assertThat(Denomination.THREE.calculate(0)).isEqualTo(3)
        assertThat(Denomination.FOUR.calculate(0)).isEqualTo(4)
        assertThat(Denomination.FIVE.calculate(0)).isEqualTo(5)
        assertThat(Denomination.SIX.calculate(0)).isEqualTo(6)
        assertThat(Denomination.SEVEN.calculate(0)).isEqualTo(7)
        assertThat(Denomination.EIGHT.calculate(0)).isEqualTo(8)
        assertThat(Denomination.NINE.calculate(0)).isEqualTo(9)
        assertThat(Denomination.TEN.calculate(0)).isEqualTo(10)
    }

    @Test
    fun `J, Q, K 테스트`() {
        assertThat(Denomination.JACK.calculate(0)).isEqualTo(10)
        assertThat(Denomination.JACK.value).isEqualTo("J")
        assertThat(Denomination.QUEEN.calculate(0)).isEqualTo(10)
        assertThat(Denomination.QUEEN.value).isEqualTo("Q")
        assertThat(Denomination.KING.calculate(0)).isEqualTo(10)
        assertThat(Denomination.KING.value).isEqualTo("K")
    }

    @Test
    fun `Ace를 포함한 카드의 합이 21을 초과하는 경우 Ace는 1로 계산한다`() {
        assertThat(Denomination.ACE.calculate(11)).isEqualTo(1)
    }

    @Test
    fun `Ace를 포함한 카드의 합이 21을 초과하지 않는 경우 Ace는 11로 계산한다`() {
        assertThat(Denomination.ACE.calculate(10)).isEqualTo(11)
    }
}
