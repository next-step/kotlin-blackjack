package blackJack.card.deck

import card.CardPack
import card.CardRank
import card.PlayingCard
import card.Suit
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

    @Test
    fun `카드 점수 반환`() {
        // given : 플레이어 덱을 생성하고 카드를 추가한다.
        val playerDeck = PlayerDeck()
        playerDeck.addCard(PlayingCard(Suit.DIAMOND, CardRank.ACE))
        playerDeck.addCard(PlayingCard(Suit.DIAMOND, CardRank.TEN))
        playerDeck.addCard(PlayingCard(Suit.DIAMOND, CardRank.TEN))

        // when : 점수 계산 요청
        val actual: Int = playerDeck.getResultPoint()

        // then :
        assertThat(actual).isEqualTo(21)
    }
}
