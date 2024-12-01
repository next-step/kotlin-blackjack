package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackPlayerCardsTest {
    @Test
    fun addcardTest() {
        val blackJackPlayerCards =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )
        blackJackPlayerCards.addCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
        assertThat(blackJackPlayerCards.cards.size).isEqualTo(3)
        assertThat(blackJackPlayerCards.cards[2]).isEqualTo(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
    }

    @Test
    fun `카드 추가 불가 케이스 테스트`() {
        val blackJackPlayerCards =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.TWO),
                ),
            )
        assertThatThrownBy { blackJackPlayerCards.addCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("21을 넘으면 카드를 못뽑아요")
    }

    @Test
    fun bestSumTest() {
        val blackJackPlayerCards1 =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )

        val blackJackPlayerCards2 =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.TWO),
                ),
            )

        assertThat(blackJackPlayerCards1.getCardsBestSum()).isEqualTo(21)
        assertThat(blackJackPlayerCards2.getCardsBestSum()).isEqualTo(13)
    }
}
