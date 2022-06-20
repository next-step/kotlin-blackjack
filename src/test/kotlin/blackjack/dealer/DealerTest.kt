package blackjack.dealer

import blackjack.card.Deck
import blackjack.factory.SimpleCardCreator
import blackjack.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DealerTest {

    @Test
    fun `딜러는 덱에서 카드를 뽑아 플레이어에게 줄 수 있다`() {
        val deck = Deck(SimpleCardCreator.startCard())
        val dealer = Dealer(deck)
        val player = Player("pang")

        assertThat(player.myCards()).isEmpty()
        dealer.giveCard(player)

        assertThat(player.myCards().size).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1, 2, 3, 4, 5])
    fun `요청한 카드 갯수만큼 카드를 뽑아 줄 수 있다`(value: Int) {
        val deck = Deck(SimpleCardCreator.startCard())
        val dealer = Dealer(deck)
        val player = Player("pang")

        dealer.giveCards(player, value)

        assertThat(player.myCards().size).isEqualTo(value)
    }
}
