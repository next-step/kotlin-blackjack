package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GamerTest {

    @Test
    fun `ACE 가 두 장인 경우`() {
        val player = Player("moshi").apply {
            requestCard(Card(Denomination.ACE to Shape.CLUB))
            requestCard(Card(Denomination.ACE to Shape.DIAMOND))
        }

        assertThat(player.calculatePoint()).isEqualTo(Point(12))
    }
}
