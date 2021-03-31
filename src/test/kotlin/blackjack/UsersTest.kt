package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class UsersTest {
    private val player = Player("kasha", 1)
    private val player2 = Player("nextstep", 1)
    private val dealer = Dealer()
    private val users = Users(listOf(player, player2, dealer))

    @Test
    fun `처음 시작에는 모든 플레이어에게 카드를 2장 부여한다`() {
        users.firstDeal(RandomCardExtractor())

        assertThat(player.cardDeck.cards.size).isEqualTo(2)
        assertThat(player2.cardDeck.cards.size).isEqualTo(2)
    }

    @Test
    fun `딜러를 반환한다`() {
        assertThat(users.getDealer()).isEqualTo(dealer)
    }

    @Test
    fun `플레이어들을 반환한다`() {
        assertThat(users.getPlayers().players).containsAll(listOf(player, player2))
    }
}
