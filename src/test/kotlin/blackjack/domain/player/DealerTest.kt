package blackjack.domain.player

import blackjack.domain.bet.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.BlackJack
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.Stay
import blackjack.domain.util.PlayerStateTestFixture
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.CLUB_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BlackJackFixture.HEART_BLACKJACK
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.CLUB_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.BustFixture.HEART_MINIMUM_BUST
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.CLUB_MAXIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.CLUB_MINIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.HEART_MAXIMUM_STAY
import blackjack.domain.util.PlayerStateTestFixture.StayFixture.HEART_MINIMUM_STAY
import org.assertj.core.api.Assertions
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("딜러(Dealer)")
internal class DealerTest {

    @Test
    fun `드로우 전략에 맞추어 카드를 드로우할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Dealer(state = Ready().draw(extraCard))

        val dealer = Dealer().draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, 블랙잭 상태이다`() {
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(
                    Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK)
                )
            }

        assertThat(dealer.state).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 16이하면, 히트 상태이다`() {
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.TWO))
            }

        assertThat(dealer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 17이상이면, 스테이 상태 이다`() {
        val dealer = Dealer()
            .draw(Deck.initialize { it }) {
                listOf(Card(Suit.CLUB, Denomination.TEN), Card(Suit.CLUB, Denomination.SEVEN))
            }

        assertThat(dealer.state).isExactlyInstanceOf(Stay::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하 17이상이면, Stay 이다`() {
        val hit = Hit(PlayerStateTestFixture.createHands(Suit.CLUB, Denomination.TEN, Denomination.SIX))

        val dealer = Dealer(state = hit)
            .draw(Deck.initialize { it }) { listOf(Card(Suit.CLUB, Denomination.ACE)) }

        assertThat(dealer.state).isExactlyInstanceOf(Stay::class.java)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `딜러가 블랙잭이고, 상대도 블랙잭 상태면 상대의 배팅 금액을 받지 못한다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_BLACKJACK)
        val gamePlayer = Gamer(Name("김우재"), HEART_BLACKJACK)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `딜러가 블랙잭이고, 상대가 버스트 상태면 상대의 배팅 금액을 받는다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_BLACKJACK)
        val gamePlayer = Gamer(Name("김우재"), HEART_MINIMUM_BUST)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `딜러가 블랙잭이고, 상대가 스테이 상태면 상대의 배팅 금액을 받는다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_BLACKJACK)
        val gamePlayer = Gamer(Name("김우재"), HEART_MAXIMUM_STAY)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-15.0", "100:-150.0", "1000:-1500.0"], delimiter = ':')
    fun `딜러가 버스트이고, 상대가 블랙잭 상태면 상대의 배팅 금액의 1배와 반을 준다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_BUST)
        val gamePlayer = Gamer(Name("김우재"), HEART_BLACKJACK)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `딜러가 버스트이고, 상대도 버스트 상태면 배팅 금액을 받지 못한다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_BUST)
        val gamePlayer = Gamer(Name("김우재"), HEART_MINIMUM_BUST)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `딜러가 버스트이고, 상대가 스테이 상태면 배팅 금액만큼 준다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_BUST)
        val gamePlayer = Gamer(Name("김우재"), HEART_MAXIMUM_STAY)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-15.0", "100:-150.0", "1000:-1500.0"], delimiter = ':')
    fun `딜러가 스테이이고, 상대가 블랙잭 상태면 상대의 배팅 금액의 1배와 반을 준다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_STAY)
        val gamePlayer = Gamer(Name("김우재"), HEART_BLACKJACK)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `딜러가 스테이이고, 상대가 버스트상태면 상대의 배팅 금액으 받는다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_STAY)
        val gamePlayer = Gamer(Name("김우재"), HEART_MINIMUM_BUST)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `딜러가 스테이이고, 상대가 자신보다 큰 스테이 상태면 배팅 금액만큼 준다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_STAY)
        val gamePlayer = Gamer(Name("김우재"), HEART_MAXIMUM_STAY)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `딜러가 스테이이고, 상대가 자신과 동일한 스테이 상태면 배팅 금액을 받지 못한다`(amount: Int, expected: Double) {
        val dealer = Dealer(state = CLUB_MINIMUM_STAY)
        val gamePlayer = Gamer(Name("김우재"), HEART_MINIMUM_STAY)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `딜러가 스테이이고, 상대가 자신보다 작은 스테이 상태면 배팅 금액을 받는다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), HEART_MINIMUM_STAY)
        val dealer = Dealer(state = CLUB_MAXIMUM_STAY)

        val actual = dealer.profit(gamePlayer, Money(amount))
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
