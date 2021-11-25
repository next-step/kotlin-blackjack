package domain.player

import domain.card.CardGenerator
import domain.card.Denomination
import domain.card.MockedCardGenerator
import domain.card.PlayingCard
import domain.card.Suit
import exception.IllegalDrawException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class DealerTest {
    private lateinit var cardGenerator: CardGenerator
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        cardGenerator = MockedCardGenerator()
        dealer = Dealer(cardGenerator)
    }

    @DisplayName("Dealer 의 이름은 딜러이다.")
    @Test
    fun name() {
        val expectedName = "딜러"
        assertThat(dealer.name()).isEqualTo(expectedName)
    }

    @DisplayName("Dealer 의 점수가 16점 이하이면, 딜러는 카드를 뽑을 수 있다.")
    @Test
    fun drawable() {
        val expectedScore = 16
        val dealerCards = mutableListOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS),
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.TWO, Suit.CLUBS)
        )
        val cardGenerator = object : CardGenerator {
            override fun getCard(): PlayingCard = dealerCards.removeFirst()
        }
        val dealer = Dealer(cardGenerator)
        while (dealerCards.isNotEmpty()) {
            dealer.play(true, cardGenerator)
        }
        assertAll(
            { assertThat(dealer.isDrawable()).isTrue },
            { assertThat(dealer.score()).isEqualTo(expectedScore) }
        )
    }

    @DisplayName("Dealer 의 점수가 16점 초과이면, 딜러는 카드를 뽑을 수 없다.")
    @Test
    fun notDrawable() {
        val expectedScore = 18
        val dealerCards = mutableListOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS),
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.TWO, Suit.CLUBS),
            PlayingCard.of(Denomination.TWO, Suit.DIAMONDS)
        )
        val cardGenerator = object : CardGenerator {
            override fun getCard(): PlayingCard = dealerCards.removeFirst()
        }
        val dealer = Dealer(cardGenerator)
        while (dealerCards.isNotEmpty()) {
            dealer.play(true, cardGenerator)
        }
        assertAll(
            { assertThat(dealer.isDrawable()).isFalse },
            { assertThat(dealer.score()).isEqualTo(expectedScore) },
            {
                assertThatExceptionOfType(IllegalDrawException::class.java)
                    .isThrownBy { dealer.draw(cardGenerator) }
            }
        )
    }
}
