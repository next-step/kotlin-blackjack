package blackjack.domain

import blackjack.domain.gamer.Player
import blackjack.exception.InvalidPlayerNameException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("플레이어 테스트")
class PlayerTest {

    @Test
    @DisplayName("플레이어를 생성한다")
    fun `sut returns player`() {
        // Arrange
        val name = "tommy"

        // Act
        val sut = Player(name)

        // Assert
        assertThat(sut.name).isEqualTo("tommy")
    }

    @Test
    @DisplayName("올바르지 않은 이름이 입력될 경우 예외 발생")
    fun `sut throw InvalidPlayerNameException when invalid player name`() {
        // Arrange
        val name = ""

        // Act, Assert
        assertThrows<InvalidPlayerNameException> { Player(name) }
    }
}
