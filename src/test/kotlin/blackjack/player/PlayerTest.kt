package blackjack.player

import betting.Bet
import betting.BetResult
import blackjack.card.Card
import blackjack.card.CardFixture
import blackjack.card.Rank
import blackjack.card.Suit
import blackjack.dealer.Dealer
import blackjack.machine.BlackJackMachine.Companion.BONUS_RATIO
import blackjack.participant.ParticipantFixture.hitCards
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class PlayerTest {
    private lateinit var dealer: Dealer
    private lateinit var pobi: Player
    private lateinit var jason: Player
    private lateinit var players: List<Player>

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
        pobi = Player(name = "pobi", betResult = BetResult.Default(bet = Bet(amount = 1_000.0)))
        jason = Player(name = "jason", betResult = BetResult.Default(bet = Bet(amount = 2_000.0)))
        players = listOf(pobi, jason)
    }

    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어의 카드의 합이 21 이하면 게임을 진행할 수 있다`(player: Player) {
        player.isBust() shouldBe false
    }

    @Test
    fun `플레이어의 카드의 합이 21을 초과하면 패배한다`() {
        val player =
            Player.fromNameAndCards(
                name = NAME,
                cards =
                    listOf(
                        CardFixture.generateTestCard(rank = Rank.SIX),
                        CardFixture.generateTestCard(rank = Rank.SIX),
                        CardFixture.generateTestCard(rank = Rank.TEN),
                    ),
            )
        player.isBust() shouldBe true
    }

    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어의 카드가 추가되는지 확인한다()`(player: Player) {
        val newCard = Card(rank = Rank.FIVE, suit = Suit.DIAMOND)
        val result = player.hitCard(card = newCard)
        result.hand.cards shouldContainAll player.hand.cards + newCard
    }

    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어의 상대가 Dealer가 아니라면 승패를 판단할 수 없다`(player: Player) {
        player.isWin(Player.ready("isNotDealer")) shouldBe null
    }

    @ParameterizedTest
    @MethodSource("generateTestPlayer")
    fun `플레이어가 Dealer 보다 카드 수의 합이 크다면 승리한다`(player: Player) {
        player.isWin(Dealer.ready(emptyList())) shouldBe true
    }

    @Test
    fun `플레이어가 Dealer 보다 카드 수의 합이 작다면 패배한다`() {
        val player = Player.ready(name = NAME)
        val dealer = Dealer.ready(CardFixture.generateBlackJack())

        player.isWin(dealer) shouldBe false
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러는 아닌 경우 플레이어는 베팅 금액의 1.5배를 받는다")
    fun playerIsBlackjackButDealerIsNotBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }

        players = players.map { it.handleBlackJack(dealer) }

        dealer.isBlackjack() shouldBe false
        players.forAll { it.isBlackjack() shouldBe true }
        players.forAll { it.winingAmount shouldBe it.betAmount.times(BONUS_RATIO) }
    }

    @Test
    @DisplayName("플레이어의 처음 2장의 합이 21이고, 딜러도 21인 경우 플레이어는 베팅 금액을 돌려 받는다")
    fun playerAndDealerIsBlackjack() {
        players = players.map { it.hitCards(CardFixture.generateBlackJack()) as Player }
        dealer = dealer.hitCards(CardFixture.generateBlackJack()) as Dealer

        players = players.map { it.handleBlackJack(dealer) }

        dealer.isBlackjack() shouldBe true
        players.forAll { it.isBlackjack() shouldBe true }
        players.forAll { it.winingAmount shouldBe it.betAmount }
    }

    @Test
    fun `플레이어가 패배한 경우 베팅한 금액을 잃는다`() {
        pobi = pobi.lose()
        pobi.betResult should beInstanceOf<BetResult.Lose>()
        pobi.winingAmount shouldBe pobi.bet.negative()
    }

    companion object {
        @JvmStatic
        private fun generateTestPlayer() =
            listOf(
                Player.fromNameAndCards(
                    name = NAME,
                    cards = CardFixture.generateBlackJack(),
                ),
            )

        private const val NAME = "nooblette"

        private fun Player.Companion.fromNameAndCards(
            name: String,
            cards: List<Card>,
        ): Player = Player(name = name, hand = Hand(cards = cards))
    }
}
