package blackjack.domain

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
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
        val tommy = Player.of("tommy", Cards())
        val pobi = Player.of("pobi", Cards())
        val jason = Player.of("jason", Cards())

        // Act
        val participants = mutableListOf(tommy, pobi, jason)
        val players = Players.from(participants)

        // Assert
        participants.add(Player.of("hangyeol", Cards()))
        assertThat(players.value).hasSize(3)
        assertThat(players.value).contains(tommy, pobi, jason)
    }

    @Test
    @DisplayName("잘못된 이름이 입력될 경우 예외 발생")
    fun `sut throw InvalidPlayerNameException`() {
        // Act, Assert
        assertThrows<InvalidPlayerNameException> {
            Players.from(
                listOf(Player.of("", Cards()), Player.of("tommy", Cards()))
            )
        }
    }

    @Test
    @DisplayName("n명의 플레이어에게 카드 2장을 주고 블랙잭 준비를 완료한다")
    fun `sut returns prepared players`() {
        // Arrange
        val tommy = Player.of("tommy", Cards())
        val pobi = Player.of("pobi", Cards())
        val jason = Player.of("jason", Cards())
        val players = listOf(tommy, pobi, jason)

        val deck = Deck.init()

        // Act
        val sut = Players.from(players)
        val preparedPlayers = sut.prepare(deck)

        // Assert
        assertThat(preparedPlayers.value).hasSize(3)
        assertThat(preparedPlayers.value.first().cards.value).hasSize(2)
        assertThat(preparedPlayers.value.last().cards.value).hasSize(2)
    }
}
