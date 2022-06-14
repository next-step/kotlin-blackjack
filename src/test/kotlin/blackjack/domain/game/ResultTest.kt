package blackjack.domain.game

import blackjack.CardFixtures.CLUB_KING
import blackjack.CardFixtures.DIAMOND_ACE
import blackjack.CardFixtures.HEART_TWO
import blackjack.CardFixtures.MONEY
import blackjack.CardFixtures.SPADE_FIVE
import blackjack.CardFixtures.SPADE_TEN
import blackjack.domain.card.Card
import blackjack.domain.game.Result.Companion.BLACKJACK_PROFIT_PERCENTAGE
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Dealer.Companion.DEALER_BAT_MONEY
import blackjack.domain.participant.Money
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class ResultTest {

    @ParameterizedTest
    @MethodSource("플레이어 패의 합계가 21을 넘지 않고 딜러보다 높은 케이스")
    fun `플레이어가 가진 패의 합계가 21을 넘지 않으면서 딜러보다 높으면 플레이어가 승리한다`(playerCards: Array<Card>, dealerCards: Array<Card>) {
        // given
        val player = Player("pug", MONEY)
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.decideWinner(dealer, player)

        // then
        assertThat(result.scoreByParticipantName)
            .containsEntry(dealer.name, Score(win = 0, lose = 1))
            .containsEntry(player.name, Score(win = 1, lose = 0))
        assertThat(result.totalEarningsByParticipantName)
            .containsEntry(dealer.name, DEALER_BAT_MONEY)
            .containsEntry(player.name, MONEY.bat)
    }

    @ParameterizedTest
    @MethodSource("플레이어 패의 합계가 21을 넘지 않고 딜러보다 낮은 케이스")
    fun `플레이어가 가진 패의 합계보다 딜러의 패의 합계가 높은 경우 플레이어가 패배한다`(playerCards: Array<Card>, dealerCards: Array<Card>) {
        // given
        val player = Player("pug", MONEY)
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))
        val playerMoney = player.money.bat

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.decideWinner(dealer, player)

        // then
        assertThat(result.scoreByParticipantName)
            .containsEntry(dealer.name, Score(win = 1, lose = 0))
            .containsEntry(player.name, Score(win = 0, lose = 1))
        assertThat(result.totalEarningsByParticipantName)
            .containsEntry(dealer.name, DEALER_BAT_MONEY + playerMoney)
            .containsEntry(player.name, 0)
    }

    @ParameterizedTest
    @MethodSource("딜러가 가진 패의 합계가 21을 초과하는 케이스")
    fun `딜러가 가진 패의 합계가 21을 초과하면 플레이어는 가진 패에 상관없이 승리한다`(playerCards: Array<Card>, dealerCards: Array<Card>) {
        // given
        val player = Player("pug", MONEY)
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.decideWinner(dealer, player)

        // then
        assertThat(result.scoreByParticipantName)
            .containsEntry(dealer.name, Score(win = 0, lose = 1))
            .containsEntry(player.name, Score(win = 1, lose = 0))
        assertThat(result.totalEarningsByParticipantName)
            .containsEntry(dealer.name, DEALER_BAT_MONEY)
            .containsEntry(player.name, MONEY.bat)
    }

    @Test
    fun `딜러와 플레이어가 가진 패의 합계가 같은 경우 비긴다`() {
        // given
        val player = Player("pug", MONEY)
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))

        // when
        player.addCards(SPADE_TEN, DIAMOND_ACE)
        dealer.addCards(DIAMOND_ACE, CLUB_KING)
        result.decideWinner(dealer, player)

        // then
        assertThat(result.scoreByParticipantName)
            .containsEntry(dealer.name, Score(win = 0, lose = 0))
            .containsEntry(player.name, Score(win = 0, lose = 0))
        assertThat(result.totalEarningsByParticipantName)
            .containsEntry(dealer.name, DEALER_BAT_MONEY)
            .containsEntry(player.name, MONEY.bat)
    }


    @Test
    fun `플레이어의 패가 블랙잭인 경우 베팅 금액의 150%를 딜러에게 받는다`() {
        // given
        val bat = 20000
        val expectedPlayerFinalEarnings = bat + (bat * BLACKJACK_PROFIT_PERCENTAGE).toInt()
        val expectedDealerFinalEarnings = DEALER_BAT_MONEY - (expectedPlayerFinalEarnings - bat)
        val player = Player("pug", Money(bat))
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))

        // when
        player.addCards(SPADE_TEN, DIAMOND_ACE)
        dealer.addCards(HEART_TWO, SPADE_FIVE)
        result.decideWinner(dealer, player)

        // then
        assertThat(player.money.bat).isEqualTo(expectedPlayerFinalEarnings)
        assertThat(result.totalEarningsByParticipantName)
            .containsEntry(dealer.name, expectedDealerFinalEarnings)
            .containsEntry(player.name, expectedPlayerFinalEarnings)
    }


    @Test
    fun `딜러와 플레이어가 모두 블랙잭인 경우 베팅 금액을 돌려받는다`() {
        // given
        val bat = 20000
        val player = Player("pug", Money(bat))
        val dealer = Dealer()
        val result = Result(dealer, listOf(player))

        // when
        player.addCards(SPADE_TEN, DIAMOND_ACE)
        dealer.addCards(DIAMOND_ACE, CLUB_KING)
        result.decideWinner(dealer, player)

        // then
        assertThat(player.money.bat).isEqualTo(bat)
    }

    companion object {
        @JvmStatic
        fun `플레이어 패의 합계가 21을 넘지 않고 딜러보다 높은 케이스`() = Stream.of(
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING, DIAMOND_ACE), arrayOf(SPADE_TEN, CLUB_KING)),
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING), arrayOf(SPADE_TEN, SPADE_FIVE, HEART_TWO))
        )

        @JvmStatic
        fun `플레이어 패의 합계가 21을 넘지 않고 딜러보다 낮은 케이스`() = Stream.of(
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING), arrayOf(SPADE_TEN, CLUB_KING, DIAMOND_ACE)),
            Arguments.of(arrayOf(SPADE_TEN, HEART_TWO), arrayOf(SPADE_TEN, SPADE_FIVE, HEART_TWO))
        )

        @JvmStatic
        fun `딜러가 가진 패의 합계가 21을 초과하는 케이스`() = Stream.of(
            Arguments.of(arrayOf(SPADE_FIVE), arrayOf(SPADE_TEN, CLUB_KING, SPADE_FIVE)),
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING, DIAMOND_ACE), arrayOf(SPADE_TEN, CLUB_KING, SPADE_FIVE)),
            Arguments.of(arrayOf(SPADE_TEN, CLUB_KING, SPADE_FIVE), arrayOf(SPADE_TEN, CLUB_KING, SPADE_FIVE))
        )
    }
}
