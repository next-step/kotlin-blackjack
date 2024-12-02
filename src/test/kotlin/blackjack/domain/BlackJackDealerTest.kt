package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackDealerTest {
    @Test
    fun `딜러는 가진 카드의 합이 17이상이면 카드를 뽑지 않는다`() {
        val blackJackDeck =
            BlackJackDeck(
                listOf(
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.SEVEN),
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                ),
            )
        val blackJackPlayerCards = BlackJackPlayerCards(mutableListOf(blackJackDeck.draw(), blackJackDeck.draw()))
        val blackJackDealer = BlackJackDealer("dealer", blackJackPlayerCards)

        if (blackJackDealer.isDrawPossible()) {
            blackJackDealer.drawCard(blackJackDeck.draw())
        }

        assertThat(blackJackDealer.blackJackPlayerCards.cards.size).isEqualTo(2)
        assertThat(blackJackDealer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.JACK,
            ),
        )
    }

    @Test
    fun `딜러는 가진 카드의 합이 16이하면 카드를 뽑는다`() {
        val blackJackDeck =
            BlackJackDeck(
                listOf(
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.SEVEN),
                    BlackJackCard(BlackJackCardShape.SPADE, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.TWO),
                ),
            )
        val blackJackPlayerCards = BlackJackPlayerCards(mutableListOf(blackJackDeck.draw(), blackJackDeck.draw()))
        val blackJackDealer = BlackJackDealer("dealer", blackJackPlayerCards)

        if (blackJackDealer.isDrawPossible()) {
            blackJackDealer.drawCard(blackJackDeck.draw())
        }

        assertThat(blackJackDealer.blackJackPlayerCards.cards.size).isEqualTo(3)
        assertThat(blackJackDealer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.TWO,
            ),
            BlackJackCard(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.JACK,
            ),
            BlackJackCard(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.SEVEN,
            ),
        )
    }
}
