package blackjack.domain.player

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Bust
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.hands.Hands
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.CLUB_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.HEART_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.CLUB_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.HEART_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.CLUB_MAXIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.CLUB_MINIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.HEART_MAXIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.HEART_MINIMUM_STAY
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("게이머(Gameer)")
internal class GamerTest {

    @Test
    fun `드로우 전략에 맞추어 카드를 드로우할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Gamer(Name("김우재"), Ready().draw(extraCard))

        val dealer = Gamer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, 블랙잭 상태이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 21 미만이면, 히트 상태이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.NINE))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, 히트 상태이다`() {
        val maximumHit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.ACE))
                .draw(Card(Suit.CLUB, Denomination.TWO))
        )
        val gamePlayer = Gamer(Name("김우재"), maximumHit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.EIGHT)) }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21초과면, 버스트 상태이다`() {
        val hit = Hit(
            Hands.EMPTY
                .draw(Card(Suit.CLUB, Denomination.TEN))
                .draw(Card(Suit.CLUB, Denomination.JACK))
        )
        val gamePlayer = Gamer(Name("김우재"), hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.TWO)) }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Bust::class.java)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `게이머가 블랙잭이고, 상대도 블랙잭 상태면 배팅 금액을 돌려받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:15.0", "100:150.0", "1000:1500.0"], delimiter = ':')
    fun `게이머가 블랙잭이고, 상대가 버스트 상태면 배팅 금액과 1배 반을 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:15.0", "100:150.0", "1000:1500.0"], delimiter = ':')
    fun `게이머가 블랙잭이고, 상대가 스테이 상태면 배팅 금액과 1배 반을 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `게이머가 버스트이고, 상대가 블랙잭 상태면 배팅 금액을 모두 잃는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `게이머가 버스트이고, 상대도 버스트 상태면 배팅 금액을 돌려 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `게이머가 버스트이고, 상대가 스테이 상태면 배팅 금액을 모두 잃는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `게이머가 스테이이고, 상대가 블랙잭 상태면 배팅 금액을 모두 잃는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `게이머가 스테이이고, 상대가 버스트 상태면 배팅 금액과 1배를 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `게이머가 스테이이고, 상대가 자신보다 큰 Stay 상태면 배팅 금액을 모두 잃는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `게이머가 스테이이고, 상대가 동일한 Stay 상태면 배팅 금액을 돌려받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `게이머가 스테이이고, 상대가 자신보다 작은 Stay 상태면 배팅 금액과 1배를 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MAXIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }
}
