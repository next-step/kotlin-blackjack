package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.card.Deck
import blackjack.dto.StayResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackTest {

    private lateinit var deck: Deck
    private lateinit var blackJack: BlackJack

    @BeforeEach
    fun before() {
        deck = Deck()
        blackJack = BlackJack(deck)
    }

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장을 뽑음")
    fun `Pull 2 cards for the first time`() {
        val player = Player("홍길동")
        blackJack.firstPick(player)

        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    @DisplayName("hit인 경우 1장을 뽑음")
    fun `If it's hit, pick one`() {
        val player = Player("홍길동")
        blackJack.hit(player)

        assertThat(player.cards.size).isEqualTo(1)
    }

    @Test
    @DisplayName("응답이 y인경우 stay는 false")
    fun `If the response is y, stay is false`() {
        val answer = "y"
        val stay = StayResult(answer)
        val isStay = blackJack.stay(stay)

        assertThat(isStay).isFalse
    }

    @Test
    @DisplayName("카드가 30인경우 bust는 true")
    fun `If the card is 30, then bust is true`() {
        val cardList = listOf(
            Card(CardNumber.JACK, CardType.CLOVER),
            Card(CardNumber.JACK, CardType.CLOVER),
            Card(CardNumber.JACK, CardType.CLOVER)
        )
        val player = Player("홍길동")
        cardList.forEach { player.enroll(it) }
        val isBust = blackJack.bust(player)

        assertThat(isBust).isTrue
    }
}
