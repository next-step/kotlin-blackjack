package blackjack.application

import blackjack.domain.deck.Deck
import blackjack.domain.gamer.Player
import blackjack.domain.state.Hit
import blackjack.domain.state.Stand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Blackjack Game 비즈니스 로직 테스트")
class BlackjackGameTest {

    @Test
    @DisplayName("플레이어에게 카드 2장을 나눠준다")
    fun `sut returns complete deal`() {
        // Arrange
        val player = Player("tommy")
        val deck = Deck.init()

        // Act
        val sut = BlackjackGame.create(player)
        val completedDeal = sut.completeDeal(deck)

        // Assert
        assertThat(completedDeal.player.name).isEqualTo("tommy")
        assertThat(completedDeal.player.cards.value).hasSize(2)
        assertThat(completedDeal.state).isInstanceOf(Hit::class.java)
    }

    @Test
    @DisplayName("n 을 입력하면 stand 상태가 된다")
    fun `sut returns stand when play`() {
        // Arrange
        val player = Player("tommy")
        val blackjackGame = BlackjackGame.create(player)
        val deck = Deck.init()
        val sut = blackjackGame.completeDeal(deck)

        // Act
        val resultState = sut.play(deck, "n")

        // Assert
        assertThat(resultState.state).isInstanceOf(Stand::class.java)
    }
}
