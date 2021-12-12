package blackjack.util

import blackjack.domain.entity.Card
import blackjack.domain.entity.Player
import blackjack.domain.entity.enums.Denomination
import blackjack.domain.entity.enums.Suit
import blackjack.domain.util.PlayerCardCalculation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerCardCalculationTest {

    @Test
    fun `플레이어의 카드가 2,5,4,8,ACE,ACE 일때 블랙잭이 나오는지 확인 합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.TWO),
                Card(Suit.SPADE, Denomination.FIVE),
                Card(Suit.SPADE, Denomination.FOUR),
                Card(Suit.SPADE, Denomination.EIGHT),
                Card(Suit.SPADE, Denomination.ACE),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(PlayerCardCalculation.calculation(player)).isEqualTo(21)
    }

    @Test
    fun `플레이어의 카드가 10,ACE 일때 블랙잭이 나오는지 확인 합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.TEN),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(PlayerCardCalculation.calculation(player)).isEqualTo(21)
    }

    @Test
    fun `플레이어의 카드가 7,8,ACE 일때 에이스가 1로 변환되어 16이 나오는지 확인합니다`() {
        val player = Player(
            "kim",
            mutableListOf(
                Card(Suit.SPADE, Denomination.SEVEN),
                Card(Suit.SPADE, Denomination.EIGHT),
                Card(Suit.SPADE, Denomination.ACE)
            )
        )
        assertThat(PlayerCardCalculation.calculation(player)).isEqualTo(16)
    }
}
