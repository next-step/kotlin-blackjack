package blackjack.common

import blackjack.domain.card.`20 point card`
import blackjack.domain.card.Card
import blackjack.domain.card.CardName
import blackjack.domain.card.CardSuit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerSummaryTest {
    @Test
    fun `플레이어의 이름을 가져올 수 있다`() {
        val name = "vivian"
        val player = Player(name)

        assertThat(PlayerSummary(player).playerName).isEqualTo(name)
    }

    @Test
    fun `플레이어 핸드에 있는 카드 전체 이름를 가져올 수 있다`() {
        val cards = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART),
            Card.Ace(CardSuit.CLOVER)
        )

        assertThat(
            PlayerSummary(Player(cards)).playerCards
        ).isEqualTo(cards.map { CardName.of(it) })
    }

    @Test
    fun `플레이어 핸드에 있는 총 카드 합을 가져올 수 있다`() {
        assertThat(
            PlayerSummary(Player(`20 point card`())).playerCardsTotal
        ).isEqualTo(20)
    }

    @Test
    fun `excludeHiddenCard 가 true 일 경우 딜러의 첫번째카드는 비공개이다`() {
        val cards = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART)
        )

        assertThat(
            PlayerSummary(Dealer(cards), excludeHiddenCard = true).playerCards
        ).isEqualTo(listOf("3다이아몬드"))
    }

    @Test
    fun `excludeHiddenCard 가 false 일 경우 딜러의 모든 카드는 공개되어있다`() {
        val cards = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART)
        )

        assertThat(
            PlayerSummary(Dealer(cards), excludeHiddenCard = false).playerCards
        ).isEqualTo(listOf("3다이아몬드", "J하트"))
    }

    @Test
    fun `excludeHiddenCard 가 true 일 경우에도 플레이어의 모든 카드는 공개되어있다`() {
        val cards = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART)
        )

        assertThat(
            PlayerSummary(Player(cards), excludeHiddenCard = true).playerCards
        ).isEqualTo(listOf("3다이아몬드", "J하트"))
    }
}
