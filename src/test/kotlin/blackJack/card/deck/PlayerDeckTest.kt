package blackJack.card.deck

import card.CardPack
import card.deck.PlayerDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerDeckTest {

    @Test
    fun `플레이어 카드 추가`() {
        val playerDeck = PlayerDeck.create()

        playerDeck.addCard(CardPack.cards[0])
        
        val actual = playerDeck.cardDeck[0]

        assertThat(actual).isEqualTo(CardPack.cards[0])
    }
}
