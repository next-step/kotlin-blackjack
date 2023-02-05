package blackjack.domain.card.state.rule

import blackjack.SpadeAce
import blackjack.SpadeJack
import blackjack.SpadeTen
import blackjack.domain.bet.Money
import blackjack.domain.card.PlayingCards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackTest {
    @Test
    fun `Blackjack(Ace + Jack = Blackjack)`() {
        // given
        val given = Blackjack(PlayingCards(SpadeAce, SpadeJack))

        // when, then
        assertThat(given).isInstanceOf(Blackjack::class.java)
        assertThat(given.cards.isBlackjack()).isTrue
    }

    @Test
    fun `Not Blackjack(ACE + Jack + Ten = 21)`() {
        // given
        val cards = PlayingCards(SpadeAce, SpadeJack, SpadeTen)

        // when
        val exception = assertThrows<IllegalArgumentException> {
            Blackjack(cards)
        }

        // then
        assertThat(exception.message).isEqualTo("블랙잭이 아닙니다.")
    }

    @Test
    fun `베팅 금액 테스트`() {
        // given
        val given = Blackjack(PlayingCards(SpadeAce, SpadeJack))

        // when
        val actual = given.earningRate(Money(1000))

        // then
        assertThat(actual).isEqualTo(1500.0)
    }
}
