package domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayingCardsTest {
    lateinit var cardGenerator: CardGenerator

    @BeforeEach
    fun setUp() {
        cardGenerator = MockedCardGenerator()
    }

    @DisplayName("맨 처음에는 두 장의 카드를 지급 받아야 한다.")
    @Test
    fun constructor() {
        val expected = 2
        assertThat(PlayingCards(cardGenerator).size)
            .isEqualTo(expected)
    }

    @DisplayName("카드들의 점수의 합을 계산할 수 있다.")
    @Test
    fun score() {
        val expected = 2 + 3 + 4 + 5
        val cardList = listOf(
            PlayingCard.of(Suit.SPADES, Denomination.TWO),
            PlayingCard.of(Suit.CLUBS, Denomination.THREE),
            PlayingCard.of(Suit.HEARTS, Denomination.FOUR),
            PlayingCard.of(Suit.DIAMONDS, Denomination.FIVE)
        )
        assertThat(PlayingCards(cardList).score())
            .isEqualTo(expected)
    }

    @DisplayName("ACE 카드가 4번 나오면 한 개만 점수가 11로 계산되어야 한다.")
    @Test
    fun aceScore() {
        val expected = 1 + 1 + 1 + 11
        val cardList = listOf(
            PlayingCard.of(Suit.SPADES, Denomination.ACE),
            PlayingCard.of(Suit.CLUBS, Denomination.ACE),
            PlayingCard.of(Suit.HEARTS, Denomination.ACE),
            PlayingCard.of(Suit.DIAMONDS, Denomination.ACE)
        )
        assertThat(PlayingCards(cardList).score())
            .isEqualTo(expected)
    }

    @DisplayName("카드들의 점수의 합이 21 이면 FINISHED 상태이다.")
    @Test
    fun finished() {
        val cardList = listOf(
            PlayingCard.of(Suit.SPADES, Denomination.ACE),
            PlayingCard.of(Suit.CLUBS, Denomination.KING)
        )
        assertThat(PlayingCards(cardList).state())
            .isEqualTo(CardState.FINISHED)
    }

    @DisplayName("카드들의 점수의 합이 21 미만이면 RUNNING 상태이다.")
    @Test
    fun running() {
        val cardList = listOf(
            PlayingCard.of(Suit.SPADES, Denomination.ACE),
            PlayingCard.of(Suit.CLUBS, Denomination.ACE)
        )
        assertThat(PlayingCards(cardList).state())
            .isEqualTo(CardState.RUNNING)
    }

    @DisplayName("카드들의 점수의 합이 21 초과이면 BUST 상태이다.")
    @Test
    fun bust() {
        val cardList = listOf(
            PlayingCard.of(Suit.SPADES, Denomination.ACE),
            PlayingCard.of(Suit.CLUBS, Denomination.ACE),
            PlayingCard.of(Suit.HEARTS, Denomination.JACK),
            PlayingCard.of(Suit.DIAMONDS, Denomination.QUEEN)
        )
        assertThat(PlayingCards(cardList).state())
            .isEqualTo(CardState.BUST)
    }
}
