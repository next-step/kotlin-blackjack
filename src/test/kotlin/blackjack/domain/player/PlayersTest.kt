package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.createPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayersTest {
    @DisplayName("첫 번째로 카드를 뽑는 경우 2개를 뽑는다")
    @Test
    fun drawAtFirst() {
        val players = createPlayers("pobi", "jason")
        val cardDeck = CardDeck()

        players.drawAtFirst(cardDeck)

        assertThat(players.players).allMatch { it.hands.cards.size == 2 }
    }
}
