package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.CardRank
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {

    @Test
    fun `플레이어가 카드를 받으면 카드 수는 1 증가해야 한다`() {
        // given
        val player = Player("jay", bettingAmount = 1)

        // when
        player.receivedCard(Card.of(CardSuit.Heart, CardRank.Ace))

        // then
        assertThat(player.cards.cards.size).isEqualTo(1)
    }

    @Test
    fun `플레이어의 카드 합이 버스트 되면 카드를 더이상 받을 수 없습니다`() {
        // given
        val cards = mutableListOf(
            Card.of(CardSuit.Heart, CardRank.Ten),
            Card.of(CardSuit.Heart, CardRank.Nine),
            Card.of(CardSuit.Heart, CardRank.Three),
        )
        val player = Player("jay", Cards(cards), 1)

        // when
        val result = player.canReceiveCard()

        // then
        assertThat(result).isFalse()
    }

    @Test
    fun `플레이어가 블랙잭이 되면 카드를 더이상 받을 수 없습니다`() {
        // given
        val cards = mutableListOf(
            Card.of(CardSuit.Heart, CardRank.Ten),
            Card.of(CardSuit.Heart, CardRank.Nine),
            Card.of(CardSuit.Heart, CardRank.Two),
        )
        val player = Dealer(cards = Cards(cards))

        // when
        val result = player.canReceiveCard()

        // then
        assertThat(result).isFalse()
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1, -1000])
    fun `플레이어의 배팅 금액이 0원 이하이면 에러를 던져야 한다`(bettingAmount: Int) {
        // given

        // when
        val result = assertThrows(IllegalArgumentException::class.java) {
            Player(name = "jay", bettingAmount = bettingAmount)
        }

        // then
        assertThat(result.message).isEqualTo("배팅 금액은 0원보다 커야 합니다")
    }

}
