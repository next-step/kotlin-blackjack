package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerStateTest {
    private val hitOrStayScore = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
            )
        )
    )
    private val blackjackScore = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.KING),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
            )
        )
    )
    private val bustScore = Score.from(
        PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.KING),
                PlayingCard.of(Suit.HEARTS, CardNumber.QUEEN),
                PlayingCard.of(Suit.HEARTS, CardNumber.JACK)
            )
        )
    )

    private val hitState = PlayerState.of(hitOrStayScore, true)
    private val stayState = PlayerState.of(hitOrStayScore, false)
    private val blackjackState = PlayerState.of(blackjackScore)
    private val bustState = PlayerState.of(bustScore)

    @Test
    fun `PlayerState는 점수와 진행 의지에 따른 현재 플레이어의 상태를 나타낸다`() {
        assertAll(
            { assertThat(hitState).isInstanceOf(PlayerState.Hit::class.java) },
            { assertThat(stayState).isInstanceOf(PlayerState.Stay::class.java) },
            { assertThat(blackjackState).isInstanceOf(PlayerState.Blackjack::class.java) },
            { assertThat(bustState).isInstanceOf(PlayerState.Bust::class.java) },
        )
    }

    @Test
    fun `PlayerState는 플레이어의 점수를 보관한다`() {
        val score = Score.from(
            PlayingCards.from(
                listOf(
                    PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                    PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
                )
            )
        )

        assertThat(PlayerState.of(score).score).isEqualTo(score)
    }

    @Test
    fun `isFinished를 통해 플레이어가 게임이 종료된 상태인지 알 수 있다`() {
        assertAll(
            { assertThat(hitState.isFinished()).isFalse },
            { assertThat(stayState.isFinished()).isTrue },
            { assertThat(blackjackState.isFinished()).isTrue },
            { assertThat(bustState.isFinished()).isTrue },
        )
    }
}
