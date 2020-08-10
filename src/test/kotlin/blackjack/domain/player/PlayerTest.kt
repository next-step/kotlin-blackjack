package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @DisplayName("플레이어는 카드를 지급받을 수 있다.")
    @Test
    fun giveCardToPlayer() {
        val player = Player(0, "yunji")
        val handStatus = player.giveCard(Card(Pip.TWO, Suit.HEART))
        assertThat(player.getCards()).contains(Card(Pip.TWO, Suit.HEART))
        assertThat(handStatus).isEqualTo(HandStatus.GENERAL)
    }

    @DisplayName("플레이어는 점수가 21 미만일 때만 카드를 받을 수 있다.")
    @Test
    fun hasFreeSpace() {
        val player = Player(0, "yunji").apply {
            giveCard(Card(Pip.TEN, Suit.HEART))
            giveCard(Card(Pip.TEN, Suit.HEART))
            giveCard(Card(Pip.THREE, Suit.HEART))
        }
        assertThat(player.hasFreeSpace()).isFalse()
    }
}
