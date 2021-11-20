package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("플레이어의 카드 관리 테스트")
class CardsTest {

    @Test
    @DisplayName("카드 한장을 받을 수 있다")
    fun `sut return cards`() {
        // Arrange
        val card = Card(Denomination.FIVE, Suit.HEART)

        // Act
        val sut = Cards()
        sut.receiveCard(card)

        // Assert
        assertThat(sut.value).hasSize(1)
        assertThat(sut.value).containsExactly(card)
    }

    @Test
    @DisplayName("블랙잭")
    fun `sut returns blackjack`() {
        // Arrange
        val cardAce = Card(Denomination.ACE, Suit.HEART)
        val cardTen = Card(Denomination.TEN, Suit.HEART)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce)
        sut.receiveCard(cardTen)

        // Assert
        assertThat(sut.isBlackjack()).isTrue
        assertThat(sut.getTotalScore()).isEqualTo(21)
    }

    @Test
    @DisplayName("세장 이상의 합이 21일 경우")
    fun `sut returns twenty one`() {
        // Arrange
        val cardFive = Card(Denomination.FIVE, Suit.HEART)
        val cardSix = Card(Denomination.SIX, Suit.HEART)
        val cardTen = Card(Denomination.TEN, Suit.HEART)

        // Act
        val sut = Cards()
        sut.receiveCard(cardFive)
        sut.receiveCard(cardSix)
        sut.receiveCard(cardTen)

        // Assert
        assertThat(sut.isTwentyOne()).isTrue
        assertThat(sut.getTotalScore()).isEqualTo(21)
    }

    @Test
    @DisplayName("카드의 합이 21을 초과하는 경우 버스트")
    fun `sut returns bust`() {
        // Arrange
        val cardFive = Card(Denomination.FIVE, Suit.HEART)
        val cardSix = Card(Denomination.SIX, Suit.HEART)
        val cardTen = Card(Denomination.TEN, Suit.HEART)
        val cardOne = Card(Denomination.ACE, Suit.SPADE)

        // Act
        val sut = Cards()
        sut.receiveCard(cardFive)
        sut.receiveCard(cardSix)
        sut.receiveCard(cardTen)
        sut.receiveCard(cardOne)

        // Assert
        assertThat(sut.isBust()).isTrue
    }

    @Test
    @DisplayName("한 사람이 Ace를 2장 뽑을 경우 1")
    fun `sut returns 2 ace case 1`() {
        // Arrange
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardTen = Card(Denomination.TEN, Suit.HEART)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardTen)

        // Assert
        assertThat(sut.getTotalScore()).isEqualTo(12)
    }

    @Test
    @DisplayName("한 사람이 Ace를 2장 뽑을 경우 2")
    fun `sut returns 2 ace case 2`() {
        // Arrange
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardThree = Card(Denomination.THREE, Suit.HEART)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardThree)

        // Assert
        assertThat(sut.getTotalScore()).isEqualTo(15)
    }

    @Test
    @DisplayName("한 사람이 Ace를 3장 뽑을 경우 1")
    fun `sut returns 3 ace case 1`() {
        // Arrange
        val cardTen = Card(Denomination.TEN, Suit.HEART)
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardAce3 = Card(Denomination.ACE, Suit.CLOVER)

        // Act
        val sut = Cards()
        sut.receiveCard(cardTen)
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardAce3)

        // Assert
        assertThat(sut.getTotalScore()).isEqualTo(13)
    }

    @Test
    @DisplayName("한 사람이 Ace를 3장 뽑을 경우 2")
    fun `sut returns 3 ace case 2`() {
        // Arrange
        val cardNine = Card(Denomination.EIGHT, Suit.HEART)
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardAce3 = Card(Denomination.ACE, Suit.CLOVER)

        // Act
        val sut = Cards()
        sut.receiveCard(cardNine)
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardAce3)

        // Assert
        assertThat(sut.isTwentyOne()).isTrue
        assertThat(sut.getTotalScore()).isEqualTo(21)
    }

    @Test
    @DisplayName("한 사람이 Ace를 4장 뽑을 경우 1")
    fun `sut returns 4 ace case 1`() {
        // Arrange
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardAce3 = Card(Denomination.ACE, Suit.CLOVER)
        val cardAce4 = Card(Denomination.ACE, Suit.DIAMOND)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardAce3)
        sut.receiveCard(cardAce4)

        // Assert
        assertThat(sut.getTotalScore()).isEqualTo(14)
    }

    @Test
    @DisplayName("한 사람이 Ace를 4장 뽑을 경우 2")
    fun `sut returns 4 ace case 2`() {
        // Arrange
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardAce3 = Card(Denomination.ACE, Suit.CLOVER)
        val cardAce4 = Card(Denomination.ACE, Suit.DIAMOND)
        val cardSix = Card(Denomination.SIX, Suit.DIAMOND)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardAce3)
        sut.receiveCard(cardAce4)
        sut.receiveCard(cardSix)

        // Assert
        assertThat(sut.getTotalScore()).isEqualTo(20)
    }

    @Test
    @DisplayName("한 사람이 Ace를 4장 뽑을 경우 3")
    fun `sut returns 4 ace case 3`() {
        // Arrange
        val cardAce1 = Card(Denomination.ACE, Suit.HEART)
        val cardAce2 = Card(Denomination.ACE, Suit.SPADE)
        val cardAce3 = Card(Denomination.ACE, Suit.CLOVER)
        val cardAce4 = Card(Denomination.ACE, Suit.DIAMOND)
        val cardSeven = Card(Denomination.SEVEN, Suit.DIAMOND)

        // Act
        val sut = Cards()
        sut.receiveCard(cardAce1)
        sut.receiveCard(cardAce2)
        sut.receiveCard(cardAce3)
        sut.receiveCard(cardAce4)
        sut.receiveCard(cardSeven)

        // Assert
        assertThat(sut.isTwentyOne()).isTrue
        assertThat(sut.getTotalScore()).isEqualTo(21)
    }
}
