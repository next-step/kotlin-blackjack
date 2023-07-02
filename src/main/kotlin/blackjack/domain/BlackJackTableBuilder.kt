package blackjack.domain

import blackjack.domain.card.BlackCardDeck
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardType
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNotify
import blackjack.domain.player.PlayersBuilder

class BlackJackTableBuilder {

    private lateinit var players: MutableList<Player>
    lateinit var cardDeck: BlackCardDeck
    lateinit var notify: PlayerNotify

    fun joinPlayers(block: PlayersBuilder.() -> Unit) {
        players = PlayersBuilder.of(notify).apply(block).build()
    }
    fun makeCardDeck(): BlackCardDeck {
        return CardType.getCardDeck(CardNumber.values())
    }

    fun build(): BlackJackTable {
        return BlackJackTable(players, cardDeck)
    }

}