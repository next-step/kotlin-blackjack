package blackjack.domains

import blackjack.GameRule
import blackjack.domains.deck.*
import blackjack.domains.participants.Player
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
        val players = gameRule.initUsers(playerNames = playerNames)

        // Assert
        assertThat(players.getDealer()).isNotNull
        assertThat(players.getPlayers().size).isEqualTo(playerNames.size)
        assertThat(players.getPlayers().map { it.name }).isEqualTo(playerNames)
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
