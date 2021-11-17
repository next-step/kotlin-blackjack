package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    fun setup() {
        player = Player(Name.valueOf("laco"), Cards.empty())
    }

    @Test
    fun `플레이어는 이름과 카드들을 가진다`() {
        assertAll(
            { assertThat(player.name).isEqualTo(Name.valueOf("laco")) },
            { assertThat(player.cards).isEqualTo(Cards.empty()) }
        )
    }

    @Test
    fun `플레이어는 카드를 받을 수 있다`() {
        assertThat(
            player
                .receive(Card(Denomination.ACE, Suit.HEART))
                .cards
        ).isEqualTo(Cards(listOf(Card(Denomination.ACE, Suit.HEART))))
    }

    @Test
    fun `플레이어는 조건에따라 카드를 계속해서 받을 수 있다`() {
        val cards = listOf(
            Card(Denomination.ACE, Suit.HEART),
            Card(Denomination.ACE, Suit.DIAMOND),
        )
        var count = 0
        assertThat(
            player
                .receiveWhile(10) { cards.getOrNull(count++) }
                .cards
        ).isEqualTo(Cards(cards))
    }
}
