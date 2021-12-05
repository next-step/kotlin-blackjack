package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.SuitTypes.Diamond
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["player1", "player2", "player3"])
    fun `참가자에게 이름을 적용 할 수 있다`(predictedName: String) {
        val predictedPlayerName = predictedName.toPlayerName()
        val player = Player(predictedPlayerName)
        val actualName = player.name
        assertThat(actualName).isEqualTo(predictedPlayerName)
    }

    @Test
    fun `참가자가 가지고 있는 카드 목록을 가져 올 수 있다`() {
        val player = Player("player".toPlayerName())
        with(player) {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val actualResult = player.getCards()
        assertThat(actualResult).contains(Card(Diamond, 1), Card(Diamond, 9))
    }

    @Test
    fun `참가자가 가지고 있는 카드값 합들을 가져 올 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val player = Player("player".toPlayerName(), hand)
        assertThat(player.getHandValues()).isEqualTo(listOf(20, 10))
        player.addCardToHand(Card(Diamond, 1))
        assertThat(player.getHandValues()).isEqualTo(listOf(21))
    }

    @Test
    fun `참가자는 hand의 값이 21 미만일 시 추가 카드를 hand에 추가 할 수 있다`() {

        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
        }
        val player = Player("player".toPlayerName(), hand)
        val actualResult = player.isHandAddable()
        assertThat(actualResult).isTrue
    }

    @Test
    fun `참가자는 hand의 값이 21 이상일 시 추가 카드를 hand에 추가 할 수 없다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
            addCardToHand(Card(Diamond, 10))
        }
        val player = Player("player".toPlayerName(), hand)
        val actualResult = player.isHandAddable()
        assertThat(actualResult).isFalse
    }

    fun String.toPlayerName() = PlayerName(this)
}
