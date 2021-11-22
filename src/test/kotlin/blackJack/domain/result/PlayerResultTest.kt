package blackJack.domain.result

import blackJack.domain.card.Card
import blackJack.domain.card.Denomination
import blackJack.domain.card.Suit
import blackJack.domain.player.Dealer
import blackJack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerResultTest {

    @Test
    fun `플레이어가 딜러보다 점수가 높으면 승리`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.TWO)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.WIN)
    }

    @Test
    fun `플레이어가 버스터인 경우 플레이어는 패배`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.QUEEN)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.NINE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.FOUR)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(player.isBustPlayer()).isTrue
        assertThat(winOrLose).isEqualTo(WinDrawLose.LOSE)
    }

    @Test
    fun `딜러가 버스터이면 플레이어는 승리`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.TWO)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.EIGHT)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.SEVEN)
            }
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.WIN)
    }

    @Test
    fun `딜러가 버스터이고 플레이어도 버스터이면 플레이어가 승리`() {
        // given
        val player = Player.of("김형준").apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.EIGHT)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.SEVEN)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.EIGHT)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.SEVEN)
            }
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
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.TWO)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.ACE)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
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
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.TWO)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }
        val dealer = Dealer().apply {
            this.receiveCard() {
                Card(Suit.HEARTS, Denomination.TWO)
            }
            this.receiveCard() {
                Card(Suit.SPADES, Denomination.KING)
            }
        }

        // when
        val winOrLose = PlayerResult.winOrLose(player, dealer).winDrawLose

        // then
        assertThat(winOrLose).isEqualTo(WinDrawLose.DRAW)
    }
}
