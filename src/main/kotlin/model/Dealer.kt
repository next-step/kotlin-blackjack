package model

class Dealer(val trump: Trump = Trump()) {
    fun hit(): Card = trump.hit()
}
