package blackjack

import blackjack.domains.deck.Deck
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameRuleTest {
    @Test
    @DisplayName("입력받은 이름을 기반으로 플레이어들을 만들고 딜러를 한명 만든다")
    fun `sut should create players with a dealer when player names are given`() {
        // Arrange
        val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
        val gameRule = GameRule(deck)
        val playerNames = listOf("a", "b", "c")

        // Act
        val players = gameRule.initGame(playerNames = playerNames)

        // Assert
        assertThat(players.getDealer()).isNotNull
        assertThat(players.getPlayers().size).isEqualTo(playerNames.size)
        assertThat(players.getPlayers().map { it.name }).isEqualTo(playerNames)
    }
}
