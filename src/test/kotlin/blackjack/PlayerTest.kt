package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드들을 가진다`() {
        val player = Player(Name.valueOf("laco"), Cards.empty())
        assertAll(
            { assertThat(player.name).isEqualTo(Name.valueOf("laco")) },
            { assertThat(player.cards).isEqualTo(Cards.empty()) }
        )
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        assertThat(
            Player(Name.valueOf("laco"), Cards.empty())
                .receive(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }
}
