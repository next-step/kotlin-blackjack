package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import blackjack.model.card.CardSuit
import blackjack.model.state.playState.gameState.BlackJack
import blackjack.model.state.playState.gameState.Bust
import blackjack.model.state.playState.gameState.Hit
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class HitTest {

    @Test
    fun `블랙잭인 경우 블랙잭을 반환한다`() {
        val playState = Hit(CardDeck())
        playState.draw(Card(CardNumber.ACE, CardSuit.다이아몬드))
        val result = playState.draw(Card(CardNumber.KING, CardSuit.다이아몬드))

        result.shouldBeInstanceOf<BlackJack>()
    }

    @Test
    fun `버스트인 경우 버스트를 반환한다`() {
        val playState = Hit(CardDeck())
        playState.draw(Card(CardNumber.KING, CardSuit.다이아몬드))
        playState.draw(Card(CardNumber.KING, CardSuit.스페이드))
        val result = playState.draw(Card(CardNumber.KING, CardSuit.클럽))

        result.shouldBeInstanceOf<Bust>()
    }

    @Test
    fun `히트인 경우 히트를 반환한다`() {
        val playState = Hit(CardDeck())
        playState.draw(Card(CardNumber.TWO, CardSuit.다이아몬드))
        playState.draw(Card(CardNumber.THREE, CardSuit.스페이드))
        val result = playState.draw(Card(CardNumber.KING, CardSuit.클럽))

        result.shouldBeInstanceOf<Hit>()
    }
}
