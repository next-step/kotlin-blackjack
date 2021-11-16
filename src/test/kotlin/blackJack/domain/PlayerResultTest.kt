package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {

    @Test
    fun `플레이어가 딜러보다 점수가 높으면 승리`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }
        val dealer = Dealer().apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.WIN)
    }

    @Test
    fun `플레이어가 딜러보다 점수가 낮으면 패배`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }
        val dealer = Dealer().apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.LOSE)
    }

    @Test
    fun `플레이어가 딜러와 점수가 같으면 무승부`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }
        val dealer = Dealer().apply {
            this.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
            this.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.DRAW)
    }
}
