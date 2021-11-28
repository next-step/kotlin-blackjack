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
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

@DisplayName("게임 참가자(GamePlayer)")
internal class GamerTest {

    @Test
    fun `이름과 상태로 생성가능하다`() {
        val gamePlayer = Gamer(Name("김우재"))

        assertAll(
            { assertThat(gamePlayer).isNotNull },
            { assertThat(gamePlayer).isExactlyInstanceOf(Gamer::class.java) }
        )
    }

    @Test
    fun `카드 전략에 따라 드로우를 할 수 있다`() {
        val extraCard = Deck.initialize { it }.pop()
        val expected = Gamer(Name("김우재"), Ready().draw(extraCard))

        val dealer = Gamer(Name("김우재")).draw(Deck.initialize { it }) { listOf(it.pop()) }

        assertThat(dealer).isEqualTo(expected)
    }

    @Test
    fun `처음 뽑은 카드들이 21이면, BlackJack이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.JACK))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `처음 뽑은 카드들이 21미만이면, Hit이다`() {
        val gamePlayer = Gamer(Name("김우재")).draw(Deck.initialize { it }) {
            listOf(Card(Suit.CLUB, Denomination.ACE), Card(Suit.CLUB, Denomination.TWO))
        }

        assertThat(gamePlayer.state).isExactlyInstanceOf(Hit::class.java)
    }

    @Test
    fun `나중에 뽑은 카드들까지의 합이 21이하면, Hit 이다`() {
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
    fun `나중에 뽑은 카드들까지의 합이 21초과면, Bust 이다`() {
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
    fun `BlackJack 상태이고, 상대가 BlackJack 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:15.0", "100:150.0", "1000:1500.0"], delimiter = ':')
    fun `BlackJack 상태이고, 상대가 Bust 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:15.0", "100:150.0", "1000:1500.0"], delimiter = ':')
    fun `BlackJack 상태이고, 상대가 Stay 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_BLACKJACK)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `Bust 상태이고, 상대가 BlackJack 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `Bust 상태이고, 상대가 Bust 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `Bust 상태이고, 상대가 Stay 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_BUST)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `Stay 상태이고, 상대가 BlackJack 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_BLACKJACK)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `Stay 상태이고, 상대가 Bust 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_BUST)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:-10.0", "100:-100.0", "1000:-1000.0"], delimiter = ':')
    fun `Stay 상태이고, 상대가 자신보다 큰 Stay 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MAXIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:0.0", "100:0.0", "1000:0.0"], delimiter = ':')
    fun `Stay 상태이고, 상대가 자신과 동일한 Stay 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MINIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값: {0}")
    @CsvSource(value = ["10:10.0", "100:100.0", "1000:1000.0"], delimiter = ':')
    fun `Stay 상태이고, 상대가 자신보다 작은 Stay 상태의 수익률을 반환한다`(amount: Int, expected: Double) {
        val gamePlayer = Gamer(Name("김우재"), CLUB_MAXIMUM_STAY)
        val dealer = Dealer(state = HEART_MINIMUM_STAY)

        val actual = gamePlayer.profit(dealer, Money(amount))
        assertThat(actual).isEqualTo(expected)
    }
}
