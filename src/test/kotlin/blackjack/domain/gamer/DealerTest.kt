package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
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
}
