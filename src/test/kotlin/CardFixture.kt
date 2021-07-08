import model.AbstractPlayer
import model.Card
import model.Denomination
import model.Suit

object CardFixture {
    fun score21OverCards(): List<Card> {
        return listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN), Card(Suit.CLUBS, Denomination.QUEEN))
    }

    fun receiveCard(player: AbstractPlayer, list: List<Card>) {
        for (card in list) {
            player.receive(card)
        }
    }
}