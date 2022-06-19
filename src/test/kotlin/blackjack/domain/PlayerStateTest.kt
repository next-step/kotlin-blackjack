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
        val playingCards = PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.NINE),
                PlayingCard(Suit.HEARTS, CardNumber.ACE)
            )
        )

        assertThat(PlayerState.of(playingCards).score.value).isEqualTo(20)
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

    private fun hitState(): PlayerState = PlayerState.of(hitOrStayCards(), true)
    private fun stayState(): PlayerState = PlayerState.of(hitOrStayCards(), false)
    private fun blackjackState(): PlayerState = PlayerState.of(blackjackCards())
    private fun bustState(): PlayerState = PlayerState.of(bustCards())

    private fun hitOrStayCards(): PlayingCards = PlayingCards.from(
        listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
    )

    private fun blackjackCards(): PlayingCards = PlayingCards.from(
        listOf(
            PlayingCard(Suit.CLUBS, CardNumber.KING),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
    )

    private fun bustCards(): PlayingCards = PlayingCards.from(
        listOf(
            PlayingCard(Suit.CLUBS, CardNumber.KING),
            PlayingCard(Suit.HEARTS, CardNumber.QUEEN),
            PlayingCard(Suit.HEARTS, CardNumber.JACK)
        )
    )
}
