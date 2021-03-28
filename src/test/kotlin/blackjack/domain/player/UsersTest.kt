package blackjack.domain.player

import blackjack.domain.BettingMoney
import blackjack.domain.SORTED_SHUFFLE
import blackjack.domain.card.CardDeck
import blackjack.domain.createUsers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class UsersTest {
    @DisplayName("첫 번째로 카드를 뽑는 경우 2개를 뽑는다")
    @Test
    fun drawAtFirst() {
        val users = createUsers("pobi", "jason")
        val cardDeck = CardDeck(SORTED_SHUFFLE)

        users.drawAtFirst(cardDeck)

        assertThat(users.users).allMatch { it.hands.cards.size == 2 }
    }

    @DisplayName("게임의 결과 반환")
    @Test
    fun getResult() {
        val users = createUsers("pobi", "jason")

        val result = users.getResult()

        assertThat(result.players.map { it.value }).isEqualTo(listOf(BettingMoney(0), BettingMoney(0)))
    }
}
