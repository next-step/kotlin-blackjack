package blackjack

import domain.Player
import domain.card.Denomination
import domain.card.Spade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["peter", "승현"])
    fun `Person 객체에 name 지정 테스트`(name: String) {
        assertThat(Player(name).name).isEqualTo(name)
    }

    @Test
    fun `플레이어에게 카드 나눠주기`() {
        val player = Player("peter")
        val cards = setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.JACK))

        cards.forEach {
            player.dealCard(it)
        }

        assertThat(player.cards()).isEqualTo(cards)
    }
}
