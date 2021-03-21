package blackjack.domain.player

import blackjack.domain.DrawDecider
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

    @DisplayName("DRAW를 인자로 받은 경우 모든 유저가 1개의 카드를 뽑는다")
    @Test
    fun draw() {
        val users = createPlayers("pobi", "jason")
        val cardDeck = CardDeck(RandomShuffleStrategy())

        users.draw({ DrawDecider.DRAW }, cardDeck)

        assertThat(users.users).allMatch { it.hands.cards.size == 1 }
    }

    @DisplayName("STAND를 인자로 받은 경우 모든 유저가 카드를 뽑지 않는다")
    @Test
    fun draw2() {
        val users = createPlayers("pobi", "jason")
        val cardDeck = CardDeck(RandomShuffleStrategy())

        users.draw({ DrawDecider.STAND }, cardDeck)

        assertThat(users.users).allMatch { it.hands.cards.isEmpty() }
    }
}
