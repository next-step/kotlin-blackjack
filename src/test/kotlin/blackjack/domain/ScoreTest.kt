package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ScoreTest {
    @Test
    fun `Score은 PlayingCards를 받아 현재 점수를 계산해 보관한다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.EIGHT)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(17)
    }

    @Test
    fun `ACE 카드의 점수를 11로 계산했을 때 총점이 21을 초과하지 않는다면, ACE는 11점으로 계산된다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.THREE),
            PlayingCard(Suit.HEARTS, CardNumber.FOUR),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(18)
    }

    @Test
    fun `ACE 카드의 점수를 11로 계산했을 때 총점이 21을 초과하면, ACE는 1점으로 계산된다`() {
        val cards = listOf(
            PlayingCard(Suit.CLUBS, CardNumber.KING),
            PlayingCard(Suit.HEARTS, CardNumber.JACK),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
        val playingCards = PlayingCards.from(cards)

        assertThat(Score.from(playingCards).value).isEqualTo(21)
    }

    @Test
    fun `isBlackjack을 통해 현재 점수가 블랙잭인지 확인할 수 있다`() {
        assertThat(Score.from(Score.BLACKJACK_SCORE).isBlackjack()).isTrue
    }

    @Test
    fun `isBust를 통해 현재 점수가 버스트인지 확인할 수 있다`() {
        assertThat(Score.from(22).isBust()).isTrue
    }

    @Test
    fun `canAddMore를 통해 카드를 더 가져올 수 있는 상태인지 확인할 수 있다`() {
        assertThat(Score.from(15).canAddMore()).isTrue
    }

    @ParameterizedTest
    @CsvSource("1,2", "10,10", "5,45")
    fun `등호 및 부등호 연산자를 통해 점수를 비교할 수 있다`(first: Int, second: Int) {
        val score1 = Score.from(first)
        val score2 = Score.from(second)
        assertAll(
            { assertThat(score1 > score2).isEqualTo(first > second) },
            { assertThat(score1 == score2).isEqualTo(first == second) },
            { assertThat(score1 < score2).isEqualTo(first < second) }
        )
    }
}
