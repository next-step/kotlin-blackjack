package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerStateTest {
    @Test
    fun `숫자 합이 21 이하 라면 Playing 을 리턴한다`() {
        assertThat(
            PlayerState.of(Player(`20 point card`()))
        ).isExactlyInstanceOf(PlayerState.Playing::class.java)
    }

    @Test
    fun `숫자 합이 21 이라면 Blackjack 을 리턴한다`() {
        assertThat(
            PlayerState.of(Player(`blackjack card`()))
        ).isEqualTo(PlayerState.Blackjack)
    }

    @Test
    fun `21점 이하가 되는 hit 를 하면 Playing 을 리턴한다`() {
        assertThat(
            Playing(`20 point card`()).hit(Card.Ace(CardSuit.CLOVER))
        ).isExactlyInstanceOf(PlayerState.Playing::class.java)
    }

    @Test
    fun `22점 이상이 되는 hit 를 하면 Busted 을 리턴한다`() {
        assertThat(
            Playing(`20 point card`()).hit(Card.King(CardSuit.CLOVER))
        ).isEqualTo(PlayerState.Busted)
    }

    @Test
    fun `stand 를 할 경우 Stand 을 리턴한다`() {
        assertThat(
            Playing(`20 point card`()).stand()
        ).isEqualTo(PlayerState.Stand)
    }
}

private fun Playing(cards: List<Card>): PlayerState.Playing {
    return PlayerState.Playing(Player(cards))
}

private fun Player(cards: List<Card>) = Player("vivian", 1000.0, cards)
private fun `20 point card`() = listOf(Card.King(CardSuit.CLOVER), Card.Queen(CardSuit.CLOVER))
private fun `blackjack card`() = listOf(Card.King(CardSuit.CLOVER), Card.Ace(CardSuit.CLOVER))
