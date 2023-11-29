package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `단건 카드 추가`() {
        // given
        val player = Player("test")
        val card = Card(Suit.SPADE, Rank.ACE)

        // when
        player.addCard(card)

        // then
        assertThat(player.cards).contains(card)
    }

    @Test
    fun `여러 카드 추가`() {
        // given
        val player = Player("test")
        val cards = listOf(
            Card(Suit.SPADE, Rank.ACE),
            Card(Suit.DIAMOND, Rank.TWO),
            Card(Suit.HEART, Rank.THREE),
            Card(Suit.CLUB, Rank.FOUR)
        )

        // when
        player.addCard(cards)

        // then
        assertThat(player.cards).containsAll(cards)
    }
}
