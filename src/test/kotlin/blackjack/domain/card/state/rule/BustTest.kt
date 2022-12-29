package blackjack.domain.card.state.rule

import blackjack.ClubAce
import blackjack.ClubJack
import blackjack.ClubTen
import blackjack.ClubTwo
import blackjack.domain.card.PlayingCards
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BustTest {
    @Test
    fun `Bust(Ten + Jack + Two = 0)`() {
        // given
        val cards = PlayingCards(ClubTen, ClubJack)
        val hit = Hit(cards)

        // when
        val actual = hit.draw(ClubTwo)

        // then
        assertThat(actual).isInstanceOf(Bust::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(0)
    }

    @Test
    fun `Not Bust(Ten + Jack + ACE = 21)`() {
        // given
        val cards = PlayingCards(ClubTen, ClubJack, ClubAce)

        // when, then
        assertThatThrownBy { Bust(cards) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("버스트가 아닙니다.")
    }
}
