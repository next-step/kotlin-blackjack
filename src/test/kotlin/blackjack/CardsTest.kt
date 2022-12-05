package blackjack

import blackjack.Number.ACE
import blackjack.Number.SIX
import blackjack.Number.TWO
import blackjack.Sharp.CLOVER
import blackjack.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test

internal class CardsTest {

    @Test
    internal fun `카드가 생성된다`() {
        // given
        // when
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))

        // when, then
        assertThat(cards.size).isEqualTo(2)
    }

    @Test
    internal fun `카드가 두장 아래면 예외가 발생한다`() {
        // given
        // when, then
        assertThatIllegalArgumentException().isThrownBy { Cards(mutableSetOf()) }
        assertThatIllegalArgumentException().isThrownBy { Cards(mutableSetOf(Card(SIX, CLOVER))) }
    }

    @Test
    internal fun `카드가 추가된다`() {
        // given
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))
        // when
        cards.add(Card(TWO, HEART))

        // then
        assertThat(cards.size).isEqualTo(3)
    }

    @Test
    internal fun `중복 카드는 추가시 예외가 발생한다`() {
        // given
        val cards = Cards(mutableSetOf(Card(ACE, CLOVER), Card(SIX, CLOVER)))

        // when, then
        assertThatIllegalArgumentException().isThrownBy { cards.add(Card(SIX, CLOVER)) }
    }
}
