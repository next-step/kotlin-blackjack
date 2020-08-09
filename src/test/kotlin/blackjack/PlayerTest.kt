package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `게임 참가 시 2장의 카드 받기`() {
        val player = Player("moshi")

        assertThat(player.myReceivedDeck.size).isEqualTo(2)
    }

    @Test
    fun `덱을 한 장 더 받았을 경우`() {
        val player = Player("moshi")

        player.requestDeck()

        assertThat(player.myReceivedDeck.size).isEqualTo(3)
    }
}

class Player(name: String) {

    private val _myReceivedDeck = mutableListOf<Deck>()
    val myReceivedDeck: List<Deck> get() = _myReceivedDeck

    init {
        repeat(INIT_DECK_SIZE) {
            requestDeck()
        }
    }

    fun requestDeck() {
        _myReceivedDeck.add(Deck.pop())
    }

    companion object {
        private const val INIT_DECK_SIZE = 2
    }
}
