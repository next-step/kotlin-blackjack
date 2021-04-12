package blackjack.model.gamer

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
        val gamer = Player(deck, name)

        assertThat(gamer).hasFieldOrPropertyWithValue("name", name)
    }

    @Test
    fun `플레이어는 카드들을 가진다`() {
        val gamer = Player(deck, "sangw0804")

        assertThat(gamer).hasFieldOrProperty("cards")
    }

    @Test
    fun `userResponse 가 "y" 이면 카드를 한장 더 뽑는다`() {
        val gamer = Player(deck, "sangw0804")

        assertThat(gamer.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT)

        gamer.keepDrawing("y", deck)

        assertThat(gamer.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT + 1)
    }
}
