package blackjack

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.GameCardsSet
import blackjack.domain.Player
import blackjack.domain.PlayerState
import blackjack.domain.Players
import blackjack.domain.Ranks
import blackjack.domain.Suits
import blackjack.service.BettingService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BettingServiceTest {
    private lateinit var gameCardsSet: GameCardsSet
    private lateinit var bettingService: BettingService
    private lateinit var dealer: Dealer
    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        gameCardsSet = GameCardsSet()
        bettingService = BettingService()
        dealer = Dealer()
        player = Player("사람1", 5000)
    }

    @Test
    fun `플레이어는 베팅 금액을 정할 수 있다`() {
        assertThat(player.bettingMoney).isEqualTo(5000)
    }

    @Test
    fun `플레이어는 버스트할 경우 베팅 금액을 모두 잃는다`() {
        player.run {
            hit(Card.of(Ranks.JACK, Suits.HEARTS))
            hit(Card.of(Ranks.JACK, Suits.SPADES))
            hit(Card.of(Ranks.JACK, Suits.CLUBS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.BUST)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(-5000)
        })
    }

    @Test
    fun `플레이어가 스탠드일 때 합이 딜러보다 적으면 베팅 금액을 모두 잃는다`() {
        player.run {
            hit(Card.of(Ranks.TWO, Suits.HEARTS))
            hit(Card.of(Ranks.THREE, Suits.SPADES))
            stand()
        }

        dealer.run {
            hit(Card.of(Ranks.JACK, Suits.HEARTS))
            hit(Card.of(Ranks.QUEEN, Suits.HEARTS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.STAND)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(-5000)
            assertThat(bettingResult.dealerProfit).isEqualTo(5000)
        })
    }

    @Test
    fun `플레이어는 처음 두 장의 카드가 블랙잭이면 베팅 금액의 1,5배를 딜러에게 받는다`() {
        player.run {
            hit((Card.of(Ranks.ACE, Suits.HEARTS)))
            hit((Card.of(Ranks.JACK, Suits.SPADES)))
        }

        dealer.run {
            hit((Card.of(Ranks.JACK, Suits.HEARTS)))
            hit((Card.of(Ranks.QUEEN, Suits.HEARTS)))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(7500)
            assertThat(bettingResult.dealerProfit).isEqualTo(-7500)
        })
    }

    @Test
    fun `플레이어와 딜러 모두 처음 두 장에서 블랙잭인 경우 플레이어는 베팅 금액을 돌려받는다`() {
        player.run {
            hit(Card.of(Ranks.ACE, Suits.HEARTS))
            hit(Card.of(Ranks.JACK, Suits.SPADES))
        }

        dealer.run {
            hit(Card.of(Ranks.ACE, Suits.SPADES))
            hit(Card.of(Ranks.JACK, Suits.HEARTS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(dealer.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(5000)
            assertThat(bettingResult.dealerProfit).isEqualTo(-5000)
        })
    }

    @Test
    fun `딜러가 버스트할 경우 모든 플레이어는 베팅 금액을 돌려받는다`() {
        player.run {
            hit(Card.of(Ranks.THREE, Suits.HEARTS))
            hit(Card.of(Ranks.FIVE, Suits.SPADES))
        }

        val player2 = Player("사람2", 1000)
        player2.run {
            hit(Card.of(Ranks.FOUR, Suits.HEARTS))
            hit(Card.of(Ranks.SIX, Suits.SPADES))
        }

        dealer.run {
            hit(Card.of(Ranks.SIX, Suits.HEARTS))
            hit(Card.of(Ranks.JACK, Suits.HEARTS))
            hit(Card.of(Ranks.QUEEN, Suits.HEARTS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player, player2)))
        assertAll({
            assertThat(dealer.state).isEqualTo(PlayerState.BUST)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(5000)
            assertThat(bettingResult.playersProfits[player2]).isEqualTo(1000)
            assertThat(bettingResult.dealerProfit).isEqualTo(-6000)
        })
    }

    @Test
    fun `플레이어가 나중에 블랙잭이 되면 베팅 금액을 돌려받는다`() {
        player.run {
            hit(Card.of(Ranks.TEN, Suits.HEARTS))
            hit(Card.of(Ranks.JACK, Suits.SPADES))
            hit(Card.of(Ranks.ACE, Suits.SPADES))
        }

        dealer.run {
            hit(Card.of(Ranks.THREE, Suits.SPADES))
            hit(Card.of(Ranks.JACK, Suits.HEARTS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(5000)
            assertThat(bettingResult.dealerProfit).isEqualTo(-5000)
        })
    }

    @Test
    fun `딜러와 플레이어가 모두 나중에 블랙잭이 되면 플레이어는 베팅 금액을 돌려받는다`() {
        player.run {
            hit(Card.of(Ranks.TEN, Suits.HEARTS))
            hit(Card.of(Ranks.JACK, Suits.SPADES))
            hit(Card.of(Ranks.ACE, Suits.SPADES))
        }

        dealer.run {
            hit(Card.of(Ranks.FIVE, Suits.SPADES))
            hit(Card.of(Ranks.SIX, Suits.HEARTS))
            hit(Card.of(Ranks.QUEEN, Suits.HEARTS))
        }

        val bettingResult = bettingService.bettingResult(dealer, Players(listOf(player)))
        assertAll({
            assertThat(player.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(dealer.state).isEqualTo(PlayerState.BLACK_JACK)
            assertThat(bettingResult.playersProfits[player]).isEqualTo(5000)
            assertThat(bettingResult.dealerProfit).isEqualTo(-5000)
        })
    }
}
