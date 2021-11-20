package blackjack.domain.gamer

import blackjack.domain.deck.Cards
import blackjack.domain.deck.Deck
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
}
