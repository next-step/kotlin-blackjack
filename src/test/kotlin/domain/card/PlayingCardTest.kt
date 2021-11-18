package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayingCardTest {
    @DisplayName("정적 팩토리로 새로운 객체가 생성되지 않고, 같은 주소값을 가져야한다.")
    @Test
    fun constructor() {
        val suit = Suit.CLUBS
        val denomination = Denomination.ACE
        assertThat(PlayingCard.of(denomination, suit))
            .isEqualTo(PlayingCard.of(denomination, suit))
    }

    @DisplayName("Denomination 과 같은 score 를 반환해야 한다.")
    @Test
    fun aceScoreOne() {
        val expected = 1
        assertAll(Suit.values().map { PlayingCard.of(Denomination.ACE, it) }
            .map { { assertThat(it.score()).isEqualTo(expected) } })
    }
}
