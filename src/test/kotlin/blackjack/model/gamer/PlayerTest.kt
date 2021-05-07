package blackjack.model.gamer

import blackjack.model.BetMoney
import blackjack.model.trump.Cards
import blackjack.model.trump.TrumpDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["pobi"])
    fun `플레이어는 이름을 가진다`(name: String) {
        val gamer = Player(name, BetMoney(1000))

        assertThat(gamer).hasFieldOrPropertyWithValue("name", name)
        assertThat(gamer).hasFieldOrProperty("cards")
        assertThat(gamer).hasFieldOrPropertyWithValue("betMoney", BetMoney(1000))
    }

    @Test
    fun `userResponse 가 "y" 이면 카드를 한장 더 뽑는다`() {
        val gamer = Player("sangw0804", BetMoney(1000))
        repeat(Cards.INITIAL_DRAW_COUNT) {
            gamer.draw(TrumpDeck())
        }

        assertThat(gamer.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT)

        gamer.keepDrawing(true, TrumpDeck())

        assertThat(gamer.cards.size).isEqualTo(Cards.INITIAL_DRAW_COUNT + 1)
    }
}
