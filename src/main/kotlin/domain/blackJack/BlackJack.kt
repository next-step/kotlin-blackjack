package domain.blackJack

import domain.card.CardDeck
import domain.player.Player
import domain.player.Players

class BlackJack(val players: Players, private val cardDeck: CardDeck) {
    fun start(){
        repeat(players.size()){
            players[it].drawCard(cardDeck.draw(), cardDeck.draw())
        }
    }

    fun progress(player: Player){
        players.progress(player,cardDeck)
    }
}
