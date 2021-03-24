package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import blackjack.domain.createPlayer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTest {
    @DisplayName("유저가 카드를 인자로 받은 경우 카드 목록에 추가")
    @ParameterizedTest
    @MethodSource("provideUsers")
    fun draw(user: User) {
        val card = createCard(CardSymbol.ACE.name, CardSuit.SPADE.name)

        user.draw(card, DrawDecider.DRAW)

        Assertions.assertThat(user.hands.cards).isEqualTo(listOf(card))
    }

    @DisplayName("유저가 가진 카드 점수의 합을 반환")
    @ParameterizedTest
    @MethodSource("provideUsers")
    fun calculateScore(user: User) {
        user.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        user.draw(createCard(CardSymbol.THREE.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        val actual = user.calculateScore()

        Assertions.assertThat(actual.score).isEqualTo(5)
    }

    @DisplayName("유저가 가진 카드 점수의 합이 21을 초과한 경우 0점을 반환")
    @ParameterizedTest
    @MethodSource("provideUsers")
    fun calculateScore2(user: User) {
        user.draw(createCard(CardSymbol.KING.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        user.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        user.draw(createCard(CardSymbol.QUEEN.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        val actual = user.calculateScore()

        Assertions.assertThat(actual.score).isEqualTo(0)
    }

    fun provideUsers() = listOf(Dealer(), createPlayer("pobi"))
}
