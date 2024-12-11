package betting.board

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.card.CardFixture
import blackjack.dealer.Dealer
import blackjack.participant.Participant
import blackjack.player.Hand
import blackjack.player.Player
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BettingBoardTest {
    private lateinit var bettingBoard: BettingBoard

    @BeforeEach
    fun setUp() {
        bettingBoard = BettingBoard()
    }
    
    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    fun `BettingBoard의 participantBets 초기화를 테스트한다`(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
        expectedResult: MutableMap<Participant<*>, BetResult>,
    ) {
        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        bettingBoard.participantBets.size shouldBe expectedResult.size
        bettingBoard.participantBets shouldBe expectedResult
    }

    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    fun `플레이어가 패배한 경우 베팅한 금액을 잃고 딜러가 베팅금을 얻는다`(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        bettingBoard.bust(player = POBI)

        val pobiBet = bettingBoard.participantBets[POBI] ?: return
        pobiBet.winning.amount shouldBe BET_AMOUNTS[POBI]?.bet?.negative()

        val dealerBet = bettingBoard.participantBets[dealer] ?: return
        dealerBet.winning.amount shouldBe BET_AMOUNTS[POBI]?.bet?.amount
    }

    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    fun `딜러가 패배하는 경우 모든 플레이어는 베팅 금액을 받는다`(
        playerBets: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        bettingBoard.winAllPlayer()

        dealer.betAmount() shouldBe sumOfPlayerBetsWithNegative()
        bettingBoard.participantBets
            .filter { it.key != dealer }
            .forAll { (_, betResult) -> betResult.bet.amount shouldBe betResult.winning.amount }
    }

    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러는 아닌 경우 플레이어는 베팅 금액의 1.5배를 받는다")
    fun playerIsBlackjackButDealerIsNotBlackjack(
        playerBetParameters: Map<Participant<*>, BetResult>,
        dealer: Dealer,
    ) {
        val playerBets = playerBetParameters.mapKeys { (player, _ ) -> player.hitCards(CardFixture.generateBlackJack()) }
        
        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        playerBets.forAll { (player, _ ) -> player.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe false
        dealer.betAmount() shouldBe sumOfPlayerBetsWithNegative().times(BettingBoard.BONUS_RATIO)
    }

    @ParameterizedTest
    @MethodSource("bettingBoardTest")
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러도 21인 경우 플레이어는 베팅 금액을 돌려 받는다")
    fun playerAndDealerIsBlackjack(
        playerBetParameters: Map<Participant<*>, BetResult>,
        dealerParameter: Dealer,
    ) {
        val playerBets = playerBetParameters.mapKeys { (player, _ ) -> player.hitCards(CardFixture.generateBlackJack()) }
        val dealer = Dealer(name = dealerParameter.name, hand = Hand(cards = CardFixture.generateBlackJack()))

        bettingBoard.setup(playerBets = playerBets, dealer = dealer)

        playerBets.forAll { (player, _ ) -> player.isBlackjack() shouldBe true }
        dealer.isBlackjack() shouldBe true
        dealer.betAmount() shouldBe sumOfPlayerBetsWithNegative()
    }

    private fun sumOfPlayerBetsWithNegative() = bettingBoard.participantBets
        .filter { it.key !is Dealer }
        .values
        .sumOf { it.bet.negative() }

    private fun Dealer.betAmount() = bettingBoard.participantBets[this]?.winning?.amount

    private fun Participant<*>.hitCards(cards: List<Card>): Participant<*> =
        Player(name = this.name, hand = Hand(cards = this.hand.cards.plus(cards)))


    companion object {
        private val POBI = Player(name = "pobi")
        private val JASON = Player(name = "jason")
        private val BET_AMOUNTS = mapOf(POBI to BetResult(bet = Bet(amount = 1_000.0)), JASON to BetResult(bet = Bet(amount = 2_000.0)))

        @JvmStatic
        fun bettingBoardTest(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    mapOf(POBI to BET_AMOUNTS[POBI], JASON to BET_AMOUNTS[JASON]),
                    Dealer(),
                    mutableMapOf(POBI to BET_AMOUNTS[POBI], JASON to BET_AMOUNTS[JASON], Dealer() to BetResult(bet = Bet(amount = 0.0))),
                ),
            )
    }
}
