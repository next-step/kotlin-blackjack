package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `PreparedPlayer는 카드 두 장을 받은 OngoingPlayer가 된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)

        val preparedPlayers = listOf(PreparedPlayer("a"))
        val onGoingPlayers = blackJack.play(preparedPlayers)

        val actualCardsCount = onGoingPlayers.first().cards.value.size
        val expectedCardsCount = 2

        assertThat(actualCardsCount).isEqualTo(expectedCardsCount)
    }

    @Test
    fun `PreparedPlayer는 OngoingPlayer가 되더라도 이름이 유지된다`() {
        val deck = Deck()
        val blackJack = BlackJack(deck)

        val preparedPlayers = listOf(PreparedPlayer("a"))
        val onGoingPlayers = blackJack.play(preparedPlayers)

        val actualPlayerName = onGoingPlayers.first().name
        val expectedPlayerName = "a"

        assertThat(actualPlayerName).isEqualTo(expectedPlayerName)
    }
}
