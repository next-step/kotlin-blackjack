package blackjack.business

import blackjack.view.OutputHandler.printPlayer
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어들")
class PlayersTest {

    @Test
    fun `플레이어가 2명이하이면 예외를 던진다`() {
        // given
        val playerNames = listOf("pobi")

        // when,then
        shouldThrow<IllegalArgumentException> { Players.from(playerNames) }.message shouldBe "플레이어는 2명 이상이여야 가능합니다."
    }

    @Test
    fun `플레이어가 2명 이상이여야 가능합니다`() {
        // given
        val playerNames = listOf("pobi", "wonyong")

        // when,then
        Players.from(playerNames)
    }

    @Test
    fun `모든 플레이어를 2장 씩 카드를 나눠준다`() {
        // given
        val playerNames = listOf("pobi", "wonyong")
        val players = Players.from(playerNames)
        val cardDesk = CardDesk()

        // when
        players.dealInitialCards(cardDesk, ::printPlayer)

        // then
        players.forEachPlayer { player ->
            player.cards.size shouldBe 2
        }
    }

    /**
     * 모든 플레이어에게 카드 순서대로 한장씩 추가로 준다.
     * 초기 카드 할당:
     * - pobi -> A스페이드, 2스페이드
     * - jason -> 3스페이드, 4스페이드
     * 카드를 최대한 받는다
     * 추가 카드 할당 후 예상 결과:
     * - pobi -> 총 5장의 카드 (A스페이드, 2스페이드, 5스페이드, 6스페이드, 7스페이드)
     * - jason -> 총 4장의 카드 (3스페이드, 4스페이드, 8스페이드, 9스페이드)
     */
    @Test
    fun `모든 플레이어에게 추가 카드를 준다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val cardDesk = CardDesk(FixSelectionStrategy())
        players.dealInitialCards(cardDesk, ::printPlayer)

        // when
        players.processAdditionalCards(cardDesk, FixDrawConditionStrategy(), ::printPlayer)

        // then
        players.forEachPlayer { player ->
            when (player.name) {
                "pobi" -> player.cards.size shouldBe 5
                "jason" -> player.cards.size shouldBe 4
            }
        }
    }
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
