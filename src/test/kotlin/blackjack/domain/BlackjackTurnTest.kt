package blackjack.domain

import blackjack.common.PlayerDecision
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class BlackjackTurnTest {
    @Nested
    inner class `딜러의 카드가` {
        @Test
        fun `16 이하일 경우 17 이상이 될때까지 덱에서 카드를 뽑는다`() {
            val dealer = Dealer(`4 point card`())

            DealerTurn(dealer).play(CardDeck()) {}

            assertThat(dealer.cards.size).isGreaterThanOrEqualTo(4)
        }

        @Test
        fun `17 이상일 경우 카드를 뽑지 않는다`() {
            val dealer = Dealer(`17 point card`())

            DealerTurn(dealer).play(CardDeck()) {}

            assertThat(dealer.cards.size).isEqualTo(2)
        }
    }

    @Nested
    inner class `플레이어의 카드가` {
        @Test
        fun `21 이하이고 hit 를 할 경우 카드를 뽑는다`() {
            val player = Player(`20 point card`())

            PlayerTurn(player).play(CardDeck(), { PlayerDecision.HIT }) {}

            assertThat(player.cards.size).isGreaterThan(2)
        }

        @Test
        fun `21 이하이고 stand 를 할 경우 카드를 뽑지 않는다`() {
            val player = Player(`20 point card`())

            PlayerTurn(player).play(CardDeck(), { PlayerDecision.STAND }) {}

            assertThat(player.cards.size).isEqualTo(2)
        }

        @Test
        fun `블랙잭일 경우 카드를 뽑지 않는다`() {
            val player = Player(`blackjack card`())

            PlayerTurn(player).play(CardDeck(), { PlayerDecision.HIT }) {}

            assertThat(player.cards.size).isEqualTo(2)
        }
    }
}

private fun `4 point card`() = listOf(Card.Two(CardSuit.CLOVER), Card.Two(CardSuit.HEART))
private fun `17 point card`() = listOf(Card.King(CardSuit.CLOVER), Card.Seven(CardSuit.CLOVER))
private fun `20 point card`() = listOf(Card.King(CardSuit.CLOVER), Card.Ten(CardSuit.CLOVER))
private fun `blackjack card`() = listOf(Card.King(CardSuit.CLOVER), Card.Ace(CardSuit.CLOVER))
private fun Player(cards: List<Card>) = Player("vivian", 1000.0, cards)
