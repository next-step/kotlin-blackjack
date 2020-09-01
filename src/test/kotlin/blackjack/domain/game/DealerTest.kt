package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import blackjack.domain.player.DetailResult
import blackjack.domain.player.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {
    private val dealer = Dealer()
    private val players = Players("a, b, c")

    @DisplayName("모든 플레이어에게 카드를 2장씩 지급한다.")
    @Test
    fun initialSetting() {
        dealer.initPlayersHand(players)
        assertThat(players.participants).allSatisfy { it.getCards().size == 2 }
    }

    @DisplayName("특정 플레이어에게 카드를 지급할 수 있다.")
    @Test
    fun giveCardToOne() {
        dealer.giveCardTo(players(0))
        assertThat(players(0).getCards().size).isEqualTo(1)
    }

    @DisplayName("딜러는 초기에 2장의 카드를 받는다.")
    @Test
    fun receiveOneCard() {
        dealer.initPlayersHand(players)
        assertThat(dealer.getCards().size).isEqualTo(2)
    }

    @DisplayName("딜러의 패가 16 이하면 카드를 지급받는다.")
    @Test
    fun receiveMoreCard() {
        dealer.fillHand()
        assertThat(dealer.getScore()).isGreaterThan(16)
    }

    @DisplayName("모든 플레이어에 대한 게임 결과를 얻을 수 있다.")
    @Test
    fun findDetailResult() {
        dealer.receiveCards(Card(Pip.TWO, Suit.HEART), Card(Pip.TWO, Suit.HEART))
        val players = Players("a, b, c")
        players(0).receiveCards(Card(Pip.ACE, Suit.HEART), Card(Pip.TEN, Suit.HEART))
        players(1).receiveCards(Card(Pip.TWO, Suit.HEART), Card(Pip.TWO, Suit.HEART))
        players(2).receiveCards(Card(Pip.TEN, Suit.HEART), Card(Pip.TEN, Suit.HEART), Card(Pip.TEN, Suit.HEART))

        assertThat(dealer.findGameResult(players)).isEqualTo(DetailResult(1, 1, 1))
    }
}
