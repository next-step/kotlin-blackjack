import domain.CardDeck
import service.BlackjackGameService
import service.BlackjackResultCalculator
import service.CardDistributorService

fun main() {
    BlackjackGameService(
        CardDistributorService(CardDeck()),
        BlackjackResultCalculator()
    ).run()
}
