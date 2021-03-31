package blackjack.model.player

import blackjack.model.trump.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {
    private val deck = MockDeck()

    @ParameterizedTest
    @ValueSource(strings = ["pobi"])
    fun `플레이어는 이름을 가진다`(name: String) {
        val player = Player(deck, name)

        assertThat(player).hasFieldOrPropertyWithValue("name", name)
    }

    @Test
    fun `플레이어는 카드들을 가진다`() {
        val player = Player(deck, "sangw0804")

        assertThat(player).hasFieldOrProperty("cards")
    }

    @Test
    fun `userResponse 가 "y" 이면 카드를 한장 더 뽑는다`() {
        val player = Player(deck, "sangw0804")

        assertThat(player.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT)

        player.keepDrawing("y", deck)

        assertThat(player.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT + 1)
    }
}
