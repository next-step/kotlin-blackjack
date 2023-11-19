package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어의 최고 득점 계산이 잘 이뤄진다`() {
        val player = Player("")
        player.getInitialCards(
            listOf(
                PlayingCard(Suits.DIAMOND, CardNumber.KING),
                PlayingCard(Suits.CLOVER, CardNumber.KING)
            )
        )
        assertThat(player.playerCards.getBestScore()).isEqualTo(20)

        val player2 = Player("")
        player2.getInitialCards(
            listOf(
                PlayingCard(Suits.DIAMOND, CardNumber.KING),
                PlayingCard(Suits.CLOVER, CardNumber.ACE)
            )
        )
        assertThat(player2.playerCards.getBestScore()).isEqualTo(21)
    }

    @Test
    fun `플레이어의 득점이 21을 넘어가는 순간 버스트된다`() {
        val player = Player("")
        player.getInitialCards(
            listOf(
                PlayingCard(Suits.DIAMOND, CardNumber.KING),
                PlayingCard(Suits.CLOVER, CardNumber.KING)
            )
        )
        player.hit(PlayingCard(Suits.DIAMOND, CardNumber.TWO))
        assertThat(player.isBusted).isTrue()
    }

    @Test
    fun `플레이어의 득점이 21을 넘지 않으면 버스트가 아니다`() {
        val player = Player("")
        player.getInitialCards(
            listOf(
                PlayingCard(Suits.DIAMOND, CardNumber.KING),
                PlayingCard(Suits.CLOVER, CardNumber.KING)
            )
        )
        player.hit(PlayingCard(Suits.DIAMOND, CardNumber.ACE))
        assertThat(player.isBusted).isFalse()
    }
}
