package blackjack.model

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

    @Test
    fun `두 장의 합이 블랙잭인 경우`() {
        val player = Player("moshi").apply {
            requestCard(Card(Denomination.TEN to Shape.CLUB))
            requestCard(Card(Denomination.ACE to Shape.DIAMOND))
        }

        assertThat(player.isBlackJack()).isTrue()
    }
}
