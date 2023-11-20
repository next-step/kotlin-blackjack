package blackjack

import blackjack.business.AlwaysDrawStrategy
import blackjack.business.CardFixture.SPACE_ACE
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_THREE
import blackjack.business.FixSelectionStrategy
import blackjack.business.card.CardDesk
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameManagerTest {

    @Test
    fun start() {
        // given
        val view = TestGameView()
        val gameManager = GameManager(
            view,
            AlwaysDrawStrategy(),
            CardDesk(GameManagerTestCardFactory(), FixSelectionStrategy())
        )

        // when
        gameManager.start()

        // then
        expectedResult(view)
    }

    private fun expectedResult(view: TestGameView) {
        view.playerNames shouldBe listOf("pobi", "jason")
        view.dealer.cards shouldBe listOf(
            SPACE_ACE, SPACE_THREE, SPACE_FIVE
        )
        view.players.forEachPlayer {
            if (it.name == "pobi") {
                it.cards shouldBe listOf(
                    SPACE_NINE, SPACE_THREE, SPACE_NINE
                )
            } else if (it.name == "jason") {
                it.cards shouldBe listOf(
                    SPACE_TEN, SPACE_THREE, SPACE_TEN
                )
            }
        }
    }
}
