package blackjack

class Score(cards: List<Card>) {


    private fun getScoreByAceCard(cards: List<Card>) {

        cards.filter { it.cardSymbol != CardSymbol.ACE }

    }
}
