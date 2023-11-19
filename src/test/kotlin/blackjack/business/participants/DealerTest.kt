package blackjack.business.participants

import blackjack.business.CardFixture.SPACE_EIGHT
import blackjack.business.CardFixture.SPACE_FIVE
import blackjack.business.CardFixture.SPACE_FOUR
import blackjack.business.CardFixture.SPACE_NINE
import blackjack.business.CardFixture.SPACE_TEN
import blackjack.business.CardFixture.SPACE_THREE
import blackjack.business.card.Card
import blackjack.business.card.CardDesk
import blackjack.business.util.GameResult
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class DealerTest {
    @Test
    fun `딜러의 카드를 한장씩 추가 한다`() {
        // given
        val dealer = Dealer()
        val cardDesk = CardDesk()
        val card = cardDesk.draw()

        // when
        dealer.addCard(card)

        // then
        dealer.cards.size shouldBe 1
        dealer.cards.contains(card) shouldBe true
    }

    @ParameterizedTest
    @MethodSource("providePlayerAndDealerCards")
    fun `딜러의 카드와 플레이어의 카드를 비교한다`(target: Card, expected: GameResult) {
        // given
        val dealer = Dealer()
        dealer.addCards(listOf(SPACE_FIVE, target))
        val player = Player.from("pobi")
        player.addCard(SPACE_FIVE)
        player.addCard(SPACE_NINE)

        // when,then
        dealer.getPlayerResult(player).result shouldBe expected
    }

    @Test
    fun `딜러가 bust면 플레이어는 무조건 이긴다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_TEN)
        dealer.addCard(SPACE_FIVE)
        dealer.addCard(SPACE_TEN)
        val player = Player.from("pobi")
        player.addCard(SPACE_TEN)
        player.addCard(SPACE_FIVE)
        player.addCard(SPACE_TEN)

        // when,then
        dealer.getPlayerResult(player).result shouldBe GameResult.WIN
    }

    @Test
    fun `딜러가 bust아닐 때 플레이어가 bust면 플레이어는 무조건 진다`() {
        // given
        val dealer = Dealer()
        dealer.addCards(listOf(SPACE_FIVE, SPACE_TEN, SPACE_FIVE))
        val player = Player.from("pobi")
        player.addCard(SPACE_TEN)
        player.addCard(SPACE_FIVE)
        player.addCard(SPACE_TEN)

        // when,then
        dealer.getPlayerResult(player).result shouldBe GameResult.LOSE
    }

    @Test
    fun `플레이어들과 비교하여 dealer의 결과를 반환한다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_TEN)
        dealer.addCard(SPACE_FOUR)
        val players = Players.from(listOf("pobi", "crong", "honux"))
        players.allPlayers.forEach() { player ->
            player.addCard(SPACE_TEN)
            when (player.name) {
                "pobi" -> player.addCard(SPACE_THREE)
                "crong" -> player.addCard(SPACE_FOUR)
                "honux" -> player.addCard(SPACE_FIVE)
            }
        }

        // when
        val actual = dealer.getDealerResult(players)

        // then
        actual shouldBe mapOf(GameResult.DRAW to 1, GameResult.WIN to 1, GameResult.LOSE to 1)
    }

    @Test
    fun `딜러의 카드가 16이하이면 카드를 한장 더 받는다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_FIVE)
        dealer.addCard(SPACE_TEN)
        val cardDesk = CardDesk()

        // when
        dealer.executeCardDraws(cardDesk) {}

        // then
        dealer.cards.size shouldBe 3
    }

    @Test
    fun `딜러의 카드가 17이상이면 카드를 받지 않는다`() {
        // given
        val dealer = Dealer()
        dealer.addCard(SPACE_TEN)
        dealer.addCard(SPACE_EIGHT)
        val cardDesk = CardDesk()

        // when
        dealer.executeCardDraws(cardDesk) {}

        // then
        dealer.cards.size shouldBe 2
    }

    companion object {
        @JvmStatic
        private fun providePlayerAndDealerCards() = listOf(
            arrayOf(SPACE_NINE, GameResult.DRAW),
            arrayOf(SPACE_TEN, GameResult.LOSE),
            arrayOf(SPACE_EIGHT, GameResult.WIN)
        )
    }
}
