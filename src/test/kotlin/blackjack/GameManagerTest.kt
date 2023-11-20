package blackjack

import blackjack.business.AlwaysDrawStrategy
import blackjack.business.FixSelectionStrategy
import blackjack.business.card.CardDesk
import blackjack.business.participants.PlayerResult
import blackjack.business.util.Money
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameManagerTest {

    /**
     *  dealer: A, 3, 5
     *  pobi: 9, 3, 9
     *  jason: 10, 3, 10
     */
    @Test
    fun start() {
        // given
        val view = GameManagerTestGameView()
        val gameManager = GameManager(
            view,
            AlwaysDrawStrategy(),
            CardDesk(GameManagerTestCardFactory(), FixSelectionStrategy())
        )

        // when
        gameManager.start()

        // then
        view.playerNames shouldBe listOf("pobi", "jason")
        view.gameResult.dealerResult shouldBe PlayerResult("딜러", Money(-10000))
        view.gameResult.playerResults shouldContainExactlyInAnyOrder listOf(
            PlayerResult("pobi", Money(20000)),
            PlayerResult("jason", Money(-10000))
        )
    }
}
