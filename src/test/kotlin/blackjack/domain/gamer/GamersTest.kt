package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.exception.NotExistDealerException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@DisplayName("n명의 게이머 테스트")
class GamersTest {

    @Test
    @DisplayName("n명의 게이머는 딜러와 플레이어로 구성된다")
    fun `sut returns gamers`() {
        // Arrange
        val playerNames = listOf("tommy", "pobi", "jason")

        // Act
        val sut = Gamers.init(playerNames)

        // Assert
        assertThat(sut.value).hasSize(4)
        assertThat(sut.value.first()).isInstanceOf(Dealer::class.java)
        assertThat(sut.value[1].name).isEqualTo("tommy")
        assertThat(sut.value.last().name).isEqualTo("jason")
    }

    @Test
    @DisplayName("첫 번째 게이머가 딜러가 아닌 경우 예외 발생")
    fun `sut throw NotExistDealerException when first gamer is not dealer`() {
        // Arrange
        val gamers = listOf(Player.of("tommy", Cards()))

        // Act, Assert
        assertThrows<NotExistDealerException> { Gamers.from(gamers) }
    }

    @Test
    @DisplayName("각 게이머들은 2장의 카드를 지급받는다")
    fun `sut returns prepared gamers`() {
        // Arrange
        val playerNames = listOf("tommy", "pobi", "jason")
        val sut = Gamers.init(playerNames)

        assertThat(sut.getDealer().cards.value).hasSize(0)
        assertThat(sut.getPlayers().first().cards.value).hasSize(0)
        assertThat(sut.getPlayers().last().cards.value).hasSize(0)

        // Act
        val preparedGamers = sut.prepare(Deck.init())

        // Assert
        assertThat(preparedGamers.getDealer().cards.value).hasSize(2)
        assertThat(preparedGamers.getPlayers().first().cards.value).hasSize(2)
        assertThat(preparedGamers.getPlayers().last().cards.value).hasSize(2)
    }
}
