package betting.board

import betting.Bet
import betting.BetResult
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BettingBoardTest {
    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    fun `BettingBoard의 participantBets 초기화를 테스트한다`(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
        expectedResult: MutableMap<Participant<*>, BetResult>,
    ) {
        val bettingBoard = BettingBoard()
        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        bettingBoard.participantBets.size shouldBe expectedResult.size
        bettingBoard.participantBets shouldBe expectedResult
    }

    companion object {
        private val POBI = Player(name = "pobi")
        private val JASON = Player(name = "jason")
        private val BET_AMOUNTS = mapOf(POBI to BetResult(bet = Bet(amount = 1_000L)), JASON to BetResult(bet = Bet(amount = 2_000L)))

        @JvmStatic
        fun bettingBoardTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    mapOf(POBI to BET_AMOUNTS[POBI], JASON to BET_AMOUNTS[JASON]),
                    Dealer(),
                    mutableMapOf(POBI to BET_AMOUNTS[POBI], JASON to BET_AMOUNTS[JASON], Dealer() to BetResult(bet = Bet(amount = 0L))),
                ),
            )
    }
}
