package blackjack.domain

import blackjack.domain.deck.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DenominationTest {
    @Test
    fun `13가지 종류의 끗수를 가지고 있다`() {
        assertThat(Denomination.values().size).isEqualTo(13)
    }

    @Test
    fun `일반 숫자 테스트`() {
        assertThat(Denomination.scoreOf(Denomination.TWO)).isEqualTo(2)
        assertThat(Denomination.scoreOf(Denomination.THREE)).isEqualTo(3)
        assertThat(Denomination.scoreOf(Denomination.FOUR)).isEqualTo(4)
        assertThat(Denomination.scoreOf(Denomination.FIVE)).isEqualTo(5)
        assertThat(Denomination.scoreOf(Denomination.SIX)).isEqualTo(6)
        assertThat(Denomination.scoreOf(Denomination.SEVEN)).isEqualTo(7)
        assertThat(Denomination.scoreOf(Denomination.EIGHT)).isEqualTo(8)
        assertThat(Denomination.scoreOf(Denomination.NINE)).isEqualTo(9)
        assertThat(Denomination.scoreOf(Denomination.TEN)).isEqualTo(10)
    }

    @Test
    fun `J, Q, K 테스트`() {
        assertThat(Denomination.scoreOf(Denomination.JACK)).isEqualTo(10)
        assertThat(Denomination.scoreOf(Denomination.QUEEN)).isEqualTo(10)
        assertThat(Denomination.scoreOf(Denomination.KING)).isEqualTo(10)
    }

    @Test
    fun `Ace를 포함한 카드의 합이 21을 초과하는 경우 Ace는 1로 계산한다`() {
        assertThat(Denomination.scoreOf(Denomination.ACE, 11)).isEqualTo(1)
    }

    @Test
    fun `Ace를 포함한 카드의 합이 21을 초과하지 않는 경우 Ace는 11로 계산한다`() {
        assertThat(Denomination.scoreOf(Denomination.ACE, 10)).isEqualTo(11)
    }
}
