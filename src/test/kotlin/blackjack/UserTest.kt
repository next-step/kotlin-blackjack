package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.Diamond
import blackjack.domain.user.User
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class UserTest {
    @ParameterizedTest
    @ValueSource(strings = ["player1", "player2", "player3"])
    fun `유저에게 이름을 적용 할 수 있다`(predictedName: String) {
        val user = User(predictedName)
        val actualName = user.name
        assertThat(actualName).isEqualTo(predictedName)
    }
    @Test
    fun `유저가 가지고 있는 카드 목록을 가져 올 수 있다`() {
        val user = User("player")
        with(user) {
            addCardToHand(Card(Diamond(), 1))
            addCardToHand(Card(Diamond(), 9))
        }
        val actualResult = user.getCards()
        assertThat(actualResult).contains(Card(Diamond(), 1), Card(Diamond(), 9))
    }

    @Test
    fun `유저는 hand의 값이 21 미만일 시 추가 카드를 hand에 추가 할 수 있다`() {
        val user = User("player").apply {
            addCardToHand(Card(Diamond(), 5))
            addCardToHand(Card(Diamond(), 6))
        }
        val actualResult = user.isHandAddable()
        assertThat(actualResult).isTrue
    }

    @Test
    fun `유저는 hand의 값이 21 이상일 시 추가 카드를 hand에 추가 할 수 없다`() {
        val user = User("player").apply {
            addCardToHand(Card(Diamond(), 5))
            addCardToHand(Card(Diamond(), 6))
            addCardToHand(Card(Diamond(), 10))
        }
        val actualResult = user.isHandAddable()
        assertThat(actualResult).isFalse
    }
}
