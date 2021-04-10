package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Deck
import blackjack.playingcard.Suit
import blackjack.playingcard.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class PlayerTest {
    @Test
    internal fun `플레이어는 이름을 받아 생성된다`() {
        // given
        val name = Name("Bryce Carlson")

        assertDoesNotThrow { Player(name = name) }
    }

    @Test
    internal fun `플레이어는 생성될 때의 이름을 그대로 가지고 있다`() {
        // given
        val name = Name("Edward O. Thorp")

        // when
        val player = Player(name)

        // then
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    internal fun `플레이어는 생성되면 빈 손패를 가지고 있다`() {
        // when
        val player = Player(Name("Arnold Snyder"))
        val hand: Hand = player.hand

        // then
        assertThat(hand.cards.toList()).isEmpty()
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "DIAMONDS, SEVEN"
    )
    internal fun `플레이어는 덱에서 카드를 뽑아 손패에 추가할 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val deck = Deck(
            listOf(Card.of(suit, symbol))
        )
        val player = Player(Name("Russ Hamilton"))

        // when
        player.drawOneFrom(deck)

        // then
        assertThat(player.hand.cards.toList()).containsExactly(Card.of(suit, symbol))
    }
}
