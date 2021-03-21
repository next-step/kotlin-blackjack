package blackjack.domain.player

import blackjack.domain.card.CardDeck
import blackjack.domain.card.RandomShuffleStrategy
import blackjack.domain.createPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UsersTest {
    @DisplayName("첫 번째로 카드를 뽑는 경우 2개를 뽑는다")
    @Test
    fun drawAtFirst() {
        val users = createPlayers("pobi", "jason")
        val cardDeck = CardDeck(RandomShuffleStrategy())

        users.drawAtFirst(cardDeck)

        assertThat(users.users).allMatch { it.hands.cards.size == 2 }
    }
}
