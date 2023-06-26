package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {
    private lateinit var player: Player
    @BeforeEach
    fun setUp(){
        player = Player("은지")
    }
    @Test
    fun getNameTest(){
        assertThat(player.getName()).isEqualTo("은지")
    }

    @Test
    fun addCardTestInitializedWithEmptyArray(){
        assertThat(player.getCards()).isEmpty()
    }

    @Test
    fun getCardsTest(){
        val cards = listOf(Card(CardShape.HEART, CardSymbol.KING))
        player.addCard(cards)
        assertThat(player.getCards()).isEqualTo(cards)
    }

    @Test
    fun getScoreTest(){
        val cards = listOf(Card(CardShape.HEART, CardSymbol.KING))
        player.addCard(cards)
        assertThat(player.getScore()).isEqualTo(10)
    }
}
