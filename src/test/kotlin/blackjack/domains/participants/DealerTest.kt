package blackjack.domains.participants

import blackjack.GameScoreType
import blackjack.domains.deck.Card
import blackjack.domains.deck.Cards
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    @DisplayName("딜러가 가진 카드의 합이 17보다 작으면 true를 반환한다")
    fun `sut should return true when dealer's sum of cards is under 17 given`() {
        // Arrange
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.SIX, PokerShape.DIAMOND))

        val dealer = Dealer(name = "딜러", cards = cards)

        // Act
        val act = dealer.isDrawable()

        // Assert
        assertThat(act).isTrue
    }

    @Test
    @DisplayName("딜러가 가진 카드의 합이 21보다 크면 true를 반환한다")
    fun `sut should return true when dealer's sum of cards is over 21 given`() {
        // Arrange
        val cards = Cards()
        cards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        cards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        cards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))

        val dealer = Dealer(name = "딜러", cards = cards)

        // Act
        val act = dealer.isOver21()

        // Assert
        assertThat(act).isTrue
    }

    @Test
    @DisplayName("딜러보다 플레이어의 점수가 더 높으면 WIN을 반환한다")
    fun `sut should return WIN when higher user score than the dealer score is given `() {
        // Arrange
        val dealerCards = Cards()
        dealerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))
        dealerCards.addCard(Card(PokerNumber.TWO, PokerShape.DIAMOND))

        val dealer = Dealer(name = "딜러", cards = dealerCards)

        val playerCards = Cards()
        playerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        playerCards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))
        playerCards.addCard(Card(PokerNumber.FIVE, PokerShape.DIAMOND))
        val player = Player(name = "modernflow", cards = playerCards)

        // Act
        val act = dealer.calculatePlayerResult(player.summedCardNumbers)

        // Assert
        assertThat(act.toString()).isEqualTo(GameScoreType.WIN.toString())
    }

    @Test
    @DisplayName("딜러가 몇번 우승했는지 알려준다")
    fun `sut should return counts of WIN when GameScoreType_WIN is given `() {
        // Arrange
        val cards = Cards()
        val dealer = Dealer(name = "딜러", cards = cards)
        dealer.win()
        dealer.win()
        dealer.lose()
        dealer.draw()

        // Act
        val act = dealer.getScoreCounts(GameScoreType.WIN)

        // Assert
        assertThat(act).isEqualTo(2)
    }
}
