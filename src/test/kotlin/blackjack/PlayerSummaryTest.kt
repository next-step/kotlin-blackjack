package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit
import blackjack.domain.card.Cards
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
    fun `플레이어 핸드에 있는 카드 정보를 가져올 수 있다`() {
        val player = Player("vivian")
        val listOfCardsDrawn = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART),
            Card.Ace(CardSuit.CLOVER)
        )

        listOfCardsDrawn.forEach { player.addCardToHand(it) }

        assertThat(PlayerSummary(player).playerCards).isEqualTo(listOfCardsDrawn.map { CardName.of(it) })
    }

    @Test
    fun `플레이어 핸드에 있는 총 카드 합을 가져올 수 있다`() {
        val player = Player("vivian")
        val listOfCardsDrawn = listOf(
            Card.Three(CardSuit.DIAMOND),
            Card.Jack(CardSuit.HEART),
            Card.Ace(CardSuit.CLOVER)
        )

        listOfCardsDrawn.forEach { player.addCardToHand(it) }

        assertThat(PlayerSummary(player).playerCardsTotal).isEqualTo(Cards(listOfCardsDrawn).total.value)
    }
}
