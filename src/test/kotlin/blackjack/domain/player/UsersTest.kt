package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.SortedShuffleStrategy
import blackjack.domain.card.CardDeck
import blackjack.domain.card.RandomShuffleStrategy
import blackjack.domain.createUsers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UsersTest {
    @DisplayName("첫 번째로 카드를 뽑는 경우 2개를 뽑는다")
    @Test
    fun drawAtFirst() {
        val users = createUsers("pobi", "jason")
        val cardDeck = CardDeck(RandomShuffleStrategy())

        users.drawAtFirst(cardDeck)

        assertThat(users.users).allMatch { it.hands.cards.size == 2 }
    }

    @DisplayName("딜러가 16 이하일 경우 계속 카드를 뽑는다")
    @Test
    fun drawDealer() {
        val users = createUsers("pobi", "jason")
        val cardDeck = CardDeck(SortedShuffleStrategy())

        users.draw({ DrawDecider.DRAW }, cardDeck)

        assertThat(users.users[0].hands.cards.size).isEqualTo(2)
    }

    @DisplayName("플레이어가 DRAW를 인자로 받은 경우 1개의 카드를 뽑는다")
    @Test
    fun drawPlayer() {
        val users = createUsers("pobi", "jason")
        val cardDeck = CardDeck(RandomShuffleStrategy())

        users.draw({ DrawDecider.DRAW }, cardDeck)

        assertThat(users.users.drop(1)).allMatch { it.hands.cards.size == 1 }
    }
}
