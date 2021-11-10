package blackjack.domain

import blackjack.exception.InvalidPlayerNameException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("n명의 플레이어 테스트")
class PlayersTest {

    @Test
    @DisplayName("n명의 플레이어 생성")
    fun `sut returns correctly`() {
        // Arrange
        val playerNames = mutableListOf("tommy", "pobi", "jason")

        // Act
        val players = Players.from(playerNames)

        // Assert
        playerNames.add("hangyeol")
        assertThat(players.value).hasSize(3)
        assertThat(players.value).contains(
            Player("tommy"),
            Player("pobi"),
            Player("jason"),
        )
    }

    @Test
    @DisplayName("잘못된 이름이 입력될 경우 예외 발생")
    fun `sut throw InvalidPlayerNameException`() {
        // Arrange
        val playerNames = listOf("", "tommy")

        // Act, Assert
        assertThrows<InvalidPlayerNameException> { Players.from(playerNames) }
    }
}
