package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayingCardTest {
    @DisplayName("정적 팩토리로 새로운 객체가 생성되지 않고, 같은 주소값을 가져야한다.")
    @Test
    fun constructor() {
        val suit = Suit.CLUBS
        val denomination = Denomination.ACE
        assertThat(PlayingCard.of(denomination, suit))
            .isEqualTo(PlayingCard.of(denomination, suit))
    }

    @DisplayName("누적된 점수가 10 보다 작거나 같으면 ACE 의 점수는 11이 되어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun aceScoreTen(accumulatedScore: Int) {
        val expected = 11
        assertAll(Suit.values().map { PlayingCard.of(Denomination.ACE, it) }
            .map { { assertThat(it.score(accumulatedScore)).isEqualTo(expected) } })
    }

    @DisplayName("누적된 점수가 10 보다 크면 ACE 의 점수는 1이 되어야 한다.")
    @ParameterizedTest
    @ValueSource(ints = [11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21])
    fun aceScoreOne(accumulatedScore: Int) {
        val expected = 1
        assertAll(Suit.values().map { PlayingCard.of(Denomination.ACE, it) }
            .map { { assertThat(it.score(accumulatedScore)).isEqualTo(expected) } })
    }
}
