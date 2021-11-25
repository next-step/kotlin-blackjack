package blackjack.domain.gamer

import blackjack.domain.deck.Card
import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.result.DealerResultType
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Deal
import blackjack.domain.state.Stand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("딜러 테스트")
class DealerTest {

    @Test
    @DisplayName("딜러를 생성한다")
    fun `sut returns dealer`() {
        // Act
        val sut = Dealer.from(Cards())

        // Assert
        assertThat(sut.name).isEqualTo("딜러")
    }

    @Test
    @DisplayName("딜러가 카드 2장을 뽑고 준비를 완료한다")
    fun `sut returns prepared`() {
        // Arrange
        val dealer = Dealer.from(Cards())
        val deck = Deck.init()

        // Act
        val preparedDealer = dealer.prepare(deck)

        // Assert
        assertThat(preparedDealer.name).isEqualTo("딜러")
        assertThat(preparedDealer.cards.value).hasSize(2)
    }

    @Test
    @DisplayName("딜러의 두장 카드 합이 17보다 크고, 21일 경우 블랙잭")
    fun `sut returns progressed dealer blackjack`() {
        // Arrange
        val dealer = Dealer.from(Cards())
        val deck = Deck.init()

        // Act
        val sut = dealer.prepare(deck)
        val actual = sut.progress(21, deck)

        // Assert
        assertThat(actual.state).isInstanceOf(Blackjack::class.java)
        assertThat(actual.cards.value).hasSize(2)
    }

    @Test
    @DisplayName("딜러의 두장 카드 합이 17보다 크고, 21을 초과할 경우 Bust")
    fun `sut returns progressed dealer bust`() {
        // Arrange
        val dealer = Dealer.from(Cards())
        val deck = Deck.init()

        // Act
        val sut = dealer.prepare(deck)
        val actual = sut.progress(22, deck)

        // Assert
        assertThat(actual.state).isInstanceOf(Bust::class.java)
        assertThat(actual.cards.value.size).isGreaterThanOrEqualTo(2)
    }

    @Test
    @DisplayName("딜러의 두장 카드 합이 17보다 클 경우 Stand")
    fun `sut returns progressed dealer stand`() {
        // Arrange
        val dealer = Dealer.from(Cards())
        val deck = Deck.init()

        // Act
        val sut = dealer.prepare(deck)
        val actual = sut.progress(18, deck)

        // Assert
        assertThat(actual.state).isInstanceOf(Stand::class.java)
        assertThat(actual.cards.value).hasSize(2)
    }

    @Test
    @DisplayName("딜러의 두장 카드 합이 17보다 작으면 카드를 더 뽑는다")
    fun `sut returns progressed dealer play`() {
        // Arrange
        val dealer = Dealer.from(Cards())
        val deck = Deck.init()

        // Act
        val sut = dealer.prepare(deck)
        val actual = sut.progress(10, deck)

        // Assert
        assertThat(actual.state).isNotInstanceOf(Dealer::class.java)
        assertThat(actual.cards.value).hasSizeGreaterThanOrEqualTo(3)
    }

    @Test
    @DisplayName("딜러의 카드 결과를 토대로 게임 결과를 판단한다 - 딜러의 결과가 더 크면 딜러 승리")
    fun `sut returns blackjack game result win`() {
        // Arrange
        val playerCards = createCardsFixture(Denomination.SIX, Denomination.SIX)
        val player = Player.init("tommy", Deal(playerCards))

        // Act
        val sut = Dealer.from(createCardsFixture(Denomination.TEN, Denomination.EIGHT))
        val actual = sut.judgeGameResult(player)

        // Assert
        assertThat(actual).isEqualTo(DealerResultType.WIN)
    }

    @Test
    @DisplayName("딜러의 카드 결과를 토대로 게임 결과를 판단한다 - 딜러와 플레이어의 합이 같으면 무승부")
    fun `sut returns blackjack game result push`() {
        // Arrange
        val playerCards = createCardsFixture(Denomination.TEN, Denomination.QUEEN)
        val player = Player.init("tommy", Deal(playerCards))

        // Act
        val sut = Dealer.from(createCardsFixture(Denomination.TEN, Denomination.KING))
        val actual = sut.judgeGameResult(player)

        // Assert
        assertThat(actual).isEqualTo(DealerResultType.PUSH)
    }

    @Test
    @DisplayName("딜러의 카드 결과를 토대로 게임 결과를 판단한다 - 딜러의 결과가 더 작으면 딜러 패배")
    fun `sut returns blackjack game lose`() {
        // Arrange
        val playerCards = createCardsFixture(Denomination.TEN, Denomination.ACE)
        val player = Player.init("tommy", Deal(playerCards))

        // Act
        val sut = Dealer.from(createCardsFixture(Denomination.TEN, Denomination.EIGHT))
        val actual = sut.judgeGameResult(player)

        // Assert
        assertThat(actual).isEqualTo(DealerResultType.LOSE)
    }

    private fun createCardsFixture(firstDenomination: Denomination, secondDenomination: Denomination): Cards {
        return Cards(
            listOf(
                Card(firstDenomination, Suit.DIAMOND),
                Card(secondDenomination, Suit.HEART),
            )
        )
    }
}
