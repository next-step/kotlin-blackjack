package service

import domain.Card
import domain.CardValue
import domain.Dealer
import domain.Money
import domain.Player
import domain.SignedMoney
import domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackjackResultCalculatorTest {
    private val blackjackResultCalculator = BlackjackResultCalculator()

    @Test
    @DisplayName("플레이어가 Bust(21 초과)면 배팅 금액을 모두 잃는다. 딜러는 그만큼 번다")
    fun profitReport_playerBust() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.SEVEN)) // 17
        }

        val player = Player("A").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.SPADE, CardValue.TEN))
            receiveCard(Card(Suit.DIAMOND, CardValue.NINE))
            receiveCard(Card(Suit.CLUB, CardValue.THREE)) // 22 bust
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(-10_000))
        assertThat(dealer.profit).isEqualTo(SignedMoney(10_000))
    }

    @Test
    @DisplayName("딜러가 Bust(21 초과)면 플레이어는 패와 상관없이 배팅 금액을 받는다 (profit = +bet)")
    fun profitReport_dealerBust() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.NINE))
            receiveCard(Card(Suit.SPADE, CardValue.THREE)) // 22 bust
        }

        val player = Player("A").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.SPADE, CardValue.TWO))
            receiveCard(Card(Suit.DIAMOND, CardValue.THREE)) // 5 (상관없음)
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(10_000))
        assertThat(dealer.profit).isEqualTo(SignedMoney(-10_000))
    }

    @Test
    @DisplayName("플레이어가 블랙잭(첫 두 장 21)이고 딜러는 블랙잭이 아니면 1.5배를 받는다")
    fun profitReport_playerBlackjack_getsOnePointFiveTimes() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.SEVEN)) // 17 (블랙잭 아님)
        }

        val player = Player("A").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.SPADE, CardValue.ACE))
            receiveCard(Card(Suit.DIAMOND, CardValue.TEN)) // blackjack
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(15_000))
        assertThat(dealer.profit).isEqualTo(SignedMoney(-15_000))
    }

    @Test
    @DisplayName("딜러와 플레이어가 동시에 블랙잭이면 플레이어는 본전이다 (profit = 0)")
    fun profitReport_bothBlackjack_push() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.ACE))
            receiveCard(Card(Suit.CLUB, CardValue.TEN)) // blackjack
        }

        val player = Player("A").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.SPADE, CardValue.ACE))
            receiveCard(Card(Suit.DIAMOND, CardValue.TEN)) // blackjack
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(0))
        assertThat(dealer.profit).isEqualTo(SignedMoney(0))
    }

    @Test
    @DisplayName("여러 플레이어가 있을 때 딜러 profit은 플레이어 profit 합의 음수다")
    fun profitReport_dealerProfit_isNegativeSumOfPlayers() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.EIGHT)) // 18
        }

        val winner = Player("WIN").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.SPADE, CardValue.TEN))
            receiveCard(Card(Suit.DIAMOND, CardValue.NINE)) // 19 -> +10_000
        }

        val loser = Player("LOSE").apply {
            placeBet(Money.of(20_000))
            receiveCard(Card(Suit.SPADE, CardValue.TEN))
            receiveCard(Card(Suit.DIAMOND, CardValue.SEVEN)) // 17 -> -20_000
        }

        // when
        blackjackResultCalculator.profitReport(listOf(winner, loser), dealer)

        // then
        assertThat(winner.profit).isEqualTo(SignedMoney(10_000))
        assertThat(loser.profit).isEqualTo(SignedMoney(-20_000))
        assertThat(dealer.profit).isEqualTo(-(winner.profit + loser.profit))
    }

    @Test
    @DisplayName("과제 실행결과 예시를 동일하게 적용한다.")
    fun profitReport_exampleScenario() {
        // given
        // 3다이아몬드, 9클로버, 8다이아몬드 - 결과 : 20
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.DIAMOND, CardValue.THREE))
            receiveCard(Card(Suit.CLUB, CardValue.NINE))
            receiveCard(Card(Suit.DIAMOND, CardValue.EIGHT))
        }

        // 2하트, 8스페이드, A클로버 - 결과 : 21
        val playerA = Player("A").apply {
            placeBet(Money.of(10_000))
            receiveCard(Card(Suit.HEART, CardValue.TWO))
            receiveCard(Card(Suit.SPADE, CardValue.EIGHT))
            receiveCard(Card(Suit.CLUB, CardValue.ACE))
        }

        // 7클로버, K스페이드 - 결과 : 17
        val playerB = Player("B").apply {
            placeBet(Money.of(20_000))
            receiveCard(Card(Suit.CLUB, CardValue.SEVEN))
            receiveCard(Card(Suit.SPADE, CardValue.KING))
        }

        // when
        blackjackResultCalculator.profitReport(listOf(playerA, playerB), dealer)

        // then
        assertThat(dealer.profit).isEqualTo(SignedMoney(10_000))
        assertThat(playerA.profit).isEqualTo(SignedMoney(10_000))
        assertThat(playerB.profit).isEqualTo(SignedMoney(-20_000))
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭이 아니면서 플레이어 점수가 높으면 플레이어가 이긴다")
    fun determineWinStatus_playerWinsWithHigherScore() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.SEVEN)) // 17
        }

        val player = Player("A").apply {
            placeBet(Money.of(99_000))
            receiveCard(Card(Suit.SPADE, CardValue.NINE))
            receiveCard(Card(Suit.DIAMOND, CardValue.NINE)) // 19
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(99_000))
    }

    @Test
    @DisplayName("플레이어, 딜러 모두 블랙잭이 아니면서 딜러 점수가 높으면 딜러가 이긴다")
    fun determineWinStatus_dealerWinsWithHigherScore() {
        // given
        val dealer = Dealer().apply {
            receiveCard(Card(Suit.HEART, CardValue.TEN))
            receiveCard(Card(Suit.CLUB, CardValue.NINE)) // 19
        }

        val player = Player("A").apply {
            placeBet(Money.of(99_000))
            receiveCard(Card(Suit.SPADE, CardValue.NINE))
            receiveCard(Card(Suit.DIAMOND, CardValue.SEVEN)) // 17
        }

        // when
        blackjackResultCalculator.profitReport(listOf(player), dealer)

        // then
        assertThat(player.profit).isEqualTo(SignedMoney(-99_000))
        assertThat(dealer.profit).isEqualTo(SignedMoney(99_000))
    }
}
