package blackjack

import blackjack.model.CardDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackApplicationTest {

    @Test
    internal fun `플레이어 이름은 쉼표로 구분한다`() {
        // given
        val input = "pobi,jason"
        val cardDeck = CardDeck.defaultDeck()

        // when
        val players = BlackjackApplication().initPlayers(input, cardDeck)

        // then
        assertThat(players.map { it.name }).containsExactly("pobi", "jason")
    }
}
