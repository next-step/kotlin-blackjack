package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.OpenCards
import blackjack.domain.Player
import blackjack.domain.PlayerName

object PlayerFixture {

    fun of(vararg cards: Card): Player {
        require(cards.size >= 2)

        val openCards = OpenCards(cards[0], cards[1])
        val player = Player.of(PlayerName.from("default"), openCards)

        if (cards.size > 2) {
            val afterOpenCards = cards.toList().subList(2, cards.size)
            afterOpenCards.forEach { player.hit(it) }
        }

        return player
    }

    fun bust(): Player {
        return of(HEART_KING, DIAMOND_KING, HEART_SEVEN)
    }

}


