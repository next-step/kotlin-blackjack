package blackjack.domain.player

import blackjack.domain.DrawDecider
import blackjack.domain.card.CardSuit
import blackjack.domain.card.CardSymbol
import blackjack.domain.createCard
import blackjack.domain.createPlayer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class UserTest {
    @DisplayName("유저가 카드를 인자로 받은 경우 카드 목록에 추가")
    @ParameterizedTest
    @MethodSource("provideUsers")
    fun draw(user: User) {
        val card = createCard(CardSymbol.ACE.name, CardSuit.SPADE.name)

        user.draw(card, DrawDecider.DRAW)

        Assertions.assertThat(user.hands.cards).isEqualTo(listOf(card))
    }

    @DisplayName("유저가 카드를 인자로 받은 경우 카드 목록에 추가")
    @ParameterizedTest
    @MethodSource("provideUsers")
    fun calculateScore(user: User) {
        user.draw(createCard(CardSymbol.TWO.name, CardSuit.SPADE.name), DrawDecider.DRAW)
        user.draw(createCard(CardSymbol.THREE.name, CardSuit.SPADE.name), DrawDecider.DRAW)

        val actual = user.calculateScore()

        Assertions.assertThat(actual.score).isEqualTo(5)
    }

    companion object {
        @JvmStatic
        fun provideUsers() = listOf(Dealer(), createPlayer("pobi"))
    }
}