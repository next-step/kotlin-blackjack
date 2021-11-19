package domain.player

import domain.card.CardGenerator
import domain.card.Denomination
import domain.card.MockedCardGenerator
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import exception.IllegalPlayException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class PlayerTest {
    lateinit var cardGenerator: CardGenerator
    lateinit var player: Player

    @BeforeEach
    fun setUp() {
        cardGenerator = MockedCardGenerator()
        player = Player(info, cardGenerator)
    }

    @DisplayName("초기에는 카드를 2장을 갖고, finished 상태가 아니어야야한다.")
    @Test
    fun name() {
        val expectedCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS),
        )
        assertAll(
            { assertThat(player.cards().toList()).isEqualTo(expectedCards) },
            { assertThat(player.isFinished()).isFalse() },
            { assertThat(player.name()).isEqualTo(name) },
            { assertThat(player.score()).isEqualTo(12) }
        )
    }

    @DisplayName("draw 를 하면 카드가 추가 되어야한다.")
    @Test
    fun draw() {
        val expectedCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS)
        )
        player.play(true, cardGenerator)
        assertAll(
            { assertThat(player.cards().toList()).isEqualTo(expectedCards) },
            { assertThat(player.isFinished()).isFalse },
            { assertThat(player.name()).isEqualTo(name) },
            { assertThat(player.score()).isEqualTo(13) }
        )
    }

    @DisplayName("stay 를 하면 finished 되어야 한다.")
    @Test
    fun stay() {
        val expectedCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS)
        )
        player.play(false, cardGenerator)
        assertAll(
            { assertThat(player.cards().toList()).isEqualTo(expectedCards) },
            { assertThat(player.isFinished()).isTrue },
            { assertThat(player.name()).isEqualTo(name) },
            { assertThat(player.score()).isEqualTo(12) }
        )
    }

    @DisplayName("이미 finished 되었다면, 더 이상 play 할 수 없다.")
    @Test
    fun illegalPlay() {
        player.play(false, cardGenerator)
        assertAll(
            { assertThat(player.isFinished()).isTrue },
            {
                assertThatExceptionOfType(IllegalPlayException::class.java)
                    .isThrownBy { player.play(true, cardGenerator) }
            },
            {
                assertThatExceptionOfType(IllegalPlayException::class.java)
                    .isThrownBy { player.play(false, cardGenerator) }
            },
            { assertThat(player.score()).isEqualTo(12) }
        )
    }

    @DisplayName("stay 를 하지 않아도, score 가 21 점이 되면 finished 되어야 한다.")
    @Test
    fun finished() {
        val expectedCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.DIAMONDS),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS),
            PlayingCard.of(Denomination.ACE, Suit.SPADES),
            PlayingCard.of(Denomination.TWO, Suit.CLUBS),
            PlayingCard.of(Denomination.TWO, Suit.DIAMONDS),
            PlayingCard.of(Denomination.TWO, Suit.HEARTS),
            PlayingCard.of(Denomination.TWO, Suit.SPADES),
            PlayingCard.of(Denomination.THREE, Suit.CLUBS),
            PlayingCard.of(Denomination.THREE, Suit.DIAMONDS),
            PlayingCard.of(Denomination.THREE, Suit.HEARTS)
        )
        val repeatTime = 4 + 4 + 3 - 2
        repeat(repeatTime) { player.play(true, cardGenerator) }
        val cards = player.cards()
        assertAll(
            { assertThat(player.isFinished()).isTrue },
            { assertThat(cards.score()).isEqualTo(21) },
            { assertThat(cards.toList()).isEqualTo(expectedCards) },
            {
                assertThatExceptionOfType(IllegalPlayException::class.java)
                    .isThrownBy { player.play(true, cardGenerator) }
            },
            {
                assertThatExceptionOfType(IllegalPlayException::class.java)
                    .isThrownBy { player.play(false, cardGenerator) }
            },
            { assertThat(player.score()).isEqualTo(21) }
        )
    }

    @DisplayName("딜러보다 Score 가 높으면 이긴다.")
    @Test
    fun win() {
        val dealerCards = listOf(
            PlayingCard.of(Denomination.TWO, Suit.CLUBS),
            PlayingCard.of(Denomination.THREE, Suit.HEARTS)
        )
        val dealer = Dealer(PlayingCards(dealerCards))
        assertThat(player.win(dealer)).isTrue
    }

    @DisplayName("딜러보다 Score 가 낮으면 진다.")
    @Test
    fun lose() {
        val dealerCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.KING, Suit.HEARTS)
        )
        val dealer = Dealer(PlayingCards(dealerCards))
        assertThat(player.win(dealer)).isFalse
    }

    @DisplayName("딜러와 Score 가 같으면 진다.")
    @Test
    fun sameScore() {
        val dealerCards = listOf(
            PlayingCard.of(Denomination.ACE, Suit.CLUBS),
            PlayingCard.of(Denomination.ACE, Suit.HEARTS)
        )
        val dealer = Dealer(PlayingCards(dealerCards))
        assertThat(player.win(dealer)).isFalse
    }

    companion object {
        private const val name = "고정완"
        private const val money = 1000
        private val info = PlayerInfo(PlayerName(name), BetAmount(money))
    }
}
