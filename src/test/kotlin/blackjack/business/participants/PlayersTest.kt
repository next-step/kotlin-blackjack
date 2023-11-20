package blackjack.business.participants

import blackjack.business.AlwaysDrawStrategy
import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FOUR
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.FixSelectionStrategy
import blackjack.business.card.CardDesk
import blackjack.business.util.Money
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
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
        val target = mutableListOf<GamePlayer>()

        // when
        players.forEachPlayer { player ->
            target.add(player)
        }

        // then
        target shouldBe players.allGamePlayers
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
        players.allGamePlayers.forEach { it.cards.size shouldBe 2 }
    }

    @Test
    fun `각 플레이어에게 순환하면서 특정 조건에 따라 카드를 추가한다`() {
        // given
        val playerNames = listOf("pobi", "jason")
        val players = Players.from(playerNames)
        val cardDesk = CardDesk(cardSelectionStrategy = FixSelectionStrategy())

        // when
        players.executeCardDraws(cardDesk, AlwaysDrawStrategy(), { "y" }) { }

        // then
        // pobi: A, 2, 3, 4, 5, 6
        players.allGamePlayers[0].cards.size shouldBe 6
        // jason: 7,8,9
        players.allGamePlayers[1].cards.size shouldBe 3
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

    @Test
    fun `딜러와 비교하여  플레이별 게임결과를 반환한다`() {
        // given
        val players = Players(
            listOf(
                GamePlayer(
                    "pobi",
                    PlayerCards(SPACE_FOUR, SPACE_EIGHT),
                    Money(10000)
                ),
                GamePlayer(
                    "jason",
                    PlayerCards(SPACE_FOUR, SPACE_NINE),
                    Money(10000)
                ),
                GamePlayer(
                    "honux",
                    PlayerCards(SPACE_FOUR, SPACE_TEN),
                    Money(10000)
                )
            )
        )
        val dealer = Dealer(PlayerCards(SPACE_FOUR, SPACE_NINE))

        // when
        val result = players.getGameResult(dealer)

        // then
        result.playerResults shouldContainExactlyInAnyOrder listOf(
            PlayerResult("pobi", Money(-10000)),
            PlayerResult("jason", Money(0)),
            PlayerResult("honux", Money(20000))
        )
        result.dealerResult shouldBe PlayerResult(Dealer.DEALER_NAME, Money(-10000))
    }
}
