package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.trump.Card
import blackjack.domain.trump.Cards
import java.util.LinkedList
import java.util.Queue

class BlackJackMachine(private val cards: Queue<Card> = LinkedList(Card.TRUMP_CARDS.shuffled())) {

    fun play(play: Boolean, player: Player): Player {
        if (player.finished || !play) return player.death()
        return draw(player)
    }

    fun init(player: Player): Player {
        return player.renewal(Cards(cards.poll() + cards.poll()))
    }

    private fun draw(player: Player): Player {
        verify()
        if (!player.isAbleToDraw()) return player.death()
        return player.renewal(cards.poll())
    }

    private fun verify() {
        require(cards.isNotEmpty()) { "더 이상 카드를 뽑을 수 없습니다." }
    }
}
