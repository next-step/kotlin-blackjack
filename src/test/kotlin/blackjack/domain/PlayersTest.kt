package blackjack.domain

import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players
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
        val tommy = Player("tommy")
        val pobi = Player("pobi")
        val jason = Player("jason")

        // Act
        val participants = mutableListOf(tommy, pobi, jason)
        val players = Players.from(participants)

        // Assert
        participants.add(Player("hangyeol"))
        assertThat(players.value).hasSize(3)
        assertThat(players.value).contains(tommy, pobi, jason)
    }

    @Test
    @DisplayName("잘못된 이름이 입력될 경우 예외 발생")
    fun `sut throw InvalidPlayerNameException`() {
        // Act, Assert
        assertThrows<InvalidPlayerNameException> {
            Players.from(
                listOf(Player(""), Player("tommy"))
            )
        }
    }
}
