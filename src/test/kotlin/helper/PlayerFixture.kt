package helper

import model.Card
import model.Dealer
import model.Player
import model.PokerNumber
import model.PokerShape

object PlayerFixture {

    fun createPlayer(pokerNumbers: List<PokerNumber>): Player {
        val player = Player("나잘함")
        pokerNumbers.forEach {
            player.hit(Card(it, PokerShape.HEART))
        }
        return player
    }

    fun createDealer(): Dealer {
        val dealer = Dealer()
        dealer.hit(Card(PokerNumber.TEN, PokerShape.HEART))
        dealer.hit(Card(PokerNumber.TWO, PokerShape.HEART))
        return dealer
    }
}
