package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `게임 참가 시 2장의 카드 받기`() {
        val player = Player("moshi")

        assertThat(player.myReceivedCard.size).isEqualTo(2)
    }

    @Test
    fun `덱을 한 장 더 받았을 경우`() {
        val player = Player("moshi")

        player.requestDeck()

        assertThat(player.myReceivedCard.size).isEqualTo(3)
    }
}

class Player(name: String) {

    private val _myReceivedCard = mutableListOf<Deck>()
    val myReceivedCard: List<Deck> get() = _myReceivedCard

    init {
        _myReceivedCard.add(Deck.pop())
        _myReceivedCard.add(Deck.pop())
    }

    fun requestDeck() {
        _myReceivedCard.add(Deck.pop())
    }
}
