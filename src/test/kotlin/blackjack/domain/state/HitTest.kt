package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class HitTest {

    private val score18Cards = Cards(listOf(Card(Suit.SPADE, Denomination.EIGHT), Card(Suit.SPADE, Denomination.JACK)))

    @Test
    fun `힛 상태는 카드를 더 받을 수 있다`() {
        val hit = Hit(score18Cards)
        Assertions.assertThat(hit.isTakeable()).isTrue()
    }

    @Test
    fun `카드 한장을 더해서 21점인 경우 블랙잭 상태다`() {
        val score3Card = Card(Suit.SPADE, Denomination.THREE)
        val expected = Blackjack(score18Cards.add(score3Card))

        val result = Hit(score18Cards).draw(score3Card)

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `카드를 더해서 21점을 초과하면 버스트 상태다`() {
        val score4Card = Card(Suit.SPADE, Denomination.FOUR)
        val expected = Bust(score18Cards.add(score4Card))

        val result = Hit(score18Cards).draw(score4Card)

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `카드를 더해서 21점 미만이면 힛 상태다`() {
        val score2Card = Card(Suit.SPADE, Denomination.TWO)
        val expected = Hit(score18Cards.add(score2Card))

        val result = Hit(score18Cards).draw(score2Card)

        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `Stay 상태로 변경할 수 있다`() {
        val expected = Stay(score18Cards)

        val result = Hit(score18Cards).stay()

        Assertions.assertThat(result).isEqualTo(expected)
    }
}
