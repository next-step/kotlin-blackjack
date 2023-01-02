package domain

class CardDeck {

    var heartCards: List<Int> = (1..13).map { it }.shuffled()
    var spaceCards: List<Int> = (1..13).map { it }.shuffled()
    var cloverCards: List<Int> = (1..13).map { it }.shuffled()
    var diamondCards: List<Int> = (1..13).map { it }.shuffled()
    var cardType: List<Int> = (0..3).map { it }

    fun divide(): CardType {
        val randomNumber = (0..3).random()
        println(randomNumber)
        //CardType(randomNumber.toInt())
        return CardType.fromInt(randomNumber)
    }


}