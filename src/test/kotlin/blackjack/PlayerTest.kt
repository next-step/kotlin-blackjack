package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드들을 가진다`() {
        val player = Player("laco", Cards.empty())
        assertAll(
            { assertThat(player.name).isEqualTo("laco") },
            { assertThat(player.cards).isEqualTo(Cards.empty()) }
        )
    }

    @Test
    fun `플레이어를 비어있는 이름으로 만들면 RuntimeException 예외가 발생한다`() {
        assertThrows<RuntimeException> { Player("", Cards.empty()) }
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        assertThat(
            Player("laco", Cards.empty())
                .receive(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }
}
