package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackDealerTest {
    @Test
    fun `딜러는 가진 카드의 합이 17이상이면 카드를 뽑지 않는다`() {
        val blackJackDeck =
            BlackJackDeck(
                listOf(
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.SEVEN),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                ),
            )
        val blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck)
        val blackJackDealer = BlackJackDealer(blackJackPlayerCards = blackJackPlayerCards)

        blackJackDealer.drawCard(blackJackDeck)

        assertThat(blackJackDealer.blackJackPlayerCards.cards.size).isEqualTo(2)
        assertThat(blackJackDealer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard.get(
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
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.TWO),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.SPADE, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.FIVE),
                ),
            )
        val blackJackPlayerCards = BlackJackPlayerCards.byDeck(blackJackDeck)
        val blackJackDealer = BlackJackDealer(blackJackPlayerCards = blackJackPlayerCards)

        blackJackDealer.drawCard(blackJackDeck)

        assertThat(blackJackDealer.blackJackPlayerCards.cards.size).isEqualTo(4)
        assertThat(blackJackDealer.blackJackPlayerCards.cards).containsExactly(
            BlackJackCard.get(
                BlackJackCardShape.HEART,
                BlackJackCardNumber.FIVE,
            ),
            BlackJackCard.get(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.JACK,
            ),
            BlackJackCard.get(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.ACE,
            ),
            BlackJackCard.get(
                BlackJackCardShape.SPADE,
                BlackJackCardNumber.TWO,
            ),
        )
    }
}
