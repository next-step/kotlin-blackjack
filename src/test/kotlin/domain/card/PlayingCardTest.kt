package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayingCardTest {
    @DisplayName("정적 팩토리로 새로운 객체가 생성되지 않고, 같은 주소값을 가져야한다.")
    @Test
    fun constructor() {
        val suit = Suit.CLUBS
        val denomination = Denomination.ACE
        assertThat(PlayingCard.of(suit, denomination))
            .isEqualTo(PlayingCard.of(suit, denomination))
    }
}
