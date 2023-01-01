package domains

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class GameRuleTest {
    @Test
    @DisplayName("입력받은 이름을 기반으로 플레이어를 만든다")
    fun `sut create players by player names`() {
        // Arrange
        val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
        val gameRule = GameRule(deck)
        val drawCount = 1
        val playerNames = listOf("a", "b", "c")

        // Act
        val players = gameRule.createPlayers(firstDrawCount = drawCount, playerNames = playerNames)

        // Assert
        assertThat(players.size).isEqualTo(playerNames.size)
        assertThat(players.map { it.name }).isEqualTo(playerNames)
    }

    @Test
    @DisplayName("player가 가진 카드의 합계를 구한다")
    fun `sut calculate sum of cards`() {
        // Arrange
        val deck = Deck(pokerNumbers = PokerNumber.values().toList(), pokerShapes = PokerShape.values().toList())
        val gameRule = GameRule(deck)
        val player = Player(
            name = "modernflow",
            cards = Cards(
                mutableListOf(
                    Card(PokerNumber.TWO, PokerShape.CLOVER),
                    Card(PokerNumber.TEN, PokerShape.CLOVER)
                )
            )
        )

        // Act
        val summed = gameRule.sumOfCards(player)

        // Assert
        assertThat(summed).isEqualTo(12)
    }
}
