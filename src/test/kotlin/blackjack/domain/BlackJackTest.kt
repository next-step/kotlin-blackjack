package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackTest {

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장을 뽑음")
    fun `Pull 2 cards for the first time`() {
        val players = listOf(Player("홍길동"))
        val playerWithCard = BlackJack.firstPick(players).first()

        assertThat(playerWithCard.cards.size).isEqualTo(2)
    }

    @Test
    @DisplayName("카드 [9, 10]를 가진 선수는 블랙잭 21을 넘지않음")
    fun `Competitors with cards (9, 10) do not exceed blackjack 21`() {
        val player = Player("홍길동")
        player.cards.add(Card(CardNumber.NINE, CardType.CLOVER))
        player.cards.add(Card(CardNumber.TEEN, CardType.CLOVER))
        val isOver = BlackJack.isOverScore(player)

        assertThat(isOver).isFalse
    }

    @Test
    @DisplayName("카드 [10, 10, 10]를 가진 선수는 블랙잭 21을 넘음")
    fun `Players with cards (10, 10, 10) exceed Black Jack 21`() {
        val player = Player("홍길동")
        player.cards.add(Card(CardNumber.TEEN, CardType.CLOVER))
        player.cards.add(Card(CardNumber.TEEN, CardType.CLOVER))
        player.cards.add(Card(CardNumber.TEEN, CardType.CLOVER))
        val isOver = BlackJack.isOverScore(player)

        assertThat(isOver).isTrue
    }

    @Test
    @DisplayName("카드 [ACE, ACE, ACE]를 가진 선수는 최종 점수는 23")
    fun `(ACE, ACE, ACE) The player with the card has a final score of 23`() {
        val player = Player("홍길동")
        player.cards.add(Card(CardNumber.ACE, CardType.CLOVER))
        player.cards.add(Card(CardNumber.ACE, CardType.CLOVER))
        player.cards.add(Card(CardNumber.ACE, CardType.CLOVER))
        val score = BlackJack.getScore(player)

        assertThat(score).isEqualTo(23)
    }
}
