package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Deal
import blackjack.domain.state.Stand
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
        val sut = Player.of(name, Cards())

        // Assert
        assertThat(sut.name).isEqualTo("tommy")
    }

    @Test
    @DisplayName("올바르지 않은 이름이 입력될 경우 예외 발생")
    fun `sut throw InvalidPlayerNameException when invalid player name`() {
        // Arrange
        val name = ""

        // Act, Assert
        assertThrows<InvalidPlayerNameException> { Player.of(name, Cards()) }
    }

    @Test
    @DisplayName("플레이어가 카드 2장을 뽑고 준비를 완료한다")
    fun `sut returns prepared`() {
        // Arrange
        val player = Player.of("tommy", Cards())
        val deck = Deck.init()

        // Act
        val preparedPlayer = player.prepare(deck)

        // Assert
        assertThat(preparedPlayer.name).isEqualTo("tommy")
        assertThat(preparedPlayer.cards.value).hasSize(2)
    }

    @Test
    @DisplayName("플레이어가 블랙잭을 진행한다")
    fun `sut returns play result`() {
        // Arrange
        val player = Player.of("tommy", Cards())
        val deck = Deck.init()

        val sut = player.prepare(deck)

        // Act
        val playResult = sut.play(deck)

        // Assert
        assertThat(playResult.name).isEqualTo("tommy")
        assertThat(playResult.state).isNotInstanceOf(Deal::class.java)
    }

    @Test
    @DisplayName("플레이어의 진행여부가 true이면 게임을 진행할 수 있다")
    fun `sut returns progressed player`() {
        // Arrange
        val player = Player.of("tommy", Cards())
        val deck = Deck.init()
        val playable = true

        // Act
        val sut = player.prepare(deck)
        val actual = sut.progress(playable, deck)

        // Assert
        assertThat(actual.state).isNotInstanceOf(Deal::class.java)
        assertThat(actual.cards.value.size).isGreaterThanOrEqualTo(2)
    }

    @Test
    @DisplayName("플레이어의 진행여부가 false이면 stand로 게임을 종료한다.")
    fun `sut returns stand player`() {
        // Arrange
        val player = Player.of("tommy", Cards())
        val deck = Deck.init()
        val playable = false

        // Act
        val sut = player.prepare(deck)
        val actual = sut.progress(playable, deck)

        // Assert
        assertThat(actual.state).isInstanceOf(Stand::class.java)
        assertThat(actual.cards.value).hasSize(2)
    }
}
