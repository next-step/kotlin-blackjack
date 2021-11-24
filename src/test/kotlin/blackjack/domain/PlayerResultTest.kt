package blackjack.domain

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.Player
import blackjack.domain.result.PlayerResult
import blackjack.domain.state.Bust
import blackjack.domain.state.Deal
import blackjack.domain.state.result.Lose
import blackjack.domain.state.result.Push
import blackjack.domain.state.result.Win
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("블랙잭 게임 플레이어 결과 테스트")
class PlayerResultTest {

    @Test
    @DisplayName("블랙잭 종료 시 플레이어와 딜러의 결과가 같으면 무승부")
    fun `sut returns push`() {
        // Arrange
        val dealer = Dealer.from(createCardsFixture(Denomination.ACE, Denomination.QUEEN))
        val playerCards = createCardsFixture(Denomination.JACK, Denomination.ACE)

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Push::class.java)
    }

    @Test
    @DisplayName("블랙잭 종료 시 플레이어가 블랙잭이고, 딜러의 합이 더 작으면 승리")
    fun `sut returns player blackjack`() {
        // Arrange
        val dealer = Dealer.from(createCardsFixture(Denomination.JACK, Denomination.QUEEN))
        val playerCards = createCardsFixture(Denomination.JACK, Denomination.ACE)

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Win::class.java)
    }

    @Test
    @DisplayName("블랙잭 종료 시 플레이어가 21이고, 딜러의 합이 더 작으면 승리")
    fun `sut returns player twenty one`() {
        // Arrange
        val dealer = Dealer.from(createCardsFixture(Denomination.JACK, Denomination.QUEEN))
        val playerCards = Cards(
            listOf(
                Card(Denomination.FIVE, Suit.DIAMOND),
                Card(Denomination.ACE, Suit.HEART),
                Card(Denomination.FIVE, Suit.SPADE)
            )
        )

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Win::class.java)
    }

    @Test
    @DisplayName("블랙잭 종료 시 딜러가 Bust이고, 플레이어의 점수가 21 이하이면 승리")
    fun `sut return player win when dealer bust`() {
        // Arrange
        val dealer = Dealer.init(
            "딜러",
            Bust(
                Cards(
                    listOf(
                        Card(Denomination.TEN, Suit.DIAMOND),
                        Card(Denomination.SEVEN, Suit.HEART),
                        Card(Denomination.SIX, Suit.SPADE)
                    )
                )
            )
        )
        val playerCards = createCardsFixture(Denomination.FIVE, Denomination.SIX)

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Win::class.java)
    }

    @Test
    @DisplayName("블랙잭 종료 시 플레이어의 합이 딜러보다 클 경우 승리")
    fun `sut return player win`() {
        // Arrange
        val dealer = Dealer.from(createCardsFixture(Denomination.FIVE, Denomination.FIVE))
        val playerCards = createCardsFixture(Denomination.FIVE, Denomination.SIX)

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Win::class.java)
    }

    @Test
    @DisplayName("블랙잭 종료 시 플레이어의 합이 딜러보다 작을 경우 패배")
    fun `sut return player lose`() {
        // Arrange
        val dealer = Dealer.from(createCardsFixture(Denomination.FIVE, Denomination.SIX))
        val playerCards = createCardsFixture(Denomination.FIVE, Denomination.FIVE)

        val player = Player.init("tommy", Deal(playerCards))
        val gamers = Gamers.from(listOf(dealer, player))

        // Act
        val sut = PlayerResult(gamers)
        val playersResult = sut.judgePlayerResult()

        // Assert
        assertThat(playersResult.first().state).isInstanceOf(Lose::class.java)
    }

    private fun createCardsFixture(firstDenomination: Denomination, secondDenomination: Denomination): Cards {
        return Cards(
            listOf(
                Card(firstDenomination, Suit.DIAMOND),
                Card(secondDenomination, Suit.HEART),
            )
        )
    }
}
