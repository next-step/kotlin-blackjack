package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import blackjack.domain.game.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayersTest {

    @DisplayName("플레이어를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = ["aa, bb"])
    fun createPlayers(input: String) {
        assertThat(Players(input).participants).contains(
            Player(0, "aa"), Player(1, "bb")
        )
    }

    @DisplayName("하나 이상의 이름이 필요하다.")
    @Test
    fun emptyPlayer() {
        assertThatIllegalArgumentException().isThrownBy { Players("") }
    }

    @DisplayName("딜러보다 점수가 높은 플레이어가 우승자가 된다.")
    @Test
    fun findWinner() {
        val players = Players("a, b")
        val dealer = Dealer()
        dealer.receiveCards(Card(Pip.TEN, Suit.HEART), Card(Pip.SEVEN, Suit.HEART))
        players(0).receiveCards(Card(Pip.TEN, Suit.HEART), Card(Pip.TEN, Suit.HEART), Card(Pip.TWO, Suit.HEART))
        players(1).receiveCards(Card(Pip.NINE, Suit.HEART), Card(Pip.TEN, Suit.HEART))

        assertThat(players.findWinners(dealer)).contains(players(1))
    }

    @DisplayName("딜러가 버스트일 경우 남은 플레이어 모두가 우승자가 된다.")
    @Test
    fun findWinnerWhenDealerBust() {
        val players = Players("a, b, c")
        val dealer = Dealer()
        dealer.receiveCards(Card(Pip.TEN, Suit.HEART), Card(Pip.TEN, Suit.HEART), Card(Pip.TWO, Suit.HEART))
        assertThat(players.findWinners(dealer)).contains(players(0), players(1), players(2))
    }
}
