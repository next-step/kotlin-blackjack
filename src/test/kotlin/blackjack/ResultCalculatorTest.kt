package blackjack

import blackjack.domains.deck.Card
import blackjack.domains.deck.Cards
import blackjack.domains.deck.PokerNumber
import blackjack.domains.deck.PokerShape
import blackjack.domains.participants.Dealer
import blackjack.domains.participants.Gamers
import blackjack.domains.participants.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class ResultCalculatorTest {
    @Test
    @DisplayName("딜러카드의 합이 BLACKJACK을 넘어가면 모든 플레이어가 이긴다")
    fun `sut all players should be win when dealer score is over BLACKJACK`() {
        // Arrange
        val dealerCards = Cards()
        dealerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.FIVE, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.KING, PokerShape.CLOVER))
        val dealer = Dealer(name = "dealer", cards = dealerCards)

        val userACards = Cards()
        userACards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))
        userACards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))
        val userA = Player(name = "userA", cards = userACards)

        val userBCards = Cards()
        userBCards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        userBCards.addCard(Card(PokerNumber.FIVE, PokerShape.DIAMOND))
        val userB = Player(name = "userB", cards = userBCards)

        val sut = ResultCalculator(Gamers(listOf(dealer, userA, userB)))

        // Act
        sut.setUserRanks()

        // Assert
        assertThat(dealer.getScoreCounts(GameScoreType.LOSE)).isEqualTo(2)
        assertThat(userA.gameScore).isEqualTo(GameScoreType.WIN)
        assertThat(userB.gameScore).isEqualTo(GameScoreType.WIN)
    }

    @Test
    @DisplayName("플레이어 점수가 BLACKJACK을 넘어가면 딜러가 이긴다")
    fun `sut dealer should be win when player score is over BLACKJACK`() {
        // Arrange
        val dealerCards = Cards()
        dealerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.EIGHT, PokerShape.CLOVER))
        val dealer = Dealer(name = "dealer", cards = dealerCards)

        val userACards = Cards()
        userACards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))
        userACards.addCard(Card(PokerNumber.TEN, PokerShape.DIAMOND))
        userACards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))
        val userA = Player(name = "userA", cards = userACards)

        val userBCards = Cards()
        userBCards.addCard(Card(PokerNumber.TEN, PokerShape.SPACE))
        userBCards.addCard(Card(PokerNumber.NINE, PokerShape.DIAMOND))
        val userB = Player(name = "userB", cards = userBCards)

        val sut = ResultCalculator(Gamers(listOf(dealer, userA, userB)))

        // Act
        sut.setUserRanks()

        // Assert
        assertThat(dealer.getScoreCounts(GameScoreType.WIN)).isEqualTo(1)
        assertThat(dealer.getScoreCounts(GameScoreType.LOSE)).isEqualTo(1)
        assertThat(userA.gameScore).isEqualTo(GameScoreType.LOSE)
        assertThat(userB.gameScore).isEqualTo(GameScoreType.WIN)
    }

    @Test
    @DisplayName("플레이어 점수가 딜러보다 높으면 플레이어가 이긴다")
    fun `sut player should be win when player score is over dealer`() {
        // Arrange
        val dealerCards = Cards()
        dealerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.EIGHT, PokerShape.CLOVER))
        val dealer = Dealer(name = "dealer", cards = dealerCards)

        val userACards = Cards()
        userACards.addCard(Card(PokerNumber.TEN, PokerShape.HEART))
        userACards.addCard(Card(PokerNumber.FIVE, PokerShape.HEART))
        val userA = Player(name = "userA", cards = userACards)

        val userBCards = Cards()
        userBCards.addCard(Card(PokerNumber.TEN, PokerShape.SPACE))
        userBCards.addCard(Card(PokerNumber.NINE, PokerShape.DIAMOND))
        val userB = Player(name = "userB", cards = userBCards)

        val sut = ResultCalculator(Gamers(listOf(dealer, userA, userB)))

        // Act
        sut.setUserRanks()

        // Assert
        assertThat(dealer.getScoreCounts(GameScoreType.WIN)).isEqualTo(1)
        assertThat(dealer.getScoreCounts(GameScoreType.LOSE)).isEqualTo(1)
        assertThat(userA.gameScore).isEqualTo(GameScoreType.LOSE)
        assertThat(userB.gameScore).isEqualTo(GameScoreType.WIN)
    }

    @Test
    @DisplayName("player가 2장의 Blackjack으로 이긴다면 배팅 금액의 150%를 얻는다")
    fun `sut player should earning 150% when player have two cards and blackjack`() {
        // Arrange
        val dealerCards = Cards()
        dealerCards.addCard(Card(PokerNumber.TEN, PokerShape.CLOVER))
        dealerCards.addCard(Card(PokerNumber.EIGHT, PokerShape.CLOVER))
        val dealer = Dealer(name = "dealer", cards = dealerCards)

        val userCards = Cards()
        userCards.addCard(Card(PokerNumber.ACE, PokerShape.HEART))
        userCards.addCard(Card(PokerNumber.KING, PokerShape.HEART))
        val user = Player(name = "userA", cards = userCards)
        user.makeABet(2_000)
        val sut = ResultCalculator(Gamers(listOf(dealer, user)))

        // Act
        sut.setUserRanks()

        // Assert
        assertThat(dealer.earningAmount).isEqualTo(-3_000)
        assertThat(user.earningAmount).isEqualTo(3_000)
    }
}
