package blackjack.business.participants

import blackjack.business.FixDrawConditionStrategy
import blackjack.business.FixSelectionStrategy
import blackjack.business.card.CardDesk
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
    fun `각 플레이어를 순환하면서 action을 실행한다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val target = mutableListOf<Player>()

        // when
        players.forEachPlayer { player ->
            target.add(player)
        }

        // then
        target shouldBe players.allPlayers
    }

    @Test
    fun `각 플레이어에게 카드를 2장씩 나눠준다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val cardDesk = CardDesk()

        // when
        players.dealInitialCards(cardDesk) { }

        // then
        players.allPlayers.forEach { it.cards.size shouldBe 2 }
    }

    @Test
    fun `각 플레이어에게 순환하면서 특정 조건에 따라 카드를 추가한다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val cardDesk = CardDesk(cardSelectionStrategy = FixSelectionStrategy())

        // when
        players.executeCardDraws(cardDesk, FixDrawConditionStrategy(), { "y" }) { }

        // then
        // pobi: A, 2, 3, 4, 5, 6
        players.allPlayers[0].cards.size shouldBe 6
        // jason: 7,8,9
        players.allPlayers[1].cards.size shouldBe 3
    }

    @Test
    fun `각 플레이어의 네임을 가져온다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)

        // when
        val names = players.getNames()

        // then
        names shouldBe playerNames
    }
}
