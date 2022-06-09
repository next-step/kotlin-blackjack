package blackjack.domain.game

import blackjack.domain.CLUB_KING
import blackjack.domain.DIAMOND_ACE
import blackjack.domain.HEART_TWO
import blackjack.domain.SPADE_FIVE
import blackjack.domain.SPADE_TEN
import blackjack.domain.card.Card
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import org.assertj.core.api.Assertions
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
        val result = Result()
        val player = Player("pug")
        val dealer = Dealer()

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.checkWinner(dealer, player)

        // then
        Assertions.assertThat(result.scoreByParticipant)
            .containsEntry(dealer, Score(win = 0, lose = 1))
            .containsEntry(player, Score(win = 1, lose = 0))
    }

    @ParameterizedTest
    @MethodSource("플레이어 패의 합계가 21을 넘지 않고 딜러보다 낮은 케이스")
    fun `플레이어가 가진 패의 합계보다 딜러의 패의 합계가 높은 경우 플레이어가 패배한다`(playerCards: Array<Card>, dealerCards: Array<Card>) {
        // given
        val result = Result()
        val player = Player("pug")
        val dealer = Dealer()

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.checkWinner(dealer, player)

        // then
        Assertions.assertThat(result.scoreByParticipant)
            .containsEntry(dealer, Score(win = 1, lose = 0))
            .containsEntry(player, Score(win = 0, lose = 1))
    }

    @ParameterizedTest
    @MethodSource("딜러가 가진 패의 합계가 21을 초과하는 케이스")
    fun `딜러가 가진 패의 합계가 21을 초과하면 플레이어는 가진 패에 상관없이 승리한다`(playerCards: Array<Card>, dealerCards: Array<Card>) {
        // given
        val result = Result()
        val player = Player("pug")
        val dealer = Dealer()

        // when
        player.addCards(*playerCards)
        dealer.addCards(*dealerCards)
        result.checkWinner(dealer, player)

        // then
        Assertions.assertThat(result.scoreByParticipant)
            .containsEntry(dealer, Score(win = 0, lose = 1))
            .containsEntry(player, Score(win = 1, lose = 0))
    }

    @Test
    fun `딜러와 플레이어가 가진 패의 합계가 같은 경우 비긴다`() {
        // given
        val result = Result()
        val player = Player("pug")
        val dealer = Dealer()

        // when
        player.addCards(SPADE_TEN, DIAMOND_ACE)
        dealer.addCards(DIAMOND_ACE, CLUB_KING)
        result.checkWinner(dealer, player)

        // then
        Assertions.assertThat(result.scoreByParticipant)
            .doesNotContainKeys(dealer, player)
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
