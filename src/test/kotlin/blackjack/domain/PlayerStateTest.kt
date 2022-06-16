package blackjack.domain

import isA
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerStateTest {
    @Test
    fun `PlayerState는 점수와 진행 의지에 따른 현재 플레이어의 상태를 나타낸다`() {
        assertAll(
            { assertThat(hitState()).isA<PlayerState.Hit>() },
            { assertThat(stayState()).isA<PlayerState.Stay>() },
            { assertThat(blackjackState()).isA<PlayerState.Blackjack>() },
            { assertThat(bustState()).isA<PlayerState.Bust>() },
        )
    }

    @Test
    fun `PlayerState는 플레이어의 점수를 보관한다`() {
        val score = Score.from(
            PlayingCards.from(
                listOf(
                    PlayingCard(Suit.CLUBS, CardNumber.NINE),
                    PlayingCard(Suit.HEARTS, CardNumber.ACE)
                )
            )
        )

        assertThat(PlayerState.of(score).score).isEqualTo(score)
    }

    @Test
    fun `isFinished를 통해 플레이어가 게임이 종료된 상태인지 알 수 있다`() {
        assertAll(
            { assertThat(hitState().isFinished()).isFalse },
            { assertThat(stayState().isFinished()).isTrue },
            { assertThat(blackjackState().isFinished()).isTrue },
            { assertThat(bustState().isFinished()).isTrue },
        )
    }

    private fun hitState(): PlayerState = PlayerState.of(hitOrStayScore(), true)
    private fun stayState(): PlayerState = PlayerState.of(hitOrStayScore(), false)
    private fun blackjackState(): PlayerState = PlayerState.of(blackjackScore())
    private fun bustState(): PlayerState = PlayerState.of(bustScore())

    private fun hitOrStayScore(): Score = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.NINE),
                PlayingCard(Suit.HEARTS, CardNumber.ACE)
            )
        )
    )

    private fun blackjackScore(): Score = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.KING),
                PlayingCard(Suit.HEARTS, CardNumber.ACE)
            )
        )
    )

    private fun bustScore(): Score = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.KING),
                PlayingCard(Suit.HEARTS, CardNumber.QUEEN),
                PlayingCard(Suit.HEARTS, CardNumber.JACK)
            )
        )
    )
}
