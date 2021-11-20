package domain.player

import domain.card.CardGenerator
import domain.card.Denomination
import domain.card.MockedCardGenerator
import domain.card.PlayingCard
import domain.card.PlayingCards
import domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayersTest {
    lateinit var cardGenerator: CardGenerator

    @BeforeEach
    fun setUp() {
        cardGenerator = MockedCardGenerator()
    }

    @DisplayName("이긴 횟수와 수익의 총합을 계산할 수 있어야 한다.")
    @Test
    fun sumOfProfits() {
        val playerList = listOf(
            listOf(
                PlayingCard.of(Denomination.KING, Suit.SPADES),
                PlayingCard.of(Denomination.QUEEN, Suit.HEARTS)
            ),
            listOf(
                PlayingCard.of(Denomination.TEN, Suit.CLUBS),
                PlayingCard.of(Denomination.TEN, Suit.DIAMONDS)
            )
        ).map { Player(info, PlayingCards(it)) }
        val players = Players(playerList)
        players.forEach { it.play(false, cardGenerator) }

        val dealerCards = listOf(
            PlayingCard.of(Denomination.TWO, Suit.SPADES),
            PlayingCard.of(Denomination.THREE, Suit.HEARTS)
        )
        val dealer = Dealer(PlayingCards(dealerCards))
        dealer.draw(cardGenerator)

        val expectedWin = 2
        val expectedSum = 2000.0
        assertAll(
            { assertThat(players.countWins(dealer)).isEqualTo(expectedWin) },
            { assertThat(players.sumOfProfits(dealer)).isEqualTo(expectedSum) }
        )
    }

    companion object {
        private val info = PlayerInfo(PlayerName("플레이어"), BetAmount(1000))
    }
}
