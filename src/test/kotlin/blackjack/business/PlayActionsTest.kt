package blackjack.business

import blackjack.business.PlayActions.dealInitialCards
import blackjack.business.PlayActions.processAdditionalCards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayActionsTest {

    @Test
    fun `모든 플래이어 카드 2개를 추가한다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val cardDesk = CardDesk()
        val players = Players.from(playerNames)

        // when
        with(players) {
            dealInitialCards(cardDesk)
        }

        // then
        players.players.forEach { player ->
            player.cards.size shouldBe 2
        }
    }

    @Test
    fun `플레이어가 카드를 추가할지 물어본다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val cardDesk = CardDesk(cardSelectionStrategy = FixSelectionStrategy())
        val players = Players.from(playerNames)

        // when
        with(players) {
            dealInitialCards(cardDesk)
            processAdditionalCards(cardDesk, FixDrawConditionStrategy())
        }

        // then
        players.players[0].cards.size shouldBe 5
        players.players[1].cards.size shouldBe 4
    }

    class FixSelectionStrategy : CardSelectionStrategy {
        override fun selectCard(cards: List<Card>): Card {
            return cards.first()
        }
    }

    class FixDrawConditionStrategy : DrawConditionStrategy {
        override fun shouldDraw(playerName: String): Boolean {
            return true
        }
    }
}
