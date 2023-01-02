package blackjack.domain.card.state.rule

import blackjack.SpadeAce
import blackjack.SpadeFive
import blackjack.SpadeFour
import blackjack.SpadeJack
import blackjack.SpadeSix
import blackjack.SpadeTen
import blackjack.SpadeThree
import blackjack.SpadeTwo
import blackjack.domain.card.PlayingCards
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StayTest {
    @Test
    fun `Stay(Ace + Two + Three + Four + Five + Six = 21)`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeTwo, SpadeThree, SpadeFour, SpadeFive, SpadeSix)
        val hit = Hit(cards)

        // when
        val actual = hit.stay()

        // then
        assertThat(actual).isInstanceOf(Stay::class.java)
        assertThat(actual.cards.getScore()).isEqualTo(21)
    }

    @Test
    fun `Not Stay(Ten + Jack + Two = 22)`() {
        // / given
        val cards = PlayingCards(SpadeTen, SpadeJack, SpadeTwo)

        // when, then
        Assertions.assertThatThrownBy { Stay(cards) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("스테이가 아닙니다.")
    }

    @Test
    fun `베팅 금액 테스트`() {
        // given
        val given = Stay(PlayingCards(SpadeTen, SpadeJack))

        // when, then
        assertThat(given.earningRate(1000)).isEqualTo(1000.0)
    }
}
