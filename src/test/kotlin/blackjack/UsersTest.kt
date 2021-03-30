package blackjack

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class UsersTest {
    private val player = Player("kasha", 1)
    private val player2 = Player("nextstep", 1)
    private val users = Users(listOf(player, player2))

    @Test
    fun `처음 시작에는 모든 플레이어에게 카드를 2장 부여한다`() {
        users.firstDeal(RandomCardExtractor())

        Assertions.assertThat(player.cardDeck.cards.size).isEqualTo(2)
        Assertions.assertThat(player2.cardDeck.cards.size).isEqualTo(2)
    }
}
