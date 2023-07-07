package blackjack.fixture

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.OpenCards
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName

object PlayerFixture {
    fun bet100(vararg cards: Card): Player = of(Money(100), *cards)

    fun of(money: Money, vararg cards: Card): Player {
        require(cards.size >= 2)

        val openCards = OpenCards(cards[0], cards[1])
        val player = Player(PlayerName.from("default"), money)
        player.open(openCards)

        if (cards.size > 2) {
            val afterOpenCards = cards.toList().subList(2, cards.size)
            afterOpenCards.forEach { player.hit(it) }
        }

        player.stay()
        return player
    }

    fun bust(money: Money = Money(100)): Player {
        return of(money, HEART_KING, DIAMOND_KING, HEART_SEVEN)
    }
}
