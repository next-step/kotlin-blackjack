package blackjack.domain

import blackjack.common.PlayerDecision
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardSuit
import blackjack.domain.card.`blackjack card`
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

private fun `two cards with total 20`(): List<Card> {
    return listOf(Card.King(CardSuit.CLOVER), Card.Ten(CardSuit.CLOVER))
}

class BlackjackTurnTest {
    @Nested
    inner class `딜러의 카드가` {
        @Test
        fun `16 이하일 경우 덱에서 카드를 뽑는다`() {
            val dealer = Dealer(listOf(Card.King(CardSuit.CLOVER), Card.Six(CardSuit.CLOVER)))

            DealerTurn(dealer).play(CardDeck()) {}

            assertThat(dealer.cards.size).isEqualTo(3)
        }

        @Test
        fun `17 이상일 경우 카드를 뽑지 않는다`() {
            val dealer = Dealer(listOf(Card.King(CardSuit.CLOVER), Card.Seven(CardSuit.CLOVER)))

            DealerTurn(dealer).play(CardDeck()) {}

            assertThat(dealer.cards.size).isEqualTo(2)
        }
    }

    @Nested
    inner class `플레이어의 카드가` {
        @Test
        fun `21 이하이고 hit 를 할 경우 카드를 뽑는다`() {
            val player = Player(`two cards with total 20`())

            PlayerTurn(player).play(CardDeck(), { PlayerDecision.HIT }) {}

            assertThat(player.cards.size).isGreaterThan(2)
        }

        @Test
        fun `21 이하이고 stand 를 할 경우 카드를 뽑지 않는다`() {
            val player = Player(`two cards with total 20`())

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
