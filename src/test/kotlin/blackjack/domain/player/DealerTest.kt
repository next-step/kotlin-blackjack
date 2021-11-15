package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.Symbol
import blackjack.domain.card.Type
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class DealerTest {

    @Test
    fun `Dealer는 스코어가 16 이하라면 hit할 수 있다`() {
        val underHitHand = Hand(listOf(
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.SIX, Type.CLUB),
        ))
        val dealer = Dealer(hand = underHitHand)

        val result = dealer.canHit()

        assertThat(result).isTrue
    }

    @Test
    fun `Dealer는 스코어가 17 이상이라면 hit할 수 없다`() {
        val overHitHand = Hand(listOf(
            Card(Symbol.TEN, Type.CLUB),
            Card(Symbol.SEVEN, Type.CLUB),
        ))
        val dealer = Dealer(hand = overHitHand)

        val result = dealer.canHit()

        assertThat(result).isFalse
    }
}
