package model

class Dealer(private val trump: Trump = Trump()) {
    fun pick(): Card = trump.pick()
    fun cardCount(): Int = trump.count()
}
